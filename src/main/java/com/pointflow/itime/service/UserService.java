package com.pointflow.itime.service;

import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-18
 * Time: 16:50
 **/
public interface UserService {

    List<User> findAll();

    User findByPhoneNumber(String phoneNumber);

    int deleteUserByPhoneNumber(String phoneNumber);

    int addUser(User user);

    int updateUser(User user);

    List<Idots> registeredUsers();
}
