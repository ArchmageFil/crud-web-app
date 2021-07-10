package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionScope
//@RequestMapping("/crud")
public class CrudController {
    private final static String PAGE = "crud/index";
    private final static String RESULT = "result";
    private final static String USR_LST = "userList";
    private final UserService userService;
    private String result;
    @Autowired
    public CrudController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/crud")
    public String listUsers(@RequestParam(value = "r", defaultValue = "false")Boolean r, Model model) {
        if (r){
            model.addAttribute(RESULT, result);
        }
        model.addAttribute(USR_LST, userService.getAllUsers());
        return PAGE;
    }

    @PostMapping("/crud")
    public String addUser(@ModelAttribute User user) {
        result = userService.addUser(user);
        return "redirect:/crud?r=true";
    }

    @PatchMapping("/crud")
    public String updateUser(@RequestParam User user, Model model) {
        model.addAttribute(RESULT, userService.updateUser(user));
        model.addAttribute(USR_LST, userService.getAllUsers());
        return PAGE;
    }

    @DeleteMapping("/crud")
    public String deleteUser(@RequestParam long id, Model model) {
        model.addAttribute(RESULT, userService.deleteUser(id));
        model.addAttribute(USR_LST, userService.getAllUsers());
        return PAGE;
    }
    //TODO Статика выполняется первой и даст не актуальный список, поискать можно ли как то обойти?
}