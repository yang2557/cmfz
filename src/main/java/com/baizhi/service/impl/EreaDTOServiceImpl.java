package com.baizhi.service.impl;

import com.baizhi.dao.EreaDTODao;
import com.baizhi.entity.EreaDTO;
import com.baizhi.service.EreaDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EreaDTOServiceImpl implements EreaDTOService {
    @Autowired
    private EreaDTODao ereaDTODao;

    @Override
    public Map selectBysex() {
        Map map = new HashMap();
        List<EreaDTO> sex1 = ereaDTODao.selectBysex1();
        System.out.println(sex1 + "&&&&&&&&&&&&&&&&&&&&&&&&&&");
        List<EreaDTO> sex2 = ereaDTODao.selectBysex2();
        System.out.println(sex2 + "*********************************");
        map.put("sex1", sex1);
        map.put("sex2", sex2);
        // System.out.println(map+"************************");
        return map;
    }
}
