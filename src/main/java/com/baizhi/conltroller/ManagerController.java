package com.baizhi.conltroller;

import com.baizhi.entity.Manager;
import com.baizhi.service.ManagerService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private Producer producer;

    @RequestMapping("/login")
    public String login(String name, String password, String Kaptcha, HttpSession session) {
        String sessionKaptcha = (String) session.getAttribute("Kaptcha");
        if (sessionKaptcha.equalsIgnoreCase(Kaptcha)) {
            Manager result = managerService.login(name, password);
            if (result != null) {
                session.setAttribute("Manager", result);
                return "redirect:main/main.jsp";
            }
        }
        return "login";
    }

    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Manager manager = (Manager) session.getAttribute("users");
        session.removeAttribute("Manager");
        return "login";
    }

    @RequestMapping("/getKaptcha")
    public void getKaptcha(HttpSession session, HttpServletResponse response) throws Exception {
        String text = producer.createText();
        session.setAttribute("Kaptcha", text);
        BufferedImage image = producer.createImage(text);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }
}
