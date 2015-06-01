package ua.com.joinit.dao;

import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 15.03.2015.
 */
public interface UserDAO {
    User postUser(User user);

    User updateUser(Long id, User user);

    User getUser(Long id);

    List<User> getAllUsers();
}
