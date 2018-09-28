package com.chen.web.controller;

import com.chen.web.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@RequestMapping("fastjson")
@Controller
public class FastJsonController {

    @RequestMapping("/testUser")
    @ResponseBody
    public User test(){
        User user = new User();
        user.setId(1);
        user.setUsername("jack");
        user.setPassword("jack123");
        user.setBirthday(new Date());
        return user;
    }



}
