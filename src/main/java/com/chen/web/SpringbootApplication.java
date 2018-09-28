package com.chen.web;

import com.chen.web.itfc.EncodingConvertor;
import com.chenc.Cache;
import com.chenc.CacheConfirguration;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Map;

//@Import(CacheConfirguration.class)
//第三方jar资源问及爱你 自动配置解决方案  @Import 或在spring.factories中配置
@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer
        implements ServletContextInitializer
{


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootApplication.class, args);
        Map<String,EncodingConvertor> map = context.getBeansOfType(EncodingConvertor.class);


//        CacheConfirguration conf = context.getBean(CacheConfirguration.class);
//        System.out.println(conf);
//
//        Cache Cache = context.getBean(Cache.class);
        System.out.println(map);
    }















    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // 配置 Servlet
//        servletContext.addServlet("servletTest",new ServletTest())
//                .addMapping("/servletTest");
//        // 配置过滤器
//        servletContext.addFilter("timeFilter",new TimeFilter())
//                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
//        // 配置监听器
//        servletContext.addListener(new ListenerTest());
//
//        SpringApplication.run(DemoApplication.class, args);
    }
}
