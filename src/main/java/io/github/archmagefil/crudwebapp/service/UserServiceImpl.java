package io.github.archmagefil.crudwebapp.service;

import io.github.archmagefil.crudwebapp.dao.DaoUser;
import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.model.UserRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private static final String WRONG_EMAIL = "Неправильный синтаксис электронной почты";
    private static final String WRONG_AGE = "Возраст должен быть корректным или не быть вообще";
    private static final String NO_ID_IN_DB = "Пользователя с таким ИД нет в базе данных";
    private DaoUser dao;


    @Override
    public List<User> getAllUsers() {
        return dao.getAll();
    }

    @Override
    public String addUser(UserRaw user) {
        if (user.isInvalidEmail()){
            return WRONG_EMAIL;
        }
        if (user.isInvalidAge()){
            return WRONG_AGE;
        }
        if (!dao.find(user.getEmail()).isEmpty()){
            return "Пользователь с таким почтовым адресом уже есть.";
        }
        dao.add(User.of(user));
        return "Пользователь " + user.getName() + " " + user.getSurname()
                + " добавлен";
    }

    @Override
    public String updateUser(UserRaw user) {
        if (user.isInvalidEmail()){
            return WRONG_EMAIL;
        }
        if (user.isInvalidAge()){
            return WRONG_AGE;
        }
        if (user.getId() == null || dao.find(user.getId()).isEmpty()){
            return NO_ID_IN_DB;
        }
        dao.update(User.of(user));
        return "Обновлено";
    }

    @Override
    public String deleteUser(long id) {
        if (dao.find(id).isEmpty()){
            return NO_ID_IN_DB;
        }
        dao.delete(id);
        return "Удален";
    }


    @Autowired
    public void setDao(DaoUser dao) {
        this.dao = dao;
    }
}
