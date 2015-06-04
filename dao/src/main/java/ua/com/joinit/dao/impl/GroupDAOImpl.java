package ua.com.joinit.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.GroupDAO;
import ua.com.joinit.entity.Group;

import java.util.ArrayList;
import java.util.List;

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
//        Group dbGroup = (Group) session.load(Group.class, groupID); // loads from cache
        Group dbGroup = (Group) session.get(Group.class, groupID); // check whether th e object is stored in db
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

        return dbGroup;
    }
}
