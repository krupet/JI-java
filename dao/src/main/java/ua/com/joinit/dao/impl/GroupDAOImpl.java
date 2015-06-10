package ua.com.joinit.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.GroupDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;

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
        Group dbGroup = (Group) session.load(Group.class, id);
        dbGroup.getName();
        dbGroup.getDescription();

        session.close();

        return dbGroup; //TODO: maybe here should use defensive copying to avoid lazy initialization exception or use DTO?
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Group> getAllGroups() {

        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Group.class);
        List<Group> dbGroups = criteria.list();
        session.close();

        return dbGroups;
    }

    @Override
    public Group updateGroup(Group group) {

        Long id = group.getId();
        Session session = sessionFactory.openSession();
        session.update(group);
        session.flush();
        Group dbGroup = (Group) session.get(Group.class, id);
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
    public Group addUserToGroupById(Long groupID, Long userID) {

        Session session = sessionFactory.openSession();
        User dbUser = (User) session.get(User.class, userID); // Ensuring that given user exists in db
        Group dbGroup = (Group) session.get(Group.class, groupID); // Ensuring that given group exists in db

        if (dbUser != null && dbGroup != null) {
            Set<User> userSet = dbGroup.getUsers();
            userSet.add(dbUser);
        } else return null; // returning null if operation isn't successful

        session.flush();
        session.close();

        return dbGroup;
    }

    @Override
    public Group deleteUserFromGroupById(Long groupID, Long userID) {

        Session session = sessionFactory.openSession();
        User dbUser = (User) session.get(User.class, userID); // Ensuring that given user exists in db
        Group dbGroup = (Group) session.get(Group.class, groupID); // Ensuring that given group exists in db

        if (dbUser != null && dbGroup != null) {
            Set<User> userList = dbGroup.getUsers();
            if (userList.contains(dbUser)) {
                userList.remove(dbUser);
            } else return null; // TODO: replace with exception throwing with reason message
        } else return null; // returning null if operation isn't successful

        session.flush();
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
            eventSet.add(dbEvent);
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
}
