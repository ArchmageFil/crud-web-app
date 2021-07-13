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
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserTableUtil {
    @Getter
    private final Properties words = new Properties();
    private final Pattern p = Pattern.compile(
            "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    @Getter
    private String message;

    public UserTableUtil() {
        try {
            words.loadFromXML(Files.newInputStream(
                    new ClassPathResource("words.xml").getFile().toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isInvalidUser(User user) {
        if (isInvalidEmail(user.getEmail())) {
            message = words.getProperty("wrong_email");
            return true;
        }
        if (isInvalidAge(user.getAge())) {
            message = words.getProperty("wrong_age");
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
        try (BufferedReader reader = Files.newBufferedReader(
                new ClassPathResource("mock_data.sql").getFile().toPath())) {
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


