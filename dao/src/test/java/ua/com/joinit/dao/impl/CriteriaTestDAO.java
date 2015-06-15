package ua.com.joinit.dao.impl;

import com.google.gson.Gson;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.DAOBaseAppTest;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;

import javax.jws.soap.SOAPBinding;
import javax.transaction.Transaction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */
public class CriteriaTestDAO extends DAOBaseAppTest {

    @Autowired
    private SessionFactory sessionFactory;

//    @Test
//    public void get_event_with_users_set() {
//        Gson gson = new Gson();
//        Long id = 1L;
//        Session session = sessionFactory.openSession();
//        Event dbEvent = (Event) session.get(Event.class, id);
//        Hibernate.initialize(dbEvent.getUsers().toString());
//        session.close();
//
//        System.out.println(dbEvent.getUsers());
////        String json = gson.toJson(dbEvent);
//
////        System.out.println(json);
//    }


    /*
        Old test when Event has users Set
     */
//    @Test
//    @SuppressWarnings(value = "unchecked")
//    public void get_events_list_by_user() {
//        Gson gson = new Gson();
//
//        Session session = sessionFactory.openSession();
//        Criteria criteria = session.createCriteria(Event.class);
//        criteria.createAlias("users", "e");
//        criteria.add(Restrictions.eq("e.id", 3L));
//        ArrayList<Event> events = (ArrayList<Event>) criteria.list();
//
//        session.close();
//        for (Event event: events) {
//            event.setUsers(null);
//        }
//
//        String json = gson.toJson(events);
//
//        System.out.println(events);
//        System.out.println(json);
//    }

    @Test
    public void get_user_with_lists_by_id() {

        Session session = sessionFactory.openSession();
        Long id = 3L;
        User dbUser = (User) session.get(User.class, id);
        Hibernate.initialize(dbUser.getEvents());
        Hibernate.initialize(dbUser.getGroups());
        session.close();

        System.out.println(dbUser);
//        System.out.println(dbUser.getGroups());
//        System.out.println(dbUser.getEvents());
    }

    /*
        get list of users in event
     */
    @SuppressWarnings(value = "unchecked")
    @Test
    public void get_list_of_users_by_event_id() {

        Gson gson = new Gson();
        Session session = sessionFactory.openSession();
        Criteria crt = session.createCriteria(User.class);
        crt.createAlias("events", "e");
        crt.add(Restrictions.eq("e.id", 1L));
        ArrayList<User> users = (ArrayList<User>) crt.list();
        session.close();

        for (User user: users) {
            user.setEvents(null);
            user.setGroups(null);
        }

        String json = gson.toJson(users);
        System.out.println(json);

        System.out.println(users);
    }
}
