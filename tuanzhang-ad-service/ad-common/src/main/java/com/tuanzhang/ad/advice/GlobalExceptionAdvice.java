package com.tuanzhang.ad.advice;

import com.tuanzhang.ad.exception.AdException;
import com.tuanzhang.ad.vo.BaseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice{

    @ExceptionHandler(value = AdException.class)
    public BaseResult<String> handlerAdException(HttpServletRequest request, AdException ex) {
        BaseResult<String> result = new BaseResult<>(0, "business error");
        result.setData(ex.getMessage());
        return null;
    }
}
