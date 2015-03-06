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
    private UserDAO userMockDAO;

    @Override
    public User postUser(User user) {
        return userMockDAO.postUser(user);
    }

    @Override
    public User updateUser(Long id) {
        return userMockDAO.updateUser(id);
    }

    @Override
    public User getUser(Long id) {
        return userMockDAO.getUser(id);
    }

    @Override
    public User deleteUser(Long id) {
        return userMockDAO.deleteUser(id);
    }
}
