package com.baizhi.dao;

import com.baizhi.entity.EreaDTO;

import java.util.List;

public interface EreaDTODao {
    List<EreaDTO> selectBysex1();

    List<EreaDTO> selectBysex2();
}
