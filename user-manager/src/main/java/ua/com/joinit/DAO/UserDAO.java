package ua.com.joinit.DAO;

import ua.com.joinit.entity.User;

/**
 * Created by krupet on 3/3/15.
 */
public interface UserDAO {

    User postUser(User user);

    User updateUser(Long id);

    User getUser(Long id);

    User deleteUser(Long id);
}
