package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.model.VisitorMessages;
import io.github.archmagefil.crudwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionScope
@RequestMapping("/crud/")
public class CrudController {
    private final UserService userService;
    private VisitorMessages messages;

    @Autowired
    public CrudController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Основной список пользователей с формой нового пользоваателя
     */
    @GetMapping("/")
    public String listUsers(@RequestParam(value = "r", defaultValue = "false")
                                    Boolean isRedirect, Model model) {
        // Если в запросе пришла инфа о наличии доп. сообщениий - добавить в модель
        if (isRedirect) {
            model.addAttribute("result", messages.getResult());
        }
        model.addAttribute("userList", userService.getAllUsers());
        return "/crud/index.html";
    }

    /**
     * Добавление нового пользователя
     */
    @PostMapping("/")
    public String addUser(@ModelAttribute User user) {
        // кидаем в сообщения результат операции
        messages.setResult(userService.addUser(user));
        return "redirect:/crud/?r=true";
    }

    /**
     * Запрос странички с редактированием пользователя
     */
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        User user = userService.find(id);
        // Такого не должно быть, т.к. вызов по кнопке, но если сервис
        // не нашел пользователя то возвращаемся на общую страничку
        if (user == null) {
            messages.setResult("Пользователь не найден в БД");
            return "redirect:crud/?r=true";
        }
        // Кидаем в сообщения результат операции ии возвращаемся на основную страницу
        messages.setId(id);
        model.addAttribute("user", user);
        return "/crud/edit.html";
    }

    /**
     * Обработка формы на редактирование пользователя.
     */
    @PatchMapping("/")
    public String updateUser(@ModelAttribute User user) {
        // Ставим ранее сохраненный ИД
        user.setId(messages.getId());
        if (user.getId() == null) {
            messages.setResult("Ошибка запроса, попробуй еще раз.");
            return "redirect:/crud/?r=true";
        }
        // Кидаем в сообщения результат операции ии возвращаемся на основную страницу
        messages.setResult(userService.updateUser(user));
        return "redirect:/crud/?r=true";
    }

    /**
     * Обработка запроса на удаление из БД пользователя по ИД
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        messages.setResult(userService.deleteUser(id));
        return "redirect:/crud/?r=true";
    }

    @Autowired
    public void setMessages(VisitorMessages messages) {
        this.messages = messages;
    }
}