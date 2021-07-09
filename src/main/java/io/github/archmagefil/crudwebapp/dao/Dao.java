package io.github.archmagefil.crudwebapp.dao;


import io.github.archmagefil.crudwebapp.model.User;

import java.util.List;

public interface Dao<T> {
    String clearDB();
     void add(T t);
    void update(T t);
    void delete(long id);
    List<T> getAll();
    List<T> find(long id);

}