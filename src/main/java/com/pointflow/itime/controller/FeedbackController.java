package com.pointflow.itime.controller;

import com.pointflow.itime.domain.Feedback;
import com.pointflow.itime.domain.Pictures;
import com.pointflow.itime.domain.User;
import com.pointflow.itime.service.FeedbackService;
import com.pointflow.itime.service.PictureService;
import com.pointflow.itime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-04
 * Time: 17:05
 **/
@Controller
@RequestMapping("/problem")
public class FeedbackController {

    @Autowired
    private UserService userService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private FeedbackService feedbackService;


    @ResponseBody
    @PostMapping(value = "/upload")
    public Map<String,Object> upload(HttpServletRequest request, @RequestParam String phoneNumber,
                                     @RequestParam String advice){
            Map<String,Object> map = new HashMap<>();
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            commonsMultipartResolver.setDefaultEncoding("utf-8");

            if (commonsMultipartResolver.isMultipart(request)){

                User user = userService.findByPhoneNumber(phoneNumber);
                Long timestamp = new Date().getTime();
                //添加反馈记录
                Feedback fd = new Feedback();
                fd.setUserId(user.getId());
                fd.setDescription(advice);
                fd.setPictureGroup(timestamp);
                feedbackService.feedback(fd);
                MultipartHttpServletRequest mulReq = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = mulReq.getFileMap();
                // 保存图片
                for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
                    MultipartFile file = entry.getValue();
                    String filePath = "/home/feedbacks/imgs/"+phoneNumber+"/"+timestamp+ "/" +file.getOriginalFilename();
                    File distFile = new File(filePath);
                    try {
                        if (!distFile.getParentFile().exists())
                            distFile.getParentFile().mkdirs();
                        file.transferTo(distFile);//把文件写到服务器
                        Pictures picture = new Pictures();
                        picture.setGroupId(timestamp);
                        picture.setUri(filePath);
                        picture.setUserId(user.getId());
                        pictureService.addPicture(picture);

                    } catch (IllegalStateException | IOException e1) {
                        e1.printStackTrace();
                    }

                }
                map.put("res","success");
            }

            return map;

    }




}
