package com.lewis.sprinbootvue.biz.service;


import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;
import com.lewis.sprinbootvue.biz.utils.page.PageList;
import com.lewis.sprinbootvue.biz.utils.page.Paginator;

import java.util.List;

public interface UserService {

    PageList<User> getUsersByPage(UserQueryObject queryObject, Paginator paginator);

    List<User> getAllUsers(UserQueryObject queryObject);

    boolean insertUsers(List<User> users);
}
