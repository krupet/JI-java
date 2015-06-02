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
    public User updateUser(Long id, User user) {
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
}
