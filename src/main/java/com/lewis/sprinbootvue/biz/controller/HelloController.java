package com.lewis.sprinbootvue.biz.controller;

import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;
import com.lewis.sprinbootvue.biz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
}
