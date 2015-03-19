package ua.com.joinit.dao;

import ua.com.joinit.entity.User;

/**
 * Created by krupet on 15.03.2015.
 */
public interface UserDAO {
    User postUser(User user);

    User updateUser(Long id, User user);

    User getUser(Long id);

    User deleteUser(Long id);
}