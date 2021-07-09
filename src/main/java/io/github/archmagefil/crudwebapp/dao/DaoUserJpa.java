package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Component
@Transactional
public class DaoUserJpa implements DaoUser {
    @PersistenceContext
    EntityManager em;

    public DaoUserJpa(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {
        em.getTransaction().begin();
        em.remove(em.getReference(User.class, id));
        em.getTransaction().commit();
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u ORDER BY u.id",
                User.class).getResultList();
    }

    @Override
    public List<User> find(long id) {
        User u = em.find(User.class, id);
        return Collections.singletonList(u);
    }

    @Override
    public List<User> find(String email) {
        Query query = em.createQuery("SELECT u from User u " +
                "WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User u = (User) query.getSingleResult();
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
}
