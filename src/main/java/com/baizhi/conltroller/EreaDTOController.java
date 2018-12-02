package com.baizhi.conltroller;

import com.baizhi.service.EreaDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class EreaDTOController {
    @Autowired
    private EreaDTOService ereaDTOService;

    @RequestMapping("/selectErea")
    public @ResponseBody
    Map selectErea() {
        System.out.println("**********************************************");
        return ereaDTOService.selectBysex();
    }

}
