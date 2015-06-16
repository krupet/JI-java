package ua.com.joinit.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.DAOBaseAppTest;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.dao.GroupDAO;
import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by krupet on 04.06.2015.
 */
public class GroupDAOTest extends DAOBaseAppTest {

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void post_new_group_with_unique_name_and_expected_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;

        Group group = new Group(groupName, "test_group_description", creationTime);

        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup.getId());
    }

    @Test(expected = Exception.class)
    public void post_new_group_with_non_unique_name_and_expected_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;

        Group group1 = new Group(groupName, "test_group_description", creationTime);
        Group group2 = new Group(groupName, "test_group_description", creationTime);

        Group postedGroup1 = groupDAO.postGroup(group1);
        assertNotNull(postedGroup1.getId());
        Group postedGroup2 = groupDAO.postGroup(group2);
    }

    @Test(expected = Exception.class)
    public void post_not_valid_group_without_some_not_null_property_and_exception_expected() {

        Group group = new Group();
        group.setName("test_group_name");
        group.setDescription(null);
        group.setCreationDate(new Date().getTime());
        Group postedGroup = groupDAO.postGroup(group);
    }

    @Test
    public void get_group_by_id_and_expected_is_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;

        Group group = new Group(groupName, "test_group_description", creationTime);
        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup);

        Long id = postedGroup.getId();

        Group dbGroup = groupDAO.getGroupById(id);
        assertNotNull(dbGroup);
        assertNotNull(dbGroup.getId());
        assertNotNull(dbGroup.getName());
        assertNotNull(dbGroup.getDescription());
        assertNotNull(dbGroup.getCreationDate());
    }

    @Test(expected = Exception.class)
    public void get_group_by_not_existing_id_and_exception_expected() {

        Long id = 22222222L;

        Group dbGroup = groupDAO.getGroupById(id);
    }

    @Test
    public void get_list_of_all_groups_and_expected_is_ok() {

        List dbGroups = groupDAO.getAllGroups();
        assertNotNull(dbGroups);
        assertFalse(dbGroups.isEmpty());
    }

    @Test
    public void update_group_and_expected_is_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;

        Group group = new Group(groupName, "test_group_description", creationTime);
        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup);

        Long id = postedGroup.getId();
        Group updGroup = new Group("upd_" + groupName, "upd_test_group_name", creationTime);
        updGroup.setId(id);

        Group dbGroup = groupDAO.updateGroup(updGroup);

        assertNotNull(dbGroup);
    }

    @Test(expected = Exception.class)
    public void update_not_existing_group_and_exception_expected() {

        Long id = 2222222L;
        Group group = new Group("update_test_group_name", "update_test_group_description",
                                new Date().getTime());
        group.setId(id);

        Group dbGroup = groupDAO.updateGroup(group);
        assertNull(dbGroup);
    }

    @Test
    public void delete_existing_group_and_expected_is_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;

        Group group = new Group(groupName, "test_group_description", creationTime);
        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup);

        Long id = postedGroup.getId();
        Group deleteGroup = new Group("del_" + groupName, "del_test_group_name", creationTime);
        deleteGroup.setId(id);

        Group dbGroup = groupDAO.deleteGroup(deleteGroup);
        assertNull(dbGroup);
    }

    @Test(expected = Exception.class)
    public void delete_not_existing_group_and_exception_expected() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;
        Long id = 222222L;

        Group group = new Group(groupName, "test_descr", creationTime);
        group.setId(id);

        Group dbGroup = groupDAO.deleteGroup(group);
    }

    @Test
    public void add_event_in_group_and_expected_is_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "test_group_" + creationTime;
        Group group = new Group(groupName, "test_add_event_in_group", creationTime);
        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup);

        Long date = new Date().getTime();
        Event event = new Event("test_event_name", "test_add_event_in_group", date, date);
        Event postedEvent = eventDAO.postEvent(event);
        assertNotNull(postedEvent);

        Long groupID = postedGroup.getId();
        Long eventID = postedEvent.getId();

        Group dbGroup = groupDAO.addEvent(groupID, eventID);
        assertNotNull(dbGroup);
    }

    @Test
    public void add_not_existing_event_in_nonexistent_group_and_expected_is_null() {

        Long groupID = 222222L;
        Long eventID = 333333L;

        Group dbGroup = groupDAO.addEvent(groupID, eventID);
        assertNull(dbGroup);
    }

    @Test
    public void delete_event_from_group_and_expected_is_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "test_group_" + creationTime;
        Group group = new Group(groupName, "test_delete_event_from_group", creationTime);
        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup);

        Long date = new Date().getTime();
        Event event = new Event("test_event_name", "test_delete_event_from_group", date, date);
        Event postedEvent = eventDAO.postEvent(event);
        assertNotNull(postedEvent);

        Long groupID = postedGroup.getId();
        Long eventID = postedEvent.getId();

        Group dbGroup1 = groupDAO.addEvent(groupID, eventID);
        assertNotNull(dbGroup1);

        Group dbGroup2 = groupDAO.removeEvent(groupID, eventID);
        assertNotNull(dbGroup2);
    }

    @Test
    public void delete_nonexistent_event_from_nonexistent_group_and_expected_is_null() {

        Long groupID = 222222L;
        Long eventID = 111111L;

        Group dbGroup = groupDAO.removeEvent(groupID, eventID);
        assertNull(dbGroup);
    }

    @Test
    public void get_list_of_users_by_group_id() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;
        Group dbGroup = new Group(groupName, "test_get_users_of_group", creationTime);
        Group postedGroup = groupDAO.postGroup(dbGroup);
        assertNotNull(postedGroup);

        Long groupID = dbGroup.getId();

        List<Long> userIds = new ArrayList<>();
        Long userID;
        int numberOfUsers = 4;
        for (int i = 0; i < numberOfUsers; i++) {

            Long emailConstant = new Date().getTime();
            String email = "" + emailConstant + "@gmail.com";
            User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                    email, 1234567890L, "test_desc");
            User postedUser = userDAO.postUser(testUser);
            assertNotNull(postedUser);

            userID = postedUser.getId();
            userIds.add(userID);

            User dbUser = userDAO.addUserIntoGroup(userID, groupID);
            assertNotNull(dbUser);
        }

        List<User> groupUsers = groupDAO.getAllUsersInAGroupByGroupID(groupID);
        assertNotNull(groupUsers);
    }

    @Test
    public void get_users_by_not_existing_group_id_and_expected_is_null() {

        Long groupID = 2222222L;
        List<User> groupUsers = groupDAO.getAllUsersInAGroupByGroupID(groupID);
        assertNull(groupUsers);
    }
}
