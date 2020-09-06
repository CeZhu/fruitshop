package com.thirdshop.po;

import java.io.Serializable;

public class Manage implements Serializable {
    /**主键*/
    private Integer id;
    /**用户名*/
    private String username;
    /**密码*/
    private String password;
    /**真实姓名*/
    private String realName;

    public Manage(Integer id, String username, String password, String realName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Manage() {
    }
}
