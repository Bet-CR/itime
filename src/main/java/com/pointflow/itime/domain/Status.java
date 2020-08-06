package com.pointflow.itime.domain;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-17
 * Time: 21:25
 **/
public class Status {
    private Integer id;
    private Integer userId;
    private Date loginTime;
    private Double duration;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
}
