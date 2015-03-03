package ua.com.joinit.service.mock;

import ua.com.joinit.entity.User;

/**
 * Created by krupet on 3/3/15.
 */
public class UserMockDAOImpl implements UserMockDAO {
    @Override
    public User postUser(User user) {
//        User user1 = new User();
//        user1.setId(1l);
//        return user1;

        return  new User();
    }
}
