package com.pointflow.itime.domain;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-04
 * Time: 16:41
 **/
public class Pictures {
    private Integer id;
    private Integer userId;
    private Long groupId;
    private String uri;


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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
