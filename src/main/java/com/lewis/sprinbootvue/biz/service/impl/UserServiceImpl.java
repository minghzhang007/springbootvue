package com.lewis.sprinbootvue.biz.service.impl;

import com.lewis.sprinbootvue.biz.mybatis.dao.sharding_0.UserMapper;
import com.lewis.sprinbootvue.biz.mybatis.dao.snailReader.BookArticleMapper;
import com.lewis.sprinbootvue.biz.mybatis.entity.BookAriticle;
import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;
import com.lewis.sprinbootvue.biz.service.UserService;
import com.lewis.sprinbootvue.biz.utils.page.AbstractPageTemplate;
import com.lewis.sprinbootvue.biz.utils.page.PageList;
import com.lewis.sprinbootvue.biz.utils.page.Paginator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private BookArticleMapper bookArticleMapper;

    @Override
    public PageList<User> getUsersByPage(final UserQueryObject queryObject, Paginator paginator) {

        PageList<User> pageList = getPageList(queryObject,paginator);
        List<BookAriticle> bookAriticles = bookArticleMapper.queryAllBookArticle();
        System.out.println("got bookAriticles:"+bookAriticles.toString());
        return pageList;
    }

    //@Transactional
    public PageList<User> getPageList(final UserQueryObject queryObject, Paginator paginator){
        return new AbstractPageTemplate<User>() {
            @Override
            protected List<User> queryItems() {
                return userMapper.getAllUser(queryObject);
            }
        }.getItemsByPage(paginator);
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
