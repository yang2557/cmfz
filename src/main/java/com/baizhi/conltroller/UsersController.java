package com.baizhi.conltroller;

import com.baizhi.service.AlbumService;
import com.baizhi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;


    @RequestMapping("/selectCount")
    public @ResponseBody
    Map selectCount() {
        Map map = new HashMap();
        int i = usersService.selectCount1();
        int i1 = usersService.selectCount2();
        int i2 = usersService.selectCount3();

        int i3 = usersService.selectCount4();
        int i4 = usersService.selectCount5();
        int[] data = {i, i1, i2, i3, i4};
        map.put("data", data);
        String[] type = {"7天", "15天", "30天", "90天", "半年"};
        map.put("type", type);
        return map;
    }


}
