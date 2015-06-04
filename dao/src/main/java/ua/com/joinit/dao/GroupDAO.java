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
}
