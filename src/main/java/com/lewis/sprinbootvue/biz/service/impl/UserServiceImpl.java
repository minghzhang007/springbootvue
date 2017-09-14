package com.lewis.sprinbootvue.biz.service.impl;

import com.lewis.sprinbootvue.biz.mybatis.dao.UserMapper;
import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;
import com.lewis.sprinbootvue.biz.service.UserService;
import com.lewis.sprinbootvue.biz.utils.page.AbstractPageTemplate;
import com.lewis.sprinbootvue.biz.utils.page.PageList;
import com.lewis.sprinbootvue.biz.utils.page.Paginator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageList<User> getUsersByPage(final UserQueryObject queryObject, Paginator paginator) {
        PageList<User> pageList = new AbstractPageTemplate<User>() {
            @Override
            protected List<User> queryItems() {
                return userMapper.getAllUser(queryObject);
            }
        }.getItemsByPage(paginator);
        return pageList;
    }

    @Override
    public List<User> getAllUsers(UserQueryObject queryObject) {
        return userMapper.getAllUser(queryObject);
    }

    @Override
    public boolean insertUsers(List<User> users) {
        for (User user : users) {
            userMapper.insertUser(user);
        }
        return true;
    }
}
