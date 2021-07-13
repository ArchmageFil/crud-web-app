package io.github.archmagefil.crudwebapp.service;

import io.github.archmagefil.crudwebapp.dao.DaoUser;
import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.util.UserTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static io.github.archmagefil.crudwebapp.util.UserTableUtil.*;

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
        if (!dao.find(user.getEmail()).isEmpty()) {
            return DUPLICATE_EMAIL;
        }
        dao.add(user);
        return USER + user.getName() + " " + user.getSurname() + ADDED;
    }

    @Override
    @Transactional
    public String updateUser(User user) {
        if (null == find(user.getId())) {
            return NO_ID_IN_DB;
        }
        dao.update(user);
        return UPDATED;
    }

    @Override
    @Transactional
    public String deleteUser(long id) {
        if (find(id) == null) {
            return NO_ID_IN_DB;
        }
        dao.delete(id);
        return DELETED;
    }

    @Override
    public User find(long id) {
        return dao.find(id);
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
