package io.github.archmagefil.crudwebapp;

import io.github.archmagefil.crudwebapp.дао.ДаоПользователя;
import io.github.archmagefil.crudwebapp.дао.ДаоПользователяJPA;
import io.github.archmagefil.crudwebapp.конфиг.КонфигБд;
import io.github.archmagefil.crudwebapp.конфиг.НастройкаПриложения;
import io.github.archmagefil.crudwebapp.модель.Пользователь;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.TransactionManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;

public class Тест {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(КонфигБд.class);
        ДаоПользователя дао = new ДаоПользователяJPA();
        Пользователь п = new Пользователь.СоздатьПользователя().возраст(10).имя("Вася").фамилия("Васин").элПочта("вася@мыло.рф").создать();
        дао.создать(п);
        System.out.println(дао.получитьВсех());


    }
}
