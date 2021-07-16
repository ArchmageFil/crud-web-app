package io.github.archmagefil.crudwebapp.service;

import io.github.archmagefil.crudwebapp.dao.DaoUser;
import io.github.archmagefil.crudwebapp.model.User;
import io.github.archmagefil.crudwebapp.model.UserDto;
import io.github.archmagefil.crudwebapp.util.UserTableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private DaoUser dao;
    private RoleService roleService;
    private UserTableUtil util;
    private PasswordEncoder bCrypt;

    @Override
    public List<User> getAllUsers() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public String addUser(UserDto tempUser) {
        if (util.isInvalidUser(tempUser)) {
            return util.getMessage();
        }
        if (dao.find(tempUser.getEmail()) != null) {
            return util.getWords().getProperty("duplicate_email");
        }
        // Криптуем пароль нового юзера.
        tempUser.setPassword(bCrypt.encode(tempUser.getPassword()));
        // Если админ - все разрешения, если нет - только заявленное.
        // Но так как есть только еще 1, то упростим
        if (tempUser.getRole().getRole().equals("ROLE_admin")) {
            tempUser.setRoles(roleService.getAllRoles());
        } else {
            tempUser.getRoles().add(roleService.getById(1L));
        }

        User user = tempUser.createUser();
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
        User user = find(id);
        if (user == null) {
            return util.getWords().getProperty("no_id_in_db");
        }
        user.setRoles(null);
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

    @Autowired
    public void setDao(DaoUser dao) {
        this.dao = dao;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @Autowired
    public void setUtil(UserTableUtil util) {
        this.util = util;
    }

    @Autowired
    public void setbCrypt(PasswordEncoder bCrypt) {
        this.bCrypt = bCrypt;
    }
}
