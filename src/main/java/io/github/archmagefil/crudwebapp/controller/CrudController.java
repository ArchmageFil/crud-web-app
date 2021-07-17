package io.github.archmagefil.crudwebapp.controller;

import io.github.archmagefil.crudwebapp.model.Role;
import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.model.UserDto;
import io.github.archmagefil.crudwebapp.model.VisitorMessages;
import io.github.archmagefil.crudwebapp.service.RoleService;
import io.github.archmagefil.crudwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Controller
@SessionScope
@RequestMapping("/admin/")
public class CrudController {
    private final UserService userService;
    private final RoleService roleService;
    private VisitorMessages messages;

    @Autowired
    public CrudController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @ModelAttribute("roles")
    public List<Role> roleList() {
        return roleService.getAllRoles();
    }

    /**
     * Основной список пользователей с формой нового пользоваателя
     */
    @GetMapping("/")
    public String listUsers(@RequestParam(value = "r", defaultValue = "false")
                                    Boolean isRedirect, Model model) {
        model.addAttribute("userDto", new UserDto());
        // Если в запросе пришла инфа о наличии доп. сообщениий - добавить в модель
        if (isRedirect) {
            model.addAttribute("result", messages.getResult());
        }
        model.addAttribute("userList", userService.getAllUsers());
        return "/admin/index.html";
    }

    /**
     * Создаание нового пользователя
     *
     * @param tempUser ДТОшка для подготовки к созданию сущности
     * @return возвращает на основную админку.
     */
    @PostMapping("/")
    public String addUser(@ModelAttribute UserDto tempUser) {
        messages.setResult(userService.addUser(tempUser));
        return "redirect:/admin/?r=true";
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
            return "redirect:admin/?r=true";
        }
        // Кидаем в сообщения результат операции ии возвращаемся на основную страницу
        messages.setId(id);
        model.addAttribute("user", user);
        return "/admin/edit.html";
    }

    /**
     * Обработка формы на редактирование пользователя.
     */
    @PatchMapping("/")
    public String updateUser(@ModelAttribute User tempUser) {
        // Ставим ранее сохраненный ИД
        User user = userService.find(messages.getId());
        if (user == null) {
            messages.setResult("Ошибка запроса, попробуй еще раз.");
            return "redirect:/admin/?r=true";
        }
        user.setName(tempUser.getName());
        user.setSurname(tempUser.getSurname());
        user.setEmail(tempUser.getEmail());
        user.setGoodAcc(tempUser.getGoodAcc());
        // Кидаем в сообщения результат операции ии возвращаемся на основную страницу
        messages.setResult(userService.updateUser(user));
        return "redirect:/admin/?r=true";
    }

    /**
     * Обработка запроса на удаление из БД пользователя по ИД
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        messages.setResult(userService.deleteUser(id));
        return "redirect:/admin/?r=true";
    }

    /**
     * Очистка БД
     */
    @DeleteMapping("/db_gen/")
    public String clear(UserService service) {
        messages.setResult(service.clearDB());
        return "redirect:/admin/?r=true";
    }

    @Autowired
    public void setMessages(VisitorMessages messages) {
        this.messages = messages;
    }

}