package com.baizhi.entity;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private int m_id;
    private int parent_id;
    private String m_title;
    private String iconCls;
    private String url;
    private List<Menu> list;
}
