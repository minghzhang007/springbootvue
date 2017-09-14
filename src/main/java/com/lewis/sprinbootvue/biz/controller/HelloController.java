package com.lewis.sprinbootvue.biz.controller;

import com.lewis.sprinbootvue.biz.domain.test.StaticRecord;
import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;
import com.lewis.sprinbootvue.biz.service.UserService;
import com.lewis.sprinbootvue.biz.utils.page.PageList;
import com.lewis.sprinbootvue.biz.utils.page.Paginator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HelloController {

    @Resource
    private UserService userService;

    @GetMapping("/user")
    public String user() {
        return "user/userList";
    }

    @PostMapping("/userPage")
    @ResponseBody
    public PageList<User> usersByPage(UserQueryObject userQuery, Paginator paginator) {
        PageList<User> allUsers = userService.getUsersByPage(userQuery,paginator);
        return allUsers;
    }

    @PostMapping("/allUser")
    @ResponseBody
    public List<User> allUsers(UserQueryObject userQuery){
        return userService.getAllUsers(userQuery);
    }

    @GetMapping("/addUsers")
    @ResponseBody
    public String addUsers(){
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            User user = new User(null,"张三"+i,"唱歌"+i,new Date().getTime());
            users.add(user);
        }
        userService.insertUsers(users);
        String ok ="{\"ok\":\"success\"}";
        return ok;
    }

    @GetMapping("/staticRecords")
    @ResponseBody
    public List<StaticRecord> getStaticRecords(){

        return StaticRecord.getStaticRecords();
    }

    @GetMapping("/toRecord")
    public String toRecord(){
        return "table-row-combine";
    }
}
