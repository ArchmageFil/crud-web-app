package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DaoRoleJpa implements DaoRole {
    @PersistenceContext
    private EntityManager em;


    @Override
    public Role find(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public List<Role> getAll() {
        return em.createQuery("SELECT r FROM Role r", Role.class)
                .getResultList();
    }
}
