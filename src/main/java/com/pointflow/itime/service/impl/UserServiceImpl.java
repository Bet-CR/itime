package com.pointflow.itime.service.impl;

import com.pointflow.itime.domain.Idots;
import com.pointflow.itime.domain.User;
import com.pointflow.itime.mapper.UserMapper;
import com.pointflow.itime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: chen
 * Date: 2020-06-18
 * Time: 16:56
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userMapper.getUserByPhone(phoneNumber);
    }

    @Override
    public int deleteUserByPhoneNumber(String phoneNumber) {
        return userMapper.deleteUserByPhone(phoneNumber);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public List<Idots> registeredUsers() {
        return userMapper.registeredUsers();
    }
}
