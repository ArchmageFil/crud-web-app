package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.User;

import java.util.List;

public interface DaoUser extends Dao {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(long id);
    List<User> getAllUsers();
    List<User> findUser(long id);
    List<User> findUser(String email);
    List<User> findUser(String name, String surname);
}
