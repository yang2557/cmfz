package com.baizhi.dao;

import com.baizhi.entity.Pricture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrictureDao {
    public List<Pricture> selectPricture(@Param("start") int start, @Param("rows") int rows);

    int getCount();

    void updatePricture(Pricture pricture);

    void insert(Pricture pricture);

    void delete(int id);
}
