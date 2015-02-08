package ua.com.joinit.service;

import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 08.02.2015.
 */
public interface UserCRUDService {
    List<User> getAllUsers();
    User getUser(Long id);
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
}
