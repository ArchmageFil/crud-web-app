package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.User;

import java.util.List;

public interface DaoUser {

    void add(User user);

    void update(User user);

    void delete(long id);

    List<User> getAll();

    User find(String email);

    User find(long id);

    String clearDB();

    int executeNative(String nq);
}
//1. Перенесите классы и зависимости из примера в свое MVC приложение из предыдущей задачи.

//2. Создайте класс Role и свяжите User с ролями так, чтобы юзер мог иметь несколько ролей.

//3. Имплементируйте модели Role и User интерфейсами GrantedAuthority и UserDetails соответственно.
// Измените настройку секьюрности с inMemory на userDetailService.

//4. Все CRUD-операции и страницы для них должны быть доступны только пользователю с ролью admin по url: /admin/.

//5. Пользователь с ролью user должен иметь доступ только к своей домашней странице /user, где выводятся его данные.
// Доступ к этой странице должен быть только у пользователей с ролью user и admin.
// Не забывайте про несколько ролей у пользователя!

//6. Настройте logout с любой страницы с использованием возможностей thymeleaf.

//7. Настройте LoginSuccessHandler так, чтобы админа после логина направляло на страницу /admin, а юзера на его страницу.