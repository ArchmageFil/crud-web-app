package io.github.archmagefil.crudwebapp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String role;

    @Override
    public String getAuthority() {
        return role;
    }
}

