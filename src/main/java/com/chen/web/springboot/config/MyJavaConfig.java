package com.chen.web.springboot.config;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.chen.web.springboot.interceptor.ListenerTest;
import com.chen.web.springboot.interceptor.ServletTest;
import com.chen.web.springboot.interceptor.TimeFilter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Servlet;
import javax.servlet.ServletRegistration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyJavaConfig {
    /**
     * 整合fastjson
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //创建FastJson信息转换对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        //创建Fastjosn对象并设定序列化规则
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNonStringKeyAsString);

        // 中文乱码解决方案
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);//设定json格式且编码为UTF-8
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);

        //规则赋予转换对象
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

//        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;

        return new HttpMessageConverters(fastJsonHttpMessageConverter);

    }

    /**
     * 自定义servlet注册
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistration(){
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new ServletTest(),"/testServlet");
        return registration;
    }

    /**
     * 自定义过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean(
                new TimeFilter());
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterRegistration.setUrlPatterns(urls);


        return  filterRegistration;
    }

    /**
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean listenerRegistration(){
        ServletListenerRegistrationBean listenerRegistration =
                new ServletListenerRegistrationBean(new ListenerTest());
        return listenerRegistration;
    }

}
