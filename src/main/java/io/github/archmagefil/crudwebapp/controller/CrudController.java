package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.dao.DaoUserJpa;
import io.github.archmagefil.crudwebapp.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CrudController {
    @GetMapping("/usereditor")
    public String userEditor(Model model, DaoUserJpa dao){
        List<User> list = dao.getAllUsers();
        model.addAttribute("users", list);
        return "usereditor";
    }
    @GetMapping("/")
    public String hello(){
        return "index";
    }
}
//TODO реализовать