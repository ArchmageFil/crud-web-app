package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.dao.DaoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LandingController {
    private final DaoUser daoUser;

    @Autowired
    public LandingController(DaoUser daoUser) {
        this.daoUser = daoUser;
    }

    @GetMapping("/")
    public String landing() {
        return "index.html";
    }

    @PostMapping("/")
    public String clear(Model model) {
        model.addAttribute("result", daoUser.clearDB());
        return "index.html";
    }

    @GetMapping(value = "/crud.css", headers = "Accept=text/css")
    public String css() {
        return "crud/crud.css";
    }

}
