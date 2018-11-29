package com.baizhi.conltroller;

import com.baizhi.entity.Pricture;
import com.baizhi.service.PrictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller

public class PrictureController {
    @Autowired
    private PrictureService prictureService;

    @RequestMapping("/selectPricture")
    public @ResponseBody
    Map selectPricture(int page, int rows) {
        return prictureService.selectPricture(page, rows);
    }

    @RequestMapping("/updatePricture")
    public @ResponseBody
    boolean updatePricture(Pricture pricture) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(pricture);
        try {
            prictureService.updatePricture(pricture);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    @RequestMapping("/insertPricture")
    public @ResponseBody
    boolean insertPricture(Pricture pricture, MultipartFile upfile, HttpServletRequest request) throws IllegalStateException, IOException {

        try {
            // 获取上传的文件的名字
            String fileName = upfile.getOriginalFilename();

            // 通过uuid获取新名字
            String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));

            //1.获取文件夹的相对路径
            String realPath = request.getSession().getServletContext().getRealPath("/shouye");

            //2.file对象（上传到项目的productImages文件夹中）
            File file = new File(realPath + "\\" + newFileName);
            //3.写入
            upfile.transferTo(file);
            pricture.setImgPath(fileName);

            prictureService.insert(pricture);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/delete")
    public @ResponseBody
    boolean delete(int id) {
        try {
            prictureService.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
