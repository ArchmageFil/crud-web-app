package io.github.archmagefil.crudwebapp.service;

import io.github.archmagefil.crudwebapp.dao.DaoRole;
import io.github.archmagefil.crudwebapp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    DaoRole daoRole;

    @Autowired
    public RoleService(DaoRole daoRole) {
        this.daoRole = daoRole;
    }

    public List<Role> getAllRoles() {
        return daoRole.getAll();
    }
}
