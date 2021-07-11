package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LandingController {
    private final UserService service;

    @Autowired
    public LandingController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String landing() {
        return "index.html";
    }

    @DeleteMapping("/")
    public String clear(Model model) {
        model.addAttribute("result", service.clearDB());
        return "index.html";
    }

    @PostMapping("/")
    public String generateDb(Model model) {
        model.addAttribute("result", service.generateDb());
        return "index.html";
    }

    @GetMapping(value = "/crud.css", headers = "Accept=text/css")
    public String css() {
        return "crud/crud.css";
    }

}
