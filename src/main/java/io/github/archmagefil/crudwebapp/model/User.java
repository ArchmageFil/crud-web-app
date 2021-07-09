package io.github.archmagefil.crudwebapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String surname;
    int age;
    @Column(unique = true, nullable = false)
    String email;

    public static class createUser {
        User user;

        public createUser() {
            user = new User();
        }

        public createUser name(String name) {
            user.name = name;
            return this;
        }

        public createUser surname(String surname) {
            user.surname = surname;
            return this;
        }

        public createUser email(String email) {
            user.email = email;
            return this;
        }

        public createUser age(int age) {
            user.age = age;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
