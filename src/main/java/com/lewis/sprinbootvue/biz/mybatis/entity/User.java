package com.lewis.sprinbootvue.biz.mybatis.entity;

import java.util.Date;

public class User {

    private Integer id;

    private String name;

    private String hobby;

    private Long birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public User() {
    }

    public User(Integer id, String name, String hobby, Long birthday) {
        this.id = id;
        this.name = name;
        this.hobby = hobby;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hobby='" + hobby + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
