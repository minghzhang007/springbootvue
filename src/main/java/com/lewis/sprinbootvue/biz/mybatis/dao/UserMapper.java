package com.lewis.sprinbootvue.biz.mybatis.dao;


import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;

import java.util.List;

public interface UserMapper {

    List<User>  getAllUser(UserQueryObject userQueryObject);
}
