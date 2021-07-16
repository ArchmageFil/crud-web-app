package io.github.archmagefil.crudwebapp.service;

import io.github.archmagefil.crudwebapp.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    String addUser(User user, String roleString);

    String updateUser(User user);

    String deleteUser(long id);

    User find(long id);

    User find(String email);

    String clearDB();
}
