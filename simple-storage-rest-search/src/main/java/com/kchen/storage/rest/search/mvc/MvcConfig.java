package com.kchen.storage.rest.search.mvc;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;

@Configuration
@ConditionalOnClass({FastJsonHttpMessageConverter4.class})
public class MvcConfig {

    @Bean
    public HttpMessageConverters customMessageConverters() {
        FastJsonHttpMessageConverter4 messageConverter = new FastJsonHttpMessageConverter4();
        ArrayList<MediaType> mediaTypes = Lists.newArrayList(
                MediaType.parseMediaType("application/json;charset=UTF-8"),
                MediaType.parseMediaType("text/html;charset=UTF-8"));
        messageConverter.setSupportedMediaTypes(mediaTypes);

        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect
        );
        messageConverter.setFastJsonConfig(fastJsonConfig);

        // by using the ctor below, FastjsonConverter will be put in the index 0 position
        return new HttpMessageConverters(true, Lists.newArrayList(messageConverter));
    }
}
