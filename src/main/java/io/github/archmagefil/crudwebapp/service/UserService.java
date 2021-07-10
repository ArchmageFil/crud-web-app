package io.github.archmagefil.crudwebapp.service;

import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.model.UserRaw;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    String addUser(UserRaw user);

    String updateUser(UserRaw user);

    String deleteUser(long id);
}
