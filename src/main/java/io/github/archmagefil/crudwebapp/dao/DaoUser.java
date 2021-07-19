package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.User;

import java.util.List;
import java.util.Optional;

public interface DaoUser {

    void add(User user);

    void update(User user);

    void deleteById(long id);

    List<User> getAll();

    Optional<User> findByEmail(String email);

    User findById(long id);

    String clearDB();
}