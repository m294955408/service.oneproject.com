package com.shuma.oneproject.controller;

import com.shuma.oneproject.common.PageList;
import com.shuma.oneproject.common.errorcode.ErrorCode;
import com.shuma.oneproject.common.result.JsonUtil;
import com.shuma.oneproject.common.result.ListOperateResult;
import com.shuma.oneproject.common.result.OperateResult;
import com.shuma.oneproject.web.validator.Error;
import com.shuma.oneproject.web.validator.ParamValidatorErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/7
 * @desc
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    /**
     * 处理参数校验异常
     *  返回参数校验失败JSON
     * @param e
     * @return
     * @throws IOException
     */
    @ExceptionHandler({ParamValidatorErrorException.class})
    public @ResponseBody String paramValidatorErrorException(ParamValidatorErrorException e) throws IOException {
        return JsonUtil.toJson(ListOperateResult.Fail(ErrorCode.PARAM_VALID_FAIL, e.getErrors()));
    }

}
