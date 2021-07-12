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
    private final static String PAGE = "crud/index.html";
    private final static String RESULT = "result";
    private final static String USR_LST = "userList";
    private final static String REDIRECT = "redirect:/crud?r=true";
    private final static String EDIT_PAGE = "crud/edit.html";
    private final UserService userService;
    private String result;
    private Long id;

    @Autowired
    public CrudController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/crud")
    public String listUsers(@RequestParam(value = "r", defaultValue = "false")
                                    Boolean r, Model model) {
        if (r) {
            model.addAttribute(RESULT, result);
        }
        model.addAttribute(USR_LST, userService.getAllUsers());
        return PAGE;
    }

    @PostMapping("/crud")
    public String addUser(@ModelAttribute User user) {
        result = userService.addUser(user);
        return REDIRECT;
    }

    @PatchMapping("/crud/{id}")
    public String editUser(@PathVariable long id, Model model) {
        User user = userService.find(id);
        if (user == null) {
            result = "Пользователь не найден в БД";
            return REDIRECT;
        }
        this.id = id;
        model.addAttribute("user", user);
        return EDIT_PAGE;
    }

    @PatchMapping("/crud")
    public String updateUser(@ModelAttribute User user) {
        if (this.id != null) {
            user.setId(this.id);
            this.id = null;
            result = userService.updateUser(user);
            return REDIRECT;
        }
        result = "Ошибка запроса, попробуй еще раз.";
        return REDIRECT;
    }

    @DeleteMapping("/crud/{id}")
    public String deleteUser(@PathVariable long id) {
        result = userService.deleteUser(id);
        return REDIRECT;
    }
}