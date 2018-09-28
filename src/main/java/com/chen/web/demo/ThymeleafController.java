package com.chen.web.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
//@RestController
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @RequestMapping("/helloWorld")
    public String hello(Map<String,Object> map){
        map.put("msg","Hello Thymeleaf");
        return "hello";
    }
}
