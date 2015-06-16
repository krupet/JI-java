package ua.com.joinit.mock;

import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 3/3/15.
 */
public class UserMockDAOImpl implements UserDAO {
    @Override
    public User postUser(User user) {
        return  null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User addUserIntoGroup(Long userID, Long groupID) {
        return null;
    }

    @Override
    public User removeUserFromGroup(Long userID, Long groupID) {
        return null;
    }

    @Override
    public User addUserIntoEvent(Long userID, Long eventID) {
        return null;
    }

    @Override
    public User removeUserFromEvent(Long userID, Long eventID) {
        return null;
    }
}
