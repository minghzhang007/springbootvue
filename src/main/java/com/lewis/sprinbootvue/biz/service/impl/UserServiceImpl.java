package com.lewis.sprinbootvue.biz.service.impl;

import com.lewis.sprinbootvue.biz.mybatis.dao.UserMapper;
import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;
import com.lewis.sprinbootvue.biz.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers(UserQueryObject queryObject) {
        List<User> allUser = userMapper.getAllUser(queryObject);
        return allUser;
    }

    @Override
    public boolean insertUsers(List<User> users) {
        for (User user : users) {
            userMapper.insertUser(user);
        }
        return true;
    }
}
