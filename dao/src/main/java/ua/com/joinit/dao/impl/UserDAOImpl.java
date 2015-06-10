package ua.com.joinit.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krupet on 15.03.2015.
 */
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User postUser(User user) {
        Session session = sessionFactory.openSession();

        Long userId = (Long) session.save(user);
        //TODO: as i understand user loads from hibernate lvl1 cache
        User dbUser = (User) session.load(User.class, userId);
        session.close();

        return dbUser;
    }

    @Override
    public User updateUser(Long id, User user) {
        Session session = sessionFactory.openSession();
//        session.update(user);
        session.saveOrUpdate(user);
        session.flush(); //TODO: set autoflush!! see: http://stackoverflow.com/questions/3313458/hibernate-is-not-updating-record-wicket
        User dbUser = (User) session.load(User.class, id);
        session.close();

        return dbUser;
    }

    @Override
    public User getUser(Long id) {

        Session session = sessionFactory.openSession();
        User dbUser = (User) session.get(User.class, id);

        session.close();
        return dbUser;
    }

    @Override
    @SuppressWarnings(value = "unchecked") // List dbUsers casting...
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(User.class);
        List dbUsers = criteria.list(); // ArrayList<???> ???

        session.close();
        return dbUsers;
    }
}
