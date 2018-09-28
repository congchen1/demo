package com.chen.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

//@Configuration
public class WebConfig {
    @Value("${ds.userName}")
    private String userName;
    @Autowired
    private Environment environment;
    public void show(){
        String password = environment.getProperty("ds.password");
        System.out.println("ds.userName:"+this.userName+" ;ds.password:"+password);
    }

}
