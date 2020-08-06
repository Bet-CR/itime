package com.pointflow.itime.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-03
 * Time: 20:57
 **/
@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleException(Exception e) {
        Map<String,Object> map = new HashMap<>();
        map.put("msg","fail");
        return map;
    }
}
