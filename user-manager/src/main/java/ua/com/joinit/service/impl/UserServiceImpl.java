package ua.com.joinit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.DAO.UserDAO;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.UserService;

/**
 * Created by krupet on 3/3/15.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User postUser(User user) {
        return userDAO.postUser(user);
    }

    @Override
    public User updateUser(Long id) {
        return userDAO.updateUser(id);
    }

    @Override
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Override
    public User deleteUser(Long id) {
        return userDAO.deleteUser(id);
    }
}
