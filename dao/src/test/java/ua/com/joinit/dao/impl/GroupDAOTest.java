package ua.com.joinit.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.BaseAppTest;
import ua.com.joinit.dao.GroupDAO;
import ua.com.joinit.entity.Group;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by krupet on 04.06.2015.
 */
public class GroupDAOTest extends BaseAppTest {

    @Autowired
    private GroupDAO groupDAO;

    @Test
    public void post_group_and_expected_ok() {

        Group group = new Group();
        group.setName("test_group_name");
        group.setDescription("test_group_description");
        group.setCreationDate(new Date().getTime());
        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup.getId());
    }

    @Test(expected = Exception.class)
    public void post_not_valid_group_and_exception_expected() {
        Group group = new Group();
        group.setName("test_group_name");
//        group.setDescription("test_group_description");
        group.setCreationDate(new Date().getTime());
        Group postedGroup = groupDAO.postGroup(group);
//        assertNull(postedGroup);
    }

    @Test
    public void get_group_by_id_without_users_list_and_expected_is_ok() {
        Long id = 2L;
        Group dbGroup = groupDAO.getGroupById(id);
        assertNotNull(dbGroup);
        assertNotNull(dbGroup.getId());
        assertNotNull(dbGroup.getName());
        assertNotNull(dbGroup.getDescription());
        assertNotNull(dbGroup.getCreationDate());
//        assertNull(dbGroup.getUsers()); //TODO: lazy initialization exception!
    }

    @Test
    public void get_list_of_all_groups_and_expected_is_ok() {
        List dbGroups = groupDAO.getAllGroups();
        assertNotNull(dbGroups);
        assertFalse(dbGroups.isEmpty());
    }

    @Test
    public void update_group_and_expected_is_ok() {
        Group group = new Group();
        group.setId(2L);
        group.setName("update_test_group_name");
        group.setDescription("update_test_group_description");
        group.setCreationDate(new Date().getTime());

        Group dbGroup = groupDAO.updateGroup(group);

        assertNotNull(dbGroup);
    }

    @Test(expected = Exception.class)
    public void update_not_existing_group_and_exception_expected() {
        Group group = new Group();
        group.setId(1101L);
        group.setName("update_test_group_name");
        group.setDescription("update_test_group_description");
        group.setCreationDate(new Date().getTime());

        Group dbGroup = groupDAO.updateGroup(group);
        assertNull(dbGroup);
    }

    @Test
    public void delete_existing_group_and_expected_is_ok() {
        Group group = new Group();
        group.setId(3L);
        group.setName("update_test_group_name");
        group.setDescription("update_test_group_description");

        Group dbGroup = groupDAO.deleteGroup(group);
        assertNull(dbGroup);
    }

    @Test(expected = Exception.class)
    public void delete_not_existing_group_and_exception_expected() {

        Group group = new Group();
        group.setId(1111L);
        group.setName("update_test_group_name");
        group.setDescription("update_test_group_description");

        Group dbGroup = groupDAO.deleteGroup(group);
        assertNull(dbGroup);
    }

    @Test
    public void add_user_to_group_by_id_and_ok_expected() {
        Long groupID = 2L;
        Long userID = 1L;
        Group dbGroup = groupDAO.addUserToGroupById(groupID, userID);
        assertNotNull(dbGroup);
    }

    @Test
    public void delete_user_from_group_and_expected_is_ok() {
        Long groupID = 1L;
        Long userID = 3L;
        Group dbGroup = groupDAO.deleteUserFromGroupById(groupID, userID);
        assertNull(dbGroup);
    }

    @Test
    public void delete_not_existing_user_from_group_and_expected_is_null() {

        Long groupID = 1L;
        Long userID = 3333L;

        Group dbGroup = groupDAO.deleteUserFromGroupById(groupID, userID);
        assertNull(dbGroup);
    }

    @Test
    public void add_event_in_group_and_expected_is_ok() {

        Long groupID = 2L;
        Long eventID = 3L;

        Group dbGroup = groupDAO.addEvent(groupID, eventID);
        assertNotNull(dbGroup);
    }

    @Test
    public void add_not_existing_event_in_nonexistent_group_and_expected_is_null() {

        Long groupID = 2222L;
        Long eventID = 3333L;

        Group dbGroup = groupDAO.addEvent(groupID, eventID);
        assertNull(dbGroup);
    }

    @Test
    public void delete_event_from_group_and_expected_is_ok() {

        Long groupID = 2L;
        Long eventID = 1L;

        Group dbGroup = groupDAO.removeEvent(groupID, eventID);
        assertNotNull(dbGroup);
    }

    @Test
    public void delete_nonexistent_event_from_nonexistent_group_and_expected_is_null() {

        Long groupID = 2222L;
        Long eventID = 1111L;

        Group dbGroup = groupDAO.removeEvent(groupID, eventID);
        assertNull(dbGroup);
    }

}
