package com.pointflow.itime.service;

import com.pointflow.itime.domain.Ddots;
import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.Status;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-29
 * Time: 20:43
 **/
public interface StatusService {

    int addStatus(Status status);

    List<Status> getAll();

    //统计服务次数
    List<Idots> serviceTimes();
    //统计服务时长
    List<Ddots> serviceTime();
    //统计活跃用户
    List<Idots> activeUsers();
}
