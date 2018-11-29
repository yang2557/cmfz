package com.baizhi.service.impl;

import com.baizhi.dao.PrictureDao;
import com.baizhi.entity.Pricture;
import com.baizhi.service.PrictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PrictureServiceImpl implements PrictureService {
    @Autowired
    private PrictureDao prictureDao;

    @Override
    public Map selectPricture(int page, int rows) {

        int start = (page - 1) * rows;
        int count = prictureDao.getCount();

        Map map = new HashMap();
        List<Pricture> list = prictureDao.selectPricture(start, rows);
        System.out.println(list);
        map.put("rows", list);
        map.put("total", count);
        return map;
    }

    @Override
    public void updatePricture(Pricture pricture) {
        prictureDao.updatePricture(pricture);
    }

    @Override
    public void insert(Pricture pricture) {
        prictureDao.insert(pricture);
    }

    @Override
    public void delete(int id) {
        prictureDao.delete(id);
    }


}
