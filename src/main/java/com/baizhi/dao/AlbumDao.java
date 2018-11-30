package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    public List<Album> selectBypage(@Param("start") int start, @Param("rows") int rows);

    public int getCount();


    void insert(Album album);
}
