package com.chen.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    /**
     * 统一处理Exception异常类
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public Map<String,Object> defaultExceptionHandler(Exception e){
        Map<String,Object>  map = new HashMap<>();
        map.put("code",500);
        map.put("msg",e.getMessage());
        return  map;
    }
}
