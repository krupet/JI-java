package ua.com.joinit.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.BaseAppTest;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */
public class CriteriaTest extends BaseAppTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void join_test() {

        Session session = sessionFactory.openSession();

        Long userID = 1L;
//        User dbUser = (User) session.get(User.class,userID);
        Criteria criteria = session.createCriteria(Event.class);
        criteria.add(Restrictions.eq("events.user_id", userID));
        List list = criteria.list();

        System.out.println(list);
        session.close();
    }

    @Test
    public void many_to_many_test() {

        Session session = sessionFactory.openSession();

        Long userID = 1L;
        User dbUser = (User) session.get(User.class,userID);
//        User dbUser = (User) session.load(User.class, userID);

        System.out.println("---before call---\n");
        System.out.println(dbUser.getEvents());
        System.out.println(dbUser.getGroups());
        session.close();

        System.out.println("--session closed--\n");
//        System.out.println(dbUser);
    }
}
