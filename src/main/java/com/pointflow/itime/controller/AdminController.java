package com.pointflow.itime.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-07-05
 * Time: 17:41
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @PostMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password, Map<String, Object> map){

        if(username.equals("admin")&&password.equals("123456"))
            return "redirect:/main.html";
        map.put("msg", "用户名或密码错误！");
        return "index";
    }


}
