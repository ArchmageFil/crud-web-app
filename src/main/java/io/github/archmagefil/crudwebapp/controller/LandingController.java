package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class LandingController {
    private final UserService service;

    @Autowired
    public LandingController(UserService service) {
        this.service = service;
    }

    /**
     * Страничка по умолчанию, приветствие ссылки
     */
    @GetMapping("/")
    public String landing() {
        return "index.html";
    }

    /**
     * Очистка БД
     */
    @DeleteMapping("/")
    public String clear(Model model) {
        model.addAttribute("result", service.clearDB());
        return "index.html";
    }

    /**
     * Генерация случайных пользователей
     */
    @PostMapping("/")
    public String generateDb(Model model) {
        model.addAttribute("result", service.generateDb());
        return "index.html";
    }

    /**
     * Табличка стилей
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
        InputStream is = Files.newInputStream(Paths.get(
                "E:/IDE/idea_proj/crud_web_app/target/crud_web_app/WEB-INF/view/resources/favicon.png"));
        IOUtils.copy(is, response.getOutputStream());
    }
}
