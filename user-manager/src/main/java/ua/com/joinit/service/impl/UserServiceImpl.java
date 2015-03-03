package ua.com.joinit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.UserService;
import ua.com.joinit.service.mock.UserMockDAO;

/**
 * Created by krupet on 3/3/15.
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMockDAO userMockDAO;

    @Override
    public User postUser(User user) {
        return userMockDAO.postUser(user);
    }
}
