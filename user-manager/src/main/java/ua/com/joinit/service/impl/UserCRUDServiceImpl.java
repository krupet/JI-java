package ua.com.joinit.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.UserCRUDService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by krupet on 08.02.2015.
 */
@Service
@Transactional
public class UserCRUDServiceImpl implements UserCRUDService {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("FROM User");

        return query.list();
    }

    @Override
    public User getUser(Long id) {
        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.get(User.class, id);

        return user;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionFactory.getCurrentSession();

        User user = (User) session.get(User.class, id);

        session.delete(user);
    }

    @Override
    public void editUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        User existingUser = (User) session.get(User.class, user.getId());

        existingUser.setName(user.getName());
        existingUser.setNickName(user.getNickName());

        session.save(existingUser);
    }
}
