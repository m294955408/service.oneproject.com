package com.shuma.oneproject.controller;

import com.shuma.oneproject.common.result.JsonUtil;
import com.shuma.oneproject.model.CategoryModel;
import com.shuma.oneproject.model.viewmodel.blog.CategoryFormModel;
import com.shuma.oneproject.model.viewmodel.blog.CreateCategoryFormModel;
import com.shuma.oneproject.model.viewmodel.blog.EditCategoryFormModel;
import com.shuma.oneproject.service.BlogService;
import com.shuma.oneproject.web.auth.WebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/26
 * @desc
 */
@Controller
@RequestMapping("/api/blog")
public class BlogController extends BaseController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private WebSecurity webSecurity;

    @RequestMapping ( value = "/category/find", method = RequestMethod.GET )
    @ResponseBody
    private String findCategory(int id) {
        return JsonUtil.toJson(blogService.getCategory(id, webSecurity.getCurrentUserName()));
    }

    @RequestMapping ( value = "/category/list", method = RequestMethod.GET )
    @ResponseBody
    private String findCategorys() {
        return JsonUtil.toJson(blogService.getCategorys(webSecurity.getCurrentUserName()));
    }

    @RequestMapping ( value = "/category/create", method = RequestMethod.POST)
    @ResponseBody
    private String createCategory(@RequestBody CreateCategoryFormModel category) throws InstantiationException, IllegalAccessException {
        return JsonUtil.toJson(blogService.createCategory(category.toModel(CategoryModel.class)));
    }

    @RequestMapping ( value = "/category/edit", method = RequestMethod.POST)
    @ResponseBody
    private String editCategory(int id, @RequestBody EditCategoryFormModel category) throws InstantiationException, IllegalAccessException {
        return JsonUtil.toJson(blogService.editCategory(id, category.toModel(CategoryModel.class)));
    }
}
