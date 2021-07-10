package io.github.archmagefil.crudwebapp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Data
@FieldDefaults(level = AccessLevel.PACKAGE)
public class UserRaw {
    private final static Pattern p = Pattern.compile(
            "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    Long id;
    String name;
    String surname;
    Integer age;
    String email;


    public boolean isInvalidEmail() {
        if (email == null) {
            return true;
        }
        Matcher syntax = p.matcher(email);
        return !syntax.matches();
    }

    public boolean isInvalidAge() {
        return age != null && (age <= 0 || age >= 200);
    }
}
