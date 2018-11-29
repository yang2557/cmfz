package com.baizhi.service;

import com.baizhi.entity.Pricture;

import java.util.Map;

public interface PrictureService {
    public Map selectPricture(int page, int rows);

    void updatePricture(Pricture pricture);

    void insert(Pricture pricture);

    void delete(int id);
}
