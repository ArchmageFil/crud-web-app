package io.github.archmagefil.crudwebapp.dao;

import io.github.archmagefil.crudwebapp.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository
public class DaoUserJpa implements DaoUser {
    @PersistenceContext(unitName = "entityManagerFactory")
    EntityManager em;

    public DaoUserJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public void addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(long id) {
        em.getTransaction().begin();
        em.remove(em.getReference(User.class, id));
        em.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        Query query = em.createQuery("SELECT u FROM User u ORDER BY u.id", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> findUser(long id) {
        User u = em.find(User.class, id);
        return Collections.singletonList(u);
    }

    @Override
    public List<User> findUser(String email) {
        Query query = em.createQuery("SELECT u from User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        User u = (User) query.getSingleResult();
        return Collections.singletonList(u);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findUser(String name, String surname) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.surname = :surname", User.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return  (List<User>) query.getResultList();
    }

    @Override
    public String createDB() {
//        Query query = em.createNativeQuery("");
//        int i = query.executeUpdate();
//        return "Завершено" + i;
        return null;
    }

    @Override
    public String clearDB() {
//        Query запрос = em.createNativeQuery("");
//        int i = запрос.executeUpdate();
//        return "Завершено" + i;
        return null;
    }

    @Override
    public String dropDB() {
//        Query запрос = em.createNativeQuery("");
//        int i = запрос.executeUpdate();
//        return "Завершено" + i;
        return null;
    }
}
