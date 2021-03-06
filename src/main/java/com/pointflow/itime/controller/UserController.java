package com.pointflow.itime.controller;

import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.User;
import com.pointflow.itime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-15
 * Time: 19:59
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 登录请求
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     *             --password
     */
    @ResponseBody
    @PostMapping(value = "/login", headers = "Accept=application/json")
    public Map<String,Object> login(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        String password = requestMap.get("password").toString();
        User user = userService.findByPhoneNumber(phone);
        if(user == null){
            map.put("msg", "unregistered");
        }else if(password.equals(user.getPassword())){
            map.put("msg","success");
        }else if(!password.equals(user.getPassword())){
            map.put("msg","fail");
        }else{
            map.put("msg", "unknown");
        }
        return map;
    }


    /**
     * 注册
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     *             --password
     */
    @ResponseBody
    @PostMapping(value = "/register", headers = "Accept=application/json")
    public Map<String,Object> register(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        String password = requestMap.get("password").toString();
        User user = userService.findByPhoneNumber(phone);
        if(user == null){
            user = new User();
            user.setPhone(phone);
            user.setPassword(password);
            user.setRegisterTime(new Date());
            userService.addUser(user);
        }
        map.put("msg", "success");
        return map;
    }


    /**
     * 是否已经注册
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     */
    @ResponseBody
    @PostMapping(value = "/isRegistered", headers = "Accept=application/json")
    public Map<String,Object> isRegistered(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        User user = userService.findByPhoneNumber(phone);
        if(user == null){
            map.put("msg", "unRegistered");
        }else{
            map.put("msg", "registered");
        }
        return map;
    }


    /**
     * 反馈意见或建议
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     *             --tip
     */
    @ResponseBody
    @PostMapping(value = "/update", headers = "Accept=application/json")
    public Map<String,Object> addTip(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        User user = userService.findByPhoneNumber(phone);
        String password = requestMap.get("password")==null?user.getPassword():requestMap.get("password").toString();
        user.setPassword(password);
        userService.updateUser(user);
        map.put("msg", "success");
        return map;
    }


    /**
     后台统计需求
     */
    @RequestMapping("/all")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "list";
    }
    @RequestMapping("/registerStatistics")
    public String registerStatistics(Model model){
       List<Idots> datas = userService.registeredUsers();
       ArrayList<String> date = new ArrayList<>();
       ArrayList<Integer> nums = new ArrayList<>();
       for(Idots item : datas){
           date.add(item.getLabel());
           nums.add(item.getValue());
       }
       model.addAttribute("dates", date);
       model.addAttribute("nums",nums);
       return "Registration";
    }

}
