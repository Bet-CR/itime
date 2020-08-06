package com.pointflow.itime.mapper;

import com.pointflow.itime.domain.Ddots;
import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.Status;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-17
 * Time: 21:33
 **/
@Repository
public interface StatusMapper {


    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into status(user_id, login_time, duration) values(#{userId}, #{loginTime}, #{duration})")
    int addStatus(Status status);

    @Select("select status.id as id, user.phone as phone, login_time, duration from status, user where user_id = user.id order by id")
    List<Status> getAll();

    //按天统计服务次数
    @Select("select date_format(login_time, '%Y-%m-%d') as label, count(id) as value from status group by label" )
    List<Idots> serviceTimes();

    //按天统计服务时长
    @Select("select date_format(login_time, '%Y-%m-%d') as label, SUM(duration) as value from status group by label" )
    List<Ddots> serviceTime();

    //按天统计活跃用户
    @Select("select date_format(login_time, '%Y-%m-%d') as label, count(user_id) as value from status group by label" )
    List<Idots> activeUsers();

}
