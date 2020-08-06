package com.pointflow.itime.domain;


/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-17
 * Time: 21:27
 **/
public class Datas {
    private Integer id;
    private Integer userId;
    private Integer data;
    private Long saveTimestamp;

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

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Long getSaveTimestamp() {
        return saveTimestamp;
    }

    public void setSaveTimestamp(Long saveTimestamp) {
        this.saveTimestamp = saveTimestamp;
    }
}
