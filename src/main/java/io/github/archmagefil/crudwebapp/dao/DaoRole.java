package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.Role;

import java.util.List;
import java.util.Optional;

public interface DaoRole {
    Optional<Role> findByName(String role);

    List<Role> getAll();
}
