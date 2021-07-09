package io.github.archmagefil.crudwebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrudController {
    @GetMapping("/usereditor")
    public String userEditor(Model model) {
        return "usereditor";
    }

    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
//TODO реализовать