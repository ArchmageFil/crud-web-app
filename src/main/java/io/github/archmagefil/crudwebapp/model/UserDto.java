package io.github.archmagefil.crudwebapp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
@NoArgsConstructor
public class UserDto {
    String name = null;
    String surname = null;
    String email;
    String password;
    Boolean goodAcc = true;
    String role;
    List<Role> roles = new ArrayList<>();

    public User createUser() {
        return new User(name, surname, email, password, goodAcc, roles);
    }
}
