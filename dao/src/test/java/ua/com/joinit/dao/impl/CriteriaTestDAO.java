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
}
