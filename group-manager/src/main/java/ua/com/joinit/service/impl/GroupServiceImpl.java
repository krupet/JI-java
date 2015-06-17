package ua.com.joinit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.GroupDAO;
import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.GroupService;

import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDAO groupDAO;

    @Override
    public Group postGroup(Group group) {
        return groupDAO.postGroup(group);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupDAO.getGroupById(id);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupDAO.getAllGroups();
    }

    @Override
    public Group updateGroup(Group group) {
        return groupDAO.updateGroup(group);
    }

    @Override
    public Group deleteGroup(Group group) {
        return groupDAO.deleteGroup(group);
    }

    @Override
    public Group addEvent(Long groupID, Long eventID) {
        return groupDAO.addEvent(groupID, eventID);
    }

    @Override
    public Group removeEvent(Long groupID, Long eventID) {
        return groupDAO.removeEvent(groupID, eventID);
    }

    @Override
    public List<User> getAllUsersInAGroupByGroupID(Long groupID) {
        return groupDAO.getAllUsersInAGroupByGroupID(groupID);
    }
}
