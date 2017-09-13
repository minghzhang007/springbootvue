package com.lewis.sprinbootvue.biz.service;


import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;

import java.util.List;

public interface UserService {

    List<User> getAllUsers(UserQueryObject queryObject);
}
