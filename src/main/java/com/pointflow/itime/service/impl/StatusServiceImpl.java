package com.pointflow.itime.service.impl;

import com.pointflow.itime.domain.Ddots;
import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.Status;
import com.pointflow.itime.mapper.StatusMapper;
import com.pointflow.itime.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-29
 * Time: 20:46
 **/
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusMapper statusMapper;

    @Override
    public int addStatus(Status status) {
        return statusMapper.addStatus(status);
    }


    @Override
    public List<Status> getAll() {
        return statusMapper.getAll();
    }

    @Override
    public List<Idots> serviceTimes() {
        return statusMapper.serviceTimes();
    }

    @Override
    public List<Ddots> serviceTime() {
        return statusMapper.serviceTime();
    }

    @Override
    public List<Idots> activeUsers() {
        return statusMapper.activeUsers();
    }

}
