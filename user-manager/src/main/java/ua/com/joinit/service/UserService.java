package ua.com.joinit.service;

import ua.com.joinit.entity.User;

/**
 * Created by krupet on 28.01.2015.
 */
public interface UserService {

    User postUser(User user);

    User updateUser(Long id);

    User getUser(Long id);

    User deleteUser(Long id);
}
