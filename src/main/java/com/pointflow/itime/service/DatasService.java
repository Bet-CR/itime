package com.pointflow.itime.service;

import com.pointflow.itime.domain.Datas;
import com.pointflow.itime.domain.Idots;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-19
 * Time: 15:40
 **/
public interface DatasService {

    int addData(Datas datas);

    int deleteDatas(Datas datas);

    //获取用户某一次的计时数据集
    List getDatas(Datas datas);

    //获取用户历史使用记录
    List<Idots> getData(Datas datas);


    List<Idots> activeUsers();

}
