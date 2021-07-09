package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.User;

import java.util.List;

public interface DaoUser extends Dao<User> {
    List<User> find(String email);

    List<User> find(String name, String surname);
}
