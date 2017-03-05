package com.kchen.storage.rest.config.mvc;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kchen on 2017/2/28.
 * custom mvc config
 */
@Configuration
@ConditionalOnClass({FastJsonHttpMessageConverter4.class})
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
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
        converters.add(0, messageConverter);
        super.extendMessageConverters(converters);
    }
}
