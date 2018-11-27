package com.baizhi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Users {
    private int id;
    private String name;
    private String phonenum;
    private String password;
    private String salt;
    private String dharmaName;
    private String province;
    private String city;
    private String sex;
    private String sign;
    private String headPic;
    private int status;
    private Date date;


}
