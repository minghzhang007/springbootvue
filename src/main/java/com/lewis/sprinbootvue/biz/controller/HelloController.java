package com.lewis.sprinbootvue.biz.controller;

import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;
import com.lewis.sprinbootvue.biz.service.UserService;
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

    @PostMapping("/user")
    @ResponseBody
    public List<User> users(UserQueryObject userQuery) {
        List<User> allUsers = userService.getAllUsers(userQuery);
        /*for (int i = 1; i <= 15; i++) {
            User user = new User(i,"lewis0077_"+i,"sing_"+i,new Date());
            allUsers.add(user);
        }*/
        return allUsers;
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
}
