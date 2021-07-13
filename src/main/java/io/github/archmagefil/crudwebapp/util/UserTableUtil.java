package io.github.archmagefil.crudwebapp.util;

import io.github.archmagefil.crudwebapp.dao.DaoUser;
import io.github.archmagefil.crudwebapp.model.User;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class UserTableUtil {
    public static final String DELETED = "Удален";
    public static final String UPDATED = "Обновлено";
    public static final String ADDED = " добавлен";
    public static final String USER = "Пользователь ";
    public static final String WRONG_EMAIL = "Неправильный синтаксис электронной почты";
    public static final String WRONG_AGE = "Возраст должен быть корректным или не быть вообще";
    public static final String NO_ID_IN_DB = "Пользователя с таким ИД нет в базе данных";
    public static final String DUPLICATE_EMAIL = "Пользователь с таким почтовым адресом уже есть.";
    private final static Pattern p = Pattern.compile(
            "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    @Getter
    private String message;

    public boolean isInvalidUser(User user) {
        if (isInvalidEmail(user.getEmail())) {
            message = WRONG_EMAIL;
            return true;
        }
        if (isInvalidAge(user.getAge())) {
            message = WRONG_AGE;
            return true;
        }
        return false;
    }

    boolean isInvalidEmail(String email) {
        if (email == null) {
            return true;
        }
        Matcher syntax = p.matcher(email);
        return !syntax.matches();
    }

    public boolean isInvalidAge(Integer age) {
        return age != null && (age <= 0 || age >= 200);
    }

    @Transactional
    public String generateFakeUsers(DaoUser daoUser) {
        try {
            BufferedReader reader = Files.newBufferedReader(
                    new ClassPathResource("mock_data.sql").getFile().toPath());
            return "Внесено: " + reader.lines()
                    .mapToInt(l -> {
                        int i = 0;
                        try {
                            i = daoUser.executeNative(l);
                        } catch (Exception ignore) {
                            //...
                        }
                        return i;
                    }).sum();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}


