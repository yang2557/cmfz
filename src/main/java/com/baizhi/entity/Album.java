package com.baizhi.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Album {
    private int id;
    private String title;
    private String coverlmg;
    private int count;
    private Double score;
    private String author;
    private String broadCast;
    private String brief;
    private Date publishDate;
    private List<Chapter> children;

}
