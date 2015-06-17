package ua.com.joinit.mock;

import ua.com.joinit.dao.GroupDAO;
import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */
public class GroupMockDAOImpl implements GroupDAO {
    @Override
    public Group postGroup(Group group) {
        return null;
    }

    @Override
    public Group getGroupById(Long id) {
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public Group updateGroup(Group group) {
        return null;
    }

    @Override
    public Group deleteGroup(Group group) {
        return null;
    }

    @Override
    public Group addEvent(Long groupID, Long eventID) {
        return null;
    }

    @Override
    public Group removeEvent(Long groupID, Long eventID) {
        return null;
    }

    @Override
    public List<User> getAllUsersInAGroupByGroupID(Long groupID) {
        return null;
    }
}
