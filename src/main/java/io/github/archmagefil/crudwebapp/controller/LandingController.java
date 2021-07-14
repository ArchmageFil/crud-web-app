package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
public class LandingController {
    private final UserService service;

    @Autowired
    public LandingController(UserService service) {
        this.service = service;
    }

    /**
     * @return Страничка по умолчанию, приветствие, ссылки
     */
    @GetMapping("/")
    public String landing() {
        return "index.html";
    }

    /**
     * @return Страница с формой авторизациии.
     */
    @GetMapping("/login")
    public String loginPage(){
        return "login.html";
    }
    @GetMapping("/user")
    public String userPage(Principal principal, UserService service, Model model){
        //model.addAttribute("user", service.
        // TODO добавять текущего пользователя по принципаалу в модель.
        return "user.html";
    }

    /**
     * @return Табличка стилей.
     */
    @GetMapping(value = "resources/crud.css", headers = "Accept=text/css")
    public String css() {
        return "resources/crud.css";
    }

    /**
     * favicon.ico просто раздражало постоянное 404
     */
    @GetMapping(value = "/favicon.ico", produces = "image/png")
    @ResponseBody
    public void favicon(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        InputStream is =Files.newInputStream(new ClassPathResource(
                "../view/resources/favicon.png").getFile().toPath());
        IOUtils.copy(is, response.getOutputStream());
    }
}
