package ua.com.joinit.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.GroupDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by krupet on 04.06.2015.
 */
public class GroupDAOImpl implements GroupDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Group postGroup(Group group) {

        Session session = sessionFactory.openSession();
        Long groupID = (Long) session.save(group);
        Group dbGroup = (Group) session.get(Group.class, groupID); // check whether the object is stored in db
        session.close();
        return dbGroup;
    }

    @Override
    public Group getGroupById(Long id) {

        Session session = sessionFactory.openSession();
        Group dbGroup = (Group) session.get(Group.class, id);
        Hibernate.initialize(dbGroup.getEvents());

        session.close();

        return dbGroup; //TODO: maybe here should use defensive copying to avoid lazy initialization exception or use DTO?
    }

    /*
        to reduce db loading this method will return list of groups without events list
     */
    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Group> getAllGroups() {

        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Group.class);
        List<Group> dbGroups = (ArrayList<Group>) criteria.list();
        session.close();

        for (Group group: dbGroups) group.setEvents(null);

        return dbGroups;
    }

    @Override
    public Group updateGroup(Group group) {

        Long id = group.getId();
        Session session = sessionFactory.openSession();
        session.update(group);
        session.flush();
        Group dbGroup = (Group) session.get(Group.class, id);
        if (dbGroup != null) {
            dbGroup.getDescription();
        }

        session.close();

        return dbGroup;
    }

    @Override
    public Group deleteGroup(Group group) {
        Long id = group.getId();
        Session session = sessionFactory.openSession();
        session.delete(group);
        session.flush();
        Group dbGroup = (Group) session.get(Group.class, id);
        session.close();

        return dbGroup;
    }

    @Override
    public Group addEvent(Long groupID, Long eventID) {

        Session session = sessionFactory.openSession();
        Event dbEvent = (Event) session.get(Event.class, eventID); // Ensuring that given event exists in db
        Group dbGroup = (Group) session.get(Group.class, groupID); // Ensuring that given group exists in db

        if (dbEvent != null && dbGroup != null) {
            Set<Event> eventSet = dbGroup.getEvents();
            if (!eventSet.add(dbEvent)) return null; // already present in the Set.
        } else return null;

        session.flush();
        session.close();

        return dbGroup;
    }

    @Override
    public Group removeEvent(Long groupID, Long eventID) {

        Session session = sessionFactory.openSession();
        Event dbEvent = (Event) session.get(Event.class, eventID); // Ensuring that given event exists in db
        Group dbGroup = (Group) session.get(Group.class, groupID); // Ensuring that given group exists in db

        if (dbEvent != null && dbGroup != null) {
            Set<Event> eventSet = dbGroup.getEvents();
            if (eventSet.contains(dbEvent)) {
                eventSet.remove(dbEvent);
            } else return null; // TODO: replace with exception throwing with reason message
        } else return null;

        session.flush();
        session.close();

        return dbGroup;
    }

    /*
        to reduce db loading method returns
        users without events and groups lists
     */
    @SuppressWarnings(value = "unchecked")
    @Override
    public List<User> getAllUsersInAGroupByGroupID(Long groupID) {

        Session session = sessionFactory.openSession();
        Group dbGroup = (Group) session.get(Group.class, groupID);

        List<User> users = null;
        if (dbGroup != null) {
            Criteria criteria = session.createCriteria(User.class);
            criteria.createAlias("groups", "g");
            criteria.add(Restrictions.eq("g.id", groupID));

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
