package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.model.UserRaw;
import io.github.archmagefil.crudwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/crud")
public class CrudController {
    private final static String PAGE = "crud/index";
    private final static String RESULT = "result";
    private final static String USR_LST = "userList";
    private final UserService userService;

    @Autowired
    public CrudController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/crud")
    public String listUsers(Model model) {
        model.addAttribute(USR_LST, userService.getAllUsers());
        return PAGE;
    }

    @PostMapping("/crud")
    public String addUser(@RequestParam UserRaw user, Model model) {
        model.addAttribute(RESULT, userService.addUser(user));
        model.addAttribute(USR_LST, userService.getAllUsers());
        return PAGE;
    }

    @PatchMapping("/crud")
    public String updateUser(@RequestParam UserRaw user, Model model) {
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
}