package ua.com.joinit.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.entity.User;

/**
 * Created by krupet on 15.03.2015.
 */
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User postUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Long userId = (Long) session.save(user);
        return (User) session.load(User.class, userId);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    @Transactional
    public User getUser(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session.load(User.class, id);
    }

    @Override
    @Transactional
    public User deleteUser(Long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        if(user != null) {
            session.delete(user);
            return user; // TODO: need to be well tested!!!!!!!!!!!
        } else {
            return null;
        }
    }
}
