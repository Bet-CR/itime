package com.pointflow.itime.mapper;

import com.pointflow.itime.domain.Datas;
import com.pointflow.itime.domain.Pictures;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-05
 * Time: 18:24
 **/
@Repository
public interface PicturesMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into pictures(user_id, group_id, uri) values(#{userId},#{groupId}, #{uri})")
    int addPicture(Pictures picture);


    @Select("select uri from pictures where user_id=#{userId} and group_id = #{groupId} ")
    String[] getUris(Pictures picture);
}
