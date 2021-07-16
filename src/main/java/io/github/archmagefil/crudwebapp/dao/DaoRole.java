package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.Role;

import java.util.List;
import java.util.Set;

public interface DaoRole {
    Role find(Long id);

    List<Role> getAll();
}
