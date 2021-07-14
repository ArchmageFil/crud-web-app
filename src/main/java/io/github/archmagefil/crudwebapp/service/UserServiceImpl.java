package io.github.archmagefil.crudwebapp.service;

import io.github.archmagefil.crudwebapp.dao.DaoUser;
import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.util.UserTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private DaoUser dao;
    private UserTableUtil util;

    @Override
    public List<User> getAllUsers() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public String addUser(User user) {
        if (util.isInvalidUser(user)) {
            return util.getMessage();
        }
        if (dao.find(user.getEmail()) != null) {
            return util.getWords().getProperty("duplicate_email");
        }
        dao.add(user);
        return String.format(util.getWords().getProperty("user_added"),
                user.getName(), user.getSurname());
    }

    @Override
    @Transactional
    public String updateUser(User user) {
        if (null == find(user.getId())) {
            return util.getWords().getProperty("no_id_in_db");
        }
        System.out.println(user);
        dao.update(user);
        return util.getWords().getProperty("updated");
    }

    @Override
    @Transactional
    public String deleteUser(long id) {
        if (find(id) == null) {
            return util.getWords().getProperty("no_id_in_db");
        }
        dao.delete(id);
        return util.getWords().getProperty("deleted");
    }

    @Override
    public User find(long id) {
        return dao.find(id);
    }

    @Override
    public User find(String email) {
        return dao.find(email);
    }

    @Transactional
    @Override
    public String clearDB() {
        return dao.clearDB();
    }

    @Override
    public String generateDb() {
        return util.generateFakeUsers(dao);
    }

    @Autowired
    public void setDao(DaoUser dao) {
        this.dao = dao;
    }

    @Autowired
    public void setUtil(UserTableUtil util) {
        this.util = util;
    }
}
