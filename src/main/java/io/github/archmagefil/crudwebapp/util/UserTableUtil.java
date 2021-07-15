package io.github.archmagefil.crudwebapp.util;

import io.github.archmagefil.crudwebapp.dao.DaoRole;
import io.github.archmagefil.crudwebapp.dao.DaoUser;
import io.github.archmagefil.crudwebapp.model.Role;
import io.github.archmagefil.crudwebapp.model.User;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
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
        return false;
    }

    boolean isInvalidEmail(String email) {
        if (email == null) {
            return true;
        }
        Matcher syntax = p.matcher(email);
        return !syntax.matches();
    }
}


