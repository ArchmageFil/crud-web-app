package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.User;

import java.util.List;

public interface DaoUser {

    void add(User user);

    void update(User user);

    void delete(long id);

    List<User> getAll();

    List<User> find(String email);

    User find(long id);

    String clearDB();

    int executeNative(String nq);
}
