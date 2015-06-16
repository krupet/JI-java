package ua.com.joinit.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        User dbUser = (User) session.get(User.class, userId);
        session.close();

        return dbUser;
    }

    @Override
    public User updateUser(User user) {
        Long id = user.getId();
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(user);
        session.flush(); //TODO: set auto-flush!! see: http://stackoverflow.com/questions/3313458/hibernate-is-not-updating-record-wicket
        User dbUser = (User) session.get(User.class, id);
        session.close();

        return dbUser;
    }

    @Override
    public User getUser(Long id) {

        Session session = sessionFactory.openSession();
        User dbUser = (User) session.get(User.class, id);
        Hibernate.initialize(dbUser.getGroups());
        Hibernate.initialize(dbUser.getEvents());

        session.close();
        return dbUser;
    }

    @Override
    public User getUserByEmail(String email) {

        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("email", email));
        User dbUser = (User) criteria.uniqueResult();

        session.close();
        return dbUser;
    }

        /*
            to avoid a big number of queries against db
            this method will return list of users without
            their events & groups lists
         */
    @Override
    @SuppressWarnings(value = "unchecked")
    public List<User> getAllUsers() {

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(User.class);
        List<User> dbUsers = (ArrayList<User>) criteria.list();

        session.close();

        for (User user: dbUsers) {
            user.setGroups(null);
            user.setEvents(null);
        }

        return dbUsers;
    }

    @Override
    public User addUserIntoGroup(Long userID, Long groupID) {

        Session session = sessionFactory.openSession();

        User dbUser = (User) session.get(User.class, userID);
        Group dbGroup = (Group) session.get(Group.class, groupID);

        if (dbUser != null && dbGroup != null) {
            Set<Group> groupSet = dbUser.getGroups();
            if (!groupSet.add(dbGroup)) return null; // already present
        } else return null;

        session.flush();
        session.close();

        return dbUser;
    }

    @Override
    public User removeUserFromGroup(Long userID, Long groupID) {

        Session session = sessionFactory.openSession();

        User dbUser = (User) session.get(User.class, userID);
        Group dbGroup = (Group) session.get(Group.class, groupID);

        if (dbUser != null && dbGroup != null) {
            Set<Group> groupSet = dbUser.getGroups();
            if (!groupSet.remove(dbGroup)) return null; // remove() returns true if Set contained passed object
        } else return null;

        session.flush();
        session.close();

        return dbUser;
    }

    @Override
    public User addUserIntoEvent(Long userID, Long eventID) {

        Session session = sessionFactory.openSession();

        User dbUser = (User) session.get(User.class, userID);
        Event dbEvent = (Event) session.get(Event.class, eventID);

        if (dbUser != null && dbEvent != null) {
            Set<Event> eventSet = dbUser.getEvents();
            if (!eventSet.add(dbEvent)) return null;
        } else return null;

        session.flush();
        session.close();

        return dbUser;
    }

    @Override
    public User removeUserFromEvent(Long userID, Long eventID) {

        Session session = sessionFactory.openSession();

        User dbUser = (User) session.get(User.class, userID);
        Event dbEvent = (Event) session.get(Event.class, eventID);

        if (dbUser != null && dbEvent != null) {
            Set<Event> eventSet = dbUser.getEvents();
            if (!eventSet.remove(dbEvent)) return null;
        } else return null;

        session.flush();
        session.close();

        return dbUser;
    }
}
