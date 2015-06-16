package ua.com.joinit.service;

import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 28.01.2015.
 */
public interface UserService {

    User postUser(User user);

    User updateUser(User user);

    User getUser(Long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    User addUserIntoGroup(Long userID, Long groupID);

    User removeUserFromGroup(Long userID, Long groupID);

    User addUserIntoEvent(Long userID, Long eventID);

    User removeUserFromEvent(Long userID, Long eventID);
}
