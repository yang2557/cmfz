package com.baizhi.conltroller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
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
public class AlbumConreoller {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/selectBypage")
    public @ResponseBody
    Map selectBypage(Integer page, Integer rows) {

        return albumService.selectBypage(page, rows);

    }

    @RequestMapping("/insertAlbum")
    public @ResponseBody
    boolean insertAlbum(Album album, MultipartFile ifile, HttpServletRequest request) throws IllegalStateException, IOException {

        try {
            // 获取上传的文件的名字
            String fileName = ifile.getOriginalFilename();

            // 通过uuid获取新名字
            String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));

            //1.获取文件夹的相对路径
            String realPath = request.getSession().getServletContext().getRealPath("/shouye/pricture");

            //2.file对象（上传到项目的productImages文件夹中）
            File file = new File(realPath + "\\" + newFileName);
            //3.写入
            ifile.transferTo(file);


            albumService.insert(album);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
