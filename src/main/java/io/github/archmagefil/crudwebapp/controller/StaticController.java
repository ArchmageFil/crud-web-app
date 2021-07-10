package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class StaticController {
    @ModelAttribute
    public void addHat(Model model) {
        model.addAttribute("hat", "Редактор пользователей");
        model.addAttribute("user", new User());
    }


}