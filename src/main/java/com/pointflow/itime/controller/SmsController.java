package com.pointflow.itime.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.pointflow.itime.service.RedisService;
import com.pointflow.itime.untils.CodeUtil;
import com.pointflow.itime.untils.SmsTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-14
 * Time: 23:57
 **/
@RestController
@RequestMapping("/sms")
public class SmsController {
    /***
     * 注入redis模版
     */
    @Autowired
    private RedisService redisService;

    private  String tokenId="TOKEN-USER-";


    /**
     * 发送短信
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     */
    @PostMapping(value = "/sendSms", headers = "Accept=application/json")
    public Map<String,Object> sendSms(@RequestBody Map<String,Object> requestMap, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();
        // 调用工具栏中生成验证码方法（指定长度的随机数）
        String code = CodeUtil.generateVerifyCode(4);
        //填充验证码
        String TemplateParam = "{\"code\":\""+code+"\"}";
        SendSmsResponse response = null;//传入手机号码及短信模板中的验证码占位符
        try {
            response = SmsTool.sendSms(phone,TemplateParam);
            if( response.getCode().equals("OK")) {
                map.put("verifyCode",code);
                map.put("phone",phone);
                map.put("msg","success");
                //验证码绑定手机号并存储到redis
                redisService.set(tokenId+phone,code);
                redisService.expire(tokenId + phone,302);//调用reids工具类中存储方法设置超时时间
            }else{
                map.put("msg","fail");
            }
        } catch (ClientException e) {
            map.put("msg","error");
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 注册验证
     * @RequestMapping 拦截请求，指定请求类型：POST
     * @RequestBody 接受前台传入的json数据 接受类型为Map
     */
    @PostMapping(value = "/validateNum", headers = "Accept=application/json")
    public Map<String, Object> validateNum(@RequestBody Map<String,Object> requestMap) {

        Map<String,Object> map = new HashMap<>();
        String phone = requestMap.get("phoneNumber").toString().trim();//获取注册手机号码
        String verifyCode = requestMap.get("verifyCode").toString();//获取手机验证码

        //首先比对验证码是否失效
        String redisauthcode= redisService.get(tokenId+phone); //传入tonkenId返回redis中的value
        if(StringUtils.isEmpty(redisauthcode)){
            //如果未取到则过期验证码已失效
            map.put("result","fail");
        }else  if(!"".equals(redisauthcode)&&!verifyCode.equals(redisauthcode)){
            //验证码错误
            map.put("result","error");
        }else{
            //用户注册成功
            map.put("result","success");
        }
        return map;
    }

}
