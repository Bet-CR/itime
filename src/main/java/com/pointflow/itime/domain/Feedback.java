package com.pointflow.itime.domain;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-05
 * Time: 18:13
 **/
public class Feedback {
    private Integer id;
    private  Integer userId;
    private String description;
    private Long pictureGroup;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPictureGroup() {
        return pictureGroup;
    }

    public void setPictureGroup(Long pictureGroup) {
        this.pictureGroup = pictureGroup;
    }
}
