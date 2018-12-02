package com.baizhi.service.impl;

import com.baizhi.dao.UsersDao;
import com.baizhi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;

    @Override
    public int selectCount1() {
        return usersDao.selectCount1();
    }

    @Override
    public int selectCount2() {
        return usersDao.selectCount2();
    }

    @Override
    public int selectCount3() {
        return usersDao.selectCount3();
    }

    @Override
    public int selectCount4() {
        return usersDao.selectCount4();
    }

    @Override
    public int selectCount5() {
        return usersDao.selectCount5();
    }
}
