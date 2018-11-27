package com.baizhi.conltroller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/selectAll")
    public @ResponseBody
    List<Menu> selectAll() {
        //List<Menu> list=;
        // System.out.println(list);
//        session.setAttribute("list",list);
        return menuService.selectAll();
    }
}
