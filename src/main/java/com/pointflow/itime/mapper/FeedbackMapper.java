package com.pointflow.itime.mapper;

import com.pointflow.itime.domain.Feedback;
import com.pointflow.itime.domain.Pictures;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-08-05
 * Time: 19:17
 **/
@Repository
public interface FeedbackMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into feedback(user_id, picture_group, description) values(#{userId},#{pictureGroup}, #{description})")
    int feedback(Feedback fd);

    @Select("select id, user_id, picture_group, description from feedback where user_id=#{userId} and picture_group = #{pictureGroup} ")
    Feedback getFeedback(Feedback fd);

    @Select("select id, user_id, picture_group, description from feedback where user_id=#{userId}")
    Feedback[] getFeedbacks(Feedback fd);

    @Select("select id, user_id, picture_group, description from feedback")
    Feedback[] getAllFeedbacks(Feedback fd);

}
