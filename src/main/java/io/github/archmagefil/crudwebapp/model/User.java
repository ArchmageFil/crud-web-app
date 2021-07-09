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
}
