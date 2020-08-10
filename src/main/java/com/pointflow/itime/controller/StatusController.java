package com.pointflow.itime.controller;

import com.pointflow.itime.domain.Ddots;
import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.Status;
import com.pointflow.itime.domain.User;
import com.pointflow.itime.service.StatusService;
import com.pointflow.itime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-29
 * Time: 20:41
 **/
@Controller
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @Autowired
    private UserService userService;

    /**
     * 添加服务记录
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     *             --
     */
    @ResponseBody
    @PostMapping(value = "/addStatus", headers = "Accept=application/json")
    public Map<String,Object> addStatus(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        Date loginTime = new Date(requestMap.get("loginTime").toString());
        Double duration = Double.parseDouble(requestMap.get("duration").toString());
        User user = userService.findByPhoneNumber(phone);
        if(user == null){
            map.put("msg", "unRegistered");
        }else{
            Status status = new Status();
            status.setUserId(user.getId());
            status.setLoginTime(loginTime);
            status.setDuration(duration);
            int flag = statusService.addStatus(status);
            if(flag == 1){
                map.put("msg", "success");
            }else{
                map.put("msg", "fail");
            }
        }
        return map;
    }

    @RequestMapping("/all")
    public String findAll(Model model){
        List<Status> statuses = statusService.getAll();
        model.addAttribute("status",statuses);
        return "loginRecords";
    }

    //服务次数统计
    @RequestMapping("/serviceTimes")
    public String serviceTimes(Model model){
        List<Idots> datas = statusService.serviceTimes();

        model.addAttribute("datas",datas);
        return  "serviceTimes";

    }

    //服务时长统计
    @RequestMapping("/serviceTime")
    public String serviceTime(Model model){
        List<Ddots> datas = statusService.serviceTime();
        model.addAttribute("datas",datas);
        return  "serviceTime";
    }

    //活跃用户统计
    @RequestMapping(value = "/activeUsers")
    public String activeUsers(Model model){
        List<Idots> datas = statusService.activeUsers();
        ArrayList<String> date = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        for(Idots item : datas){
            date.add(item.getLabel());
            nums.add(item.getValue());
        }
        model.addAttribute("dates", date);
        model.addAttribute("nums",nums);
        return "activeUsers";
    }
}
