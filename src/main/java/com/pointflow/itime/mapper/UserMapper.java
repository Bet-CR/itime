package com.pointflow.itime.mapper;

import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.User;
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
public interface UserMapper {

    @Select("select id, phone, AES_DECRYPT(from_base64(password),'jkl;itime1234++==') as password, register_time from user")
    List<User> findAll();

    @Select("select id, phone, AES_DECRYPT(from_base64(password),'jkl;itime1234++==') as password, register_time from user where phone=#{phone}")
    User getUserByPhone(String phone);

    @Delete("delete from user where phone=#{phone}")
    int deleteUserByPhone(String phone);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(phone, password, register_time) values(#{phone},to_base64(AES_ENCRYPT(#{password},'jkl;itime1234++==')), #{registerTime})")
    int addUser(User user);

    @Update("update user set password = to_base64(AES_ENCRYPT(#{password},'jkl;itime1234++==')) where id=#{id}")
    int updateUser(User user);

    //按天统计注册用户
    @Select("select date_format(register_time, '%Y-%m-%d') as label, count(id) as value from user group by label" )
    List<Idots> registeredUsers();

}
