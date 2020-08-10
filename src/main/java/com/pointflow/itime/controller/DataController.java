package com.pointflow.itime.controller;

import com.pointflow.itime.domain.Datas;
import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.Record;
import com.pointflow.itime.domain.User;
import com.pointflow.itime.service.DatasService;
import com.pointflow.itime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-19
 * Time: 15:58
 **/
@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    DatasService datasService;
    @Autowired
    UserService userService;

    /**
     * 计时数据上传
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     *             --data
     */
    @ResponseBody
    @PostMapping(value = "/upload", headers = "Accept=application/json")
    public Map<String,Object> upload(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        Long savaTimestamp = (Long)requestMap.get("saveTimestamp");
        int flag = (int)requestMap.get("flag");
        User user = userService.findByPhoneNumber(phone);
        if(user != null&&flag == 1){
            int userId = user.getId();
            List list = (List) requestMap.get("recordData");
            for (int i = 0; i<list.size(); i++){
                Datas data = new Datas();
                data.setUserId(userId);
                data.setSaveTimestamp(savaTimestamp);
                data.setData((int)list.get(i));
                datasService.addData(data);
            }
            map.put("msg","success");

        }else{
            map.put("msg","fail");
            map.put("flag", 0);
        }
        return map;
    }

    /**
     * 计时数据加载
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     *             --stamp
     */
    @ResponseBody
    @PostMapping(value = "/load", headers = "Accept=application/json")
    public Map<String,Object> load(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        Long saveTimeStamp = (Long)requestMap.get("saveTimestamp");
        User user = userService.findByPhoneNumber(phone);
        if(user != null){
            Datas data = new Datas();
            data.setSaveTimestamp(saveTimeStamp);
            data.setUserId(user.getId());
            List list = datasService.getDatas(data);
            map.put("msg", "success");
            map.put("data",list);
        }else{
            map.put("msg","fail");
        }
        return map;
    }

    /**
     * 计时记录加载
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     */
    @ResponseBody
    @PostMapping(value = "/loadRecord", headers = "Accept=application/json")
    public Map<String,Object> loadRecord(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        User user = userService.findByPhoneNumber(phone);
        if(user != null){
            Datas data = new Datas();
            data.setUserId(user.getId());
            List<Idots> records = datasService.getData(data);
            int limit = (int)requestMap.get("limit");
            int offset = (int)requestMap.get("offset");
            limit = Math.min(offset+limit, records.size());
            Datas d = new Datas();
            ArrayList<Record> res = new ArrayList<>();
            for(int i = offset;i<limit;i++){
               Idots id =  records.get(i);
               Long saveTimeStamp = Long.parseLong(id.getLabel());
               d.setSaveTimestamp(saveTimeStamp);
               d.setUserId(user.getId());
               List list = datasService.getDatas(d);
               Record temp = new Record();
               temp.setSaveTimestamp(saveTimeStamp);
               temp.setRecordData(list);
               res.add(temp);
            }
            map.put("msg", "success");
            map.put("data",res);
        }else{
            map.put("msg","fail");
        }
        return map;
    }
    /**
     * 计时数据删除
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     *             --phoneNumber
     *             --stamp
     */
    @ResponseBody
    @PostMapping(value = "/delete", headers = "Accept=application/json")
    public Map<String,Object> delete(@RequestBody Map<String,Object> requestMap){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        Long saveTimeStamp = (Long)requestMap.get("saveTimestamp");
        User user = userService.findByPhoneNumber(phone);
        if(user != null){
            Datas data = new Datas();
            data.setSaveTimestamp(saveTimeStamp);
            data.setUserId(user.getId());
            datasService.deleteDatas(data);
            map.put("msg", "success");
        }else{
            map.put("msg","fail");
        }
        return map;
    }

    //活跃用户统计
    @RequestMapping(value = "/activeUsers")
    public String activeUsers(Model model){
        List<Idots> datas = datasService.activeUsers();
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
