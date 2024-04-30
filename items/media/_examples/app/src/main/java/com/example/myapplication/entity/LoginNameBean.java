package com.example.myapplication.entity;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LoginNameBean implements Serializable {

    public String loginName, pwd;

    public LoginNameBean() {

    }

    public LoginNameBean(String name, String pwd) {
        this.loginName = name;
        this.pwd = pwd;
    }

}


