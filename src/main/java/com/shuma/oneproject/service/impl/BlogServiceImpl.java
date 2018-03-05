package com.shuma.oneproject.service.impl;

import com.shuma.oneproject.common.errorcode.BlogErrorCode;
import com.shuma.oneproject.common.result.ListOperateResult;
import com.shuma.oneproject.common.result.SingleOperateResult;
import com.shuma.oneproject.dao.CategoryDao;
import com.shuma.oneproject.entity.Category;
import com.shuma.oneproject.model.CategoryModel;
import com.shuma.oneproject.model.utils.DataConverter;
import com.shuma.oneproject.service.BlogService;
import com.shuma.oneproject.web.auth.WebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/26
 * @desc
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private WebSecurity webSecurity;

    //region 类别管理

    public SingleOperateResult getCategory(int id, String username) {
        // 获取类别
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", id);
        Category category = categoryDao.selectOne(condition);

        // 判断是否为请求用户
        if(category.getUserName().equals(username)) {
            CategoryModel model = new CategoryModel();
            try {
                model = DataConverter.Cast(category, CategoryModel.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return SingleOperateResult.Success(model);
        } else {
            return SingleOperateResult.Fail(BlogErrorCode.CURRENT_USER_DONOT_HAVE_CATEGORY);
        }
    }

    public ListOperateResult getCategorys(String username) {
        // 获取类别列表
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userName", username);
        List<Category> categories = categoryDao.select(condition);

        // 将类别实体转换为类别模型
        List<CategoryModel> items = new ArrayList<CategoryModel>();
        try {
            items = DataConverter.Cast(categories, CategoryModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ListOperateResult.Success(items);
    }

    public SingleOperateResult createCategory(CategoryModel model)
    {
        // 获取用户
        String username = webSecurity.getCurrentUserName();

        Category category = null;
        // 先校验该用户Category是否已经存在
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("userName", username);
        condition.put("name", model.getName());
        category = categoryDao.selectOne(condition);

        // 若Category存在，则返回对应错误码
        if(category != null) {
            return SingleOperateResult.Fail(BlogErrorCode.CURRENT_USER_EXIST_CATEGORY);
        }

        // 创建逻辑
        try {
            category = DataConverter.Restore(model, Category.class);
            // 绑定当前用户
            category.setUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 插入数据库
        categoryDao.insert(category);

        return SingleOperateResult.Success();
    }

    public SingleOperateResult editCategory(int id, CategoryModel model)
    {
        // 获取用户
        String username = webSecurity.getCurrentUserName();

        Category category = null;
        // 先校验该用户Category是否已经存在
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("id", id);
        category = categoryDao.selectOne(condition);

        // 若不存在，则返回错误码
        if(category == null || !category.getUserName().equals(username)) {
            return SingleOperateResult.Fail(BlogErrorCode.CURRENT_USER_DONOT_HAVE_CATEGORY);
        }

        // 编辑
        DataConverter.Assign(model, category);

        // 更新数据库
        categoryDao.update(category);

        return SingleOperateResult.Success();
    }

    //endregion

}
