package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class DaoUserJpa implements DaoUser {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        System.out.println(user);
        em.merge(user);
    }

    @Override
    public void delete(long id) {
        em.remove(em.getReference(User.class, id));
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u ORDER BY u.id",
                User.class).getResultList();
    }

    @Override
    public User find(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> find(String email) {
        Query query = em.createQuery("SELECT u from User u " +
                "WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User u;
        try {
            u = (User) query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return Collections.emptyList();
        }
        return Collections.singletonList(u);
    }

    @Override
    public List<User> find(String name, String surname) {
        return em.createQuery("SELECT u FROM User u WHERE u.name = :name " +
                "AND u.surname = :surname", User.class)
                .setParameter("name", name)
                .setParameter("surname", surname).getResultList();
    }

    @Override
    public String clearDB() {
        int i = em.createQuery("DELETE FROM User").executeUpdate();
        return "Завершено" + i;
    }

    @Override
    public int executeNative(String nq) {
        return em.createNativeQuery(nq).executeUpdate();
    }
}
