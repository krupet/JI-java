package ua.com.joinit.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.Set;

/**
 * Created by krupet on 08.06.2015.
 */
public class EventDAOImpl implements EventDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Event postEvent(Event event) {

        Session session = sessionFactory.openSession();
        Long eventID = (Long) session.save(event);
        Event dbEvent = (Event) session.get(Event.class, eventID);
        session.close();

        return dbEvent;
    }

    @Override
    public Event updateEvent(Event event) {
        Long eventID = event.getId();
        Session session = sessionFactory.openSession();
        session.update(event);
        session.flush();
        Event dbEvent = (Event) session.get(Event.class, eventID);
        session.close();

        return dbEvent;
    }

    @Override
    public Event getEventById(Long id) {

        Session session = sessionFactory.openSession();
        Event dbEvent = (Event) session.get(Event.class, id);
        session.close();

        return dbEvent;
    }

    @Override
    public Event deleteEvent(Event event) {

        Long eventID = event.getId();
        Session session = sessionFactory.openSession();
        session.delete(event);
        session.flush();
        Event dbEvent = (Event) session.get(Event.class, eventID); // must to be null if delete op. is successful
        session.close();

        return dbEvent;
    }

    @Override
    public Event addUser(Long eventID, Long userID) {

        Session session = sessionFactory.openSession();
        Event dbEvent = (Event) session.get(Event.class, eventID);
        User dbUser = (User) session.get(User.class, userID);

        if (dbEvent != null && dbUser != null) {
            Set<User> userSet = dbEvent.getUsers();
            if (!userSet.contains(dbUser)) { // redundant? maybe.
                userSet.add(dbUser);
            } else return null;
        } else return null;

        session.flush();
        session.close();

        return dbEvent;
    }

    @Override
    public Event deleteUser(Long eventID, Long userID) {

        Session session = sessionFactory.openSession();
        Event dbEvent = (Event) session.get(Event.class, eventID);
        User dbUser = (User) session.get(User.class, userID);

        if (dbEvent != null && dbUser != null) {
            Set<User> userSet = dbEvent.getUsers();
            if (userSet.contains(dbUser)) {
                userSet.remove(dbUser);
            } else return null;
        } else return null;

        session.flush();
        session.close();

        return dbEvent;
    }
}
