package ua.com.joinit.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.BaseAppTest;
import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.entity.User;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by krupet on 15.03.2015.
 */
public class DAOTest extends BaseAppTest{
    @Autowired
    private UserDAO userDAO;

    @Test
    public void post_new_user() {
        User testUser = new User();
        testUser.setFirstName("test_f_name");
        testUser.setLastName("test_last_name");
        testUser.setNickName("test_nickname");
        testUser.setEmail("testUser@gmail.com");
        testUser.setPhone(1234567890L);
        testUser.setAboutYourself("desc");

        User dbUser = userDAO.postUser(testUser);
        assertNotNull(dbUser.getId());
    }

    @Test
    public void get_user_by_id() {
        long id = 2L;
        User dbUser = userDAO.getUser(id);
        assertNotNull(dbUser);
    }

    @Test
    public void get_list_of_all_users() {
        List dbUsers = userDAO.getAllUsers();
        assertNotNull(dbUsers);
        assertNotNull(dbUsers.get(0));
    }

    @Test
    public void update_user() {
        long id = 2L;
        User testUser = new User();
        testUser.setId(id);
        testUser.setFirstName("f_name");
        testUser.setLastName("last_name");
        testUser.setNickName("nickname");
        testUser.setEmail("test1User@gmail.com");
        testUser.setPhone(987654321L);
        testUser.setAboutYourself("test_desc");

        User dbUser = userDAO.updateUser(testUser.getId(), testUser);
        assertNotNull(dbUser);
        System.out.println(testUser.toString());
//        assertNotNull(dbUser.getId());
//        assertEquals(dbUser.getId(), testUser.getId());
    }
}
