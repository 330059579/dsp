package com.tuanzhang.ad.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    @Override
    public void  configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();

        //实现将java对象转换为json对象
        converters.add(new MappingJackson2CborHttpMessageConverter());
    }

}
