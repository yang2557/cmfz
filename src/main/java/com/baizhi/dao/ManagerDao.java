package com.baizhi.dao;

import com.baizhi.entity.Manager;
import org.apache.ibatis.annotations.Param;

public interface ManagerDao {

    public Manager login(@Param("name") String name, @Param("password") String password);
}
