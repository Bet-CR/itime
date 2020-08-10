package com.pointflow.itime.mapper;

import com.pointflow.itime.domain.Datas;
import com.pointflow.itime.domain.Idots;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-17
 * Time: 21:32
 **/
@Repository
public interface DatasMapper {

    //获取某一次计时的详细数据
    @Select("select * from datas where user_id=#{userId} and saveTimestamp = #{saveTimestamp} ")
    Datas[] getDatas(Datas datas);

    @Delete("delete from datas where user_id=#{userId} and saveTimestamp = #{saveTimestamp} ")
    int deleteDatas(Datas datas);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into datas(user_id, saveTimestamp, data) values(#{userId},#{saveTimestamp}, #{data})")
    int addData(Datas datas);

    //获取用户历史计时列表
    @Select("select saveTimestamp as label, count(*) as value from datas where user_id=#{userId} group by saveTimestamp order by saveTimestamp desc")
    List<Idots> getData(Datas datas);



    //获取日活跃用户数
    @Select("select FROM_UNIXTIME(saveTimestamp/1000, '%Y-%m-%d' ) as label, count(distinct user_id) as value from datas group by label")
    List<Idots> activeUsers();



}
