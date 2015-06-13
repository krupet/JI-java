package ua.com.joinit.dao;

import ua.com.joinit.entity.Group;

import java.util.List;

/**
 * Created by krupet on 04.06.2015.
 */
public interface GroupDAO {

    Group postGroup(Group group);

    Group getGroupById(Long id);

    List<Group> getAllGroups();

    Group updateGroup(Group group);

    Group deleteGroup(Group group);

    Group addUserToGroupById(Long groupID, Long userID);

    Group deleteUserFromGroupById(Long groupID, Long userID);

    Group addEvent(Long groupID, Long eventID);

    Group removeEvent(Long groupID, Long eventID);
}