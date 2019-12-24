package com.tuanzhang.ad.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuanzhang.ad.annotation.IgnoreResponseAdvice;
import com.tuanzhang.ad.vo.BaseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

@RestControllerAdvice
public class CommonResponseDataService implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        //若类上有IgnoreResponseAdvice注解不拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }

        //若方法上有IgnoreResponseAdvice注解不拦截
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        BaseResult<Object> result = new BaseResult<>(0, "");
        if (null == o) {
            return result;
        }

        if (o instanceof BaseResult) {
            result = (BaseResult<Object>) o;
            return result;
        }

        result.setData(o);
        return result;
    }
}
