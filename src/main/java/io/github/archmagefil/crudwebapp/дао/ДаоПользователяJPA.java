package io.github.archmagefil.crudwebapp.дао;

import io.github.archmagefil.crudwebapp.модель.Пользователь;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ДаоПользователяJPA implements ДаоПользователя {
    @Autowired
    EntityManagerFactory фс;

    @Override
    public void создать(Пользователь пользователь) {
        EntityManager мс = фс.createEntityManager();
        мс.getTransaction().begin();
        мс.persist(пользователь);
        мс.getTransaction().commit();
        мс.close();
    }

    @Override
    public void обновить(Пользователь пользователь) {
        EntityManager мс = фс.createEntityManager();
        мс.getTransaction().begin();
        мс.merge(пользователь);
        мс.getTransaction().commit();
        мс.close();
    }

    @Override
    public void удалить(long ид) {
        EntityManager em = фс.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.getReference(Пользователь.class, ид));
        em.getTransaction().commit();
        em.flush();
        em.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Пользователь> получитьВсех() {
        EntityManager мс = фс.createEntityManager();
        Query запрос = мс.createQuery("SELECT п FROM Пользователь п ORDER BY п.ид", Пользователь.class);
        List<Пользователь> список = запрос.getResultList();
        мс.close();
        return список;
    }

    @Override
    public List<Пользователь> найтиПользователя(long ид) {
        EntityManager ем = фс.createEntityManager();
        Пользователь п = ем.find(Пользователь.class, ид);
        ем.close();
        return Collections.singletonList(п);
    }

    @Override
    public List<Пользователь> найтиПользователя(String элПочта) {
        EntityManager мс = фс.createEntityManager();
        Query запрос = мс.createQuery("SELECT п from Пользователь п WHERE п.элПочта = :элПочта", Пользователь.class);
        запрос.setParameter("элПочта", элПочта);
        Пользователь п = (Пользователь) запрос.getSingleResult();
        мс.close();
        return Collections.singletonList(п);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Пользователь> найтиПользователя(String имя, String фамилия) {
        EntityManager мс = фс.createEntityManager();
        Query запрос = мс.createQuery("SELECT п FROM Пользователь п WHERE п.имя = :имя AND п.фамилия = :фамилия", Пользователь.class);
        запрос.setParameter("имя", имя);
        запрос.setParameter("фамилия", фамилия);
        List<Пользователь> список = (List<Пользователь>) запрос.getResultList();
        мс.close();
        return список;
    }

    @Override
    public String создатьБД() {
        EntityManager мс = фс.createEntityManager();
        Query запрос = мс.createNativeQuery("");
        int i = запрос.executeUpdate();
        мс.close();
        return "Завершено" + i;
    }

    @Override
    public String очиститьБд() {
        EntityManager мс = фс.createEntityManager();
        Query запрос = мс.createNativeQuery("");
        int i = запрос.executeUpdate();
        мс.close();
        return "Завершено" + i;
    }

    @Override
    public String удалитьБД() {
        EntityManager мс = фс.createEntityManager();
        Query запрос = мс.createNativeQuery("");
        int i = запрос.executeUpdate();
        мс.close();
        return "Завершено" + i;
    }
}
