package com.baizhi.conltroller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import com.baizhi.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/insertChapter")
    public @ResponseBody
    boolean insertChapter(MultipartFile chapter1, Chapter chapter, HttpServletRequest request) {
        String name = chapter1.getOriginalFilename();
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(realPath + "/shouye/music");
        String extension = FilenameUtils.getExtension(chapter1.getOriginalFilename());
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        String newName = s + "." + extension;

        try {
            chapter1.transferTo(new File(file.getAbsolutePath(), newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int length = FileUtil.getDuration(new File(file.getAbsolutePath() + "/" + newName));
        int M = length / 60;
        int S = length % 60;
        String time = M + "分" + S + "秒";

        long size = chapter1.getSize();
        double d = size / 1024 / 1024;
        System.out.println(d);
        chapter.setSize(size);
        chapter.setDuration(time);
        chapter.setTitle(name);
        chapter.setDownPath(newName);
        chapter.setAid(chapter.getAid());
        try {
            chapterService.insert(chapter);
            System.out.println("章节打印" + chapter);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("/download")
    public void download(String downPath, String title, HttpServletRequest request, HttpServletResponse response) {

        String uploadPath = request.getSession().getServletContext().getRealPath("shouye/music");  //webapp当前项目的路径
        String path = uploadPath + "/" + downPath;
        File file = new File(path);

        String s = title;


        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(s, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("audio/mpeg");

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
