package com.lewis.sprinbootvue.biz.controller;

import com.lewis.sprinbootvue.biz.domain.test.StaticRecord;
import com.lewis.sprinbootvue.biz.mybatis.entity.User;
import com.lewis.sprinbootvue.biz.mybatis.queryObject.UserQueryObject;
import com.lewis.sprinbootvue.biz.service.UserService;
import com.lewis.sprinbootvue.biz.utils.excel.AbstractExportExcelTemplate;
import com.lewis.sprinbootvue.biz.utils.page.PageList;
import com.lewis.sprinbootvue.biz.utils.page.Paginator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
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
        PageList<User> allUsers = userService.getUsersByPage(userQuery, paginator);
        return allUsers;
    }

    @PostMapping("/allUser")
    @ResponseBody
    public List<User> allUsers(UserQueryObject userQuery) {
        return userService.getAllUsers(userQuery);
    }

    @GetMapping("/addUsers")
    @ResponseBody
    public String addUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            User user = new User(null, "张三" + i, "唱歌" + i, new Date().getTime());
            users.add(user);
        }
        userService.insertUsers(users);
        String ok = "{\"ok\":\"success\"}";
        return ok;
    }

    @PostMapping("/toAddUser")
    @ResponseBody
    public User toAddUser(@RequestBody User user) {
        System.out.println(user);
        return user;
    }

    @GetMapping("/addUser")
    public String addUser(@RequestParam("name") String name,@RequestParam("hobby") String hobby){
        User user = new User();
        user.setName(name);
        user.setHobby(hobby);
        user.setBirthday(System.currentTimeMillis());
        userService.insertUsers(Arrays.asList(user));
        String ok = "{\"ok\":\"success\"}";
        return "user/userList";
    }

    @GetMapping("/staticRecords")
    @ResponseBody
    public List<StaticRecord> getStaticRecords() {

        return StaticRecord.getStaticRecords();
    }

    @GetMapping("/toRecord")
    public String toRecord() {
        return "table-row-combine";
    }

    @GetMapping("/export")
    public String export(HttpServletRequest request, HttpServletResponse response) {

        //定义表的列名
        final String[] rowsName = new String[]{"年份", "平台", "时长类型", "1月", "2月",
                "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月", "年度累计收入"};

        //定义表的内容
        final List<Object[]> dataList = new ArrayList<Object[]>();
        List<StaticRecord> staticRecords = StaticRecord.getStaticRecords();
        for (int i = 0; i < staticRecords.size(); i++) {
            StaticRecord per = staticRecords.get(i);
            Object[] objs = new Object[rowsName.length];
            objs[0] = per.getYear();
            objs[1] = per.getPlatform();
            objs[2] = per.getTimeType();
            objs[3] = per.getJanuaryMoney();
            objs[4] = per.getFebruaryMoney();
            objs[5] = per.getMarchMoney();
            objs[6] = per.getAprilMoney();
            objs[7] = per.getMayMoney();
            objs[8] = per.getJuneMoney();
            objs[9] = per.getJulyMoney();
            objs[10] = per.getAugustMoney();
            objs[11] = per.getSeptemberMoney();
            objs[12] = per.getOctoberMoney();
            objs[13] = per.getNovemberMoney();
            objs[14] = per.getDecemberMoney();
            objs[15] = per.getAccumulateMoney();
            dataList.add(objs);
        }

        new AbstractExportExcelTemplate(response) {
            @Override
            protected String getFileName() {
                return "sellStatic销售统计";
            }

            @Override
            protected List<Object[]> getDataList() {
                return dataList;
            }

            @Override
            protected String[] getRowNames() {
                return rowsName;
            }

            @Override
            protected String getTitle() {
                return "销售统计一览表";
            }
        }.exportExcel();

        return "table-row-combine";// 返回列表显示
    }
}
