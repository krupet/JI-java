package ua.com.joinit.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
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

    /*
        to reduce db loading method returns
        users without events and groups lists
     */
    @Override
    @SuppressWarnings(value = "unchecked")
    public List<User> getListOfUsersByEventID(Long eventID) {

        Session session = sessionFactory.openSession();
        Event dbEvent = (Event) session.get(Event.class, eventID);

        List<User> users = null;
        if (dbEvent != null) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.createAlias("events", "e");
            criteria.add(Restrictions.eq("e.id", eventID));
            users = (ArrayList<User>) criteria.list();
        } else return users;

        session.close();

        if (users != null) {
            for (User user: users) {
                user.setEvents(null);
                user.setGroups(null);
            }
        }

        return users;
    }
}
