package ua.com.joinit.service;

import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */
public interface GroupService {

    Group postGroup(Group group);

    Group getGroupById(Long id);

    List<Group> getAllGroups();

    Group updateGroup(Group group);

    Group deleteGroup(Group group);

    Group addEvent(Long groupID, Long eventID);

    Group removeEvent(Long groupID, Long eventID);

    List<User> getAllUsersInAGroupByGroupID(Long groupID);
}
