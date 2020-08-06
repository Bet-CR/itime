package com.pointflow.itime.service.impl;

import com.pointflow.itime.domain.Datas;
import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.mapper.DatasMapper;
import com.pointflow.itime.service.DatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-19
 * Time: 15:45
 **/
@Service
public class DatasServiceImpl implements DatasService {

    @Autowired
    DatasMapper datasMapper;
    @Override
    public int addData(Datas datas) {
        return datasMapper.addData(datas);
    }

    @Override
    public int deleteDatas(Datas datas) {
        return datasMapper.deleteDatas(datas);
    }

    @Override
    public List getDatas(Datas datas) {
        Datas[] data = datasMapper.getDatas(datas);
        List res = new ArrayList<Double>();
        for(Datas d: data){
            res.add(d.getData());
        }
        return res;
    }

    @Override
    public List<Idots> getData(Datas datas) {
        return datasMapper.getData(datas);
    }


}
