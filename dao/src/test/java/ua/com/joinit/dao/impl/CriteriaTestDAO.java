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

import javax.transaction.Transaction;
import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */
public class CriteriaTestDAO extends DAOBaseAppTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void get_event_with_users_set() {
        Gson gson = new Gson();
        Long id = 1L;
        Session session = sessionFactory.openSession();
        Event dbEvent = (Event) session.get(Event.class, id);
        Hibernate.initialize(dbEvent.getUsers().toString());
        session.close();

        System.out.println(dbEvent.getUsers());
//        String json = gson.toJson(dbEvent);

//        System.out.println(json);
    }


}
