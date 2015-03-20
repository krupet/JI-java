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

/**
 * Created by krupet on 15.03.2015.
 */
public class DAOTest extends BaseAppTest{
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void post_new_user() {

        User user = new User("name", "nickname");

//        userDAO.postUser(user);

//        userDAO.deleteUser(1L);

        User obtainedUser = userDAO.getUser(2L);
        String name = obtainedUser.getName();

        System.out.println(name);

//        Session session = sessionFactory.openSession();
//        Transaction tx = null;
//        Long employeeID = null;
//        try{
//            tx = session.beginTransaction();
//            User user = new User("name", "nickname");
//            employeeID = (Long) session.save(user);
//            tx.commit();
//        }catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
    }
}
