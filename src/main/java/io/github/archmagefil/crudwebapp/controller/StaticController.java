package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class StaticController {
    private static final User USER = new User(); //TODO что насчет передечи с моделью?

    private final UserService userService;

    public StaticController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addHat(Model model) {
        model.addAttribute("hat", "Редактор пользователей" +
                "");
        model.addAttribute("user", USER);

    }


}