package com.chen.web.springboot.config;

import com.chen.web.itfc.EncodingConvertor;
import com.chen.web.itfc.impl.GBKEncodingConvertor;
import com.chen.web.itfc.impl.UTF8EncodingConvertor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//条件配置测试
@Configuration
public class EncodingConvertorConfiguration {
    @Bean
    public EncodingConvertor createGBKEncodingConvertor(){
        return new GBKEncodingConvertor();
    }

    @Bean
    public EncodingConvertor createUTF8EncodingConvertor(){
        return new UTF8EncodingConvertor();
    }
}
