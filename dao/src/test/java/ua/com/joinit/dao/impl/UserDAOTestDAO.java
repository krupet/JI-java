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

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by krupet on 15.03.2015.
 */
public class UserDAOTestDAO extends DAOBaseAppTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private EventDAO eventDAO;

    @Test
    public void post_new_user() {

        Long emailConstant = new Date().getTime();
        String email = "" + emailConstant + "@gmail.com";

        User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");

        User dbUser = userDAO.postUser(testUser);
        assertNotNull(dbUser.getId());
    }

    @Test(expected = Exception.class)
    public void post_user_with_already_existing_email_and_exception_expected() {

        Long emailConstant = new Date().getTime();
        String email = "" + emailConstant + "@gmail.com";

        User user1 = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");
        User user2 = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");

        User dbUser1 = userDAO.postUser(user1);
        User dbUser2 = userDAO.postUser(user2);
    }

    @Test
    public void get_user_by_id() {

        Long emailConstant = new Date().getTime();
        String email = "" + emailConstant + "@gmail.com";
        User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");
        User postedUser = userDAO.postUser(testUser);
        assertNotNull(postedUser.getId());

        Long id = postedUser.getId();
        User retrievedUser = userDAO.getUser(id);
        assertNotNull(retrievedUser);
    }

    @Test
    public void get_user_by_email_and_expected_is_ok() {

        Long emailConstant = new Date().getTime();
        String email = "" + emailConstant + "@gmail.com";
        User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");
        User postedUser = userDAO.postUser(testUser);
        assertNotNull(postedUser.getId());

        String testEmail = postedUser.getEmail();
        User user = userDAO.getUserByEmail(testEmail);
        assertNotNull(user);
    }

    @Test
    public void get_user_by_not_existing_email_and_expected_is_null() {

        String email = "noEmail@gmail.com";
        User user = userDAO.getUserByEmail(email);
        assertNull(user);
    }

    @Test
    public void get_list_of_all_users() {

        List dbUsers = userDAO.getAllUsers();
        assertNotNull(dbUsers);
        assertNotNull(dbUsers.get(0));
    }

    @Test
    public void update_user() {

        Long emailConstant = new Date().getTime();
        String email = "" + emailConstant + "@gmail.com";

        User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");
        User postedUser = userDAO.postUser(testUser);
        assertNotNull(postedUser.getId());

        Long id = postedUser.getId();

        User updUser = new User("upd_test_f_name", "upd_test_last_name", "upd_test_nickname",
                email, 987654321L, "upd_test_desc");
        updUser.setId(id);

        // TODO: delete queries firing against db join tables because of null fields events and groups!!
        User dbUser = userDAO.updateUser(updUser);
        assertNotNull(dbUser);
    }

    @Test
    public void add_user_into_group_and_expected_is_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;
        String email = "" + creationTime + "@gmail.com";

        User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");

        User postedUser = userDAO.postUser(testUser);
        assertNotNull(postedUser.getId());

        Group group = new Group(groupName, "test_group_description", creationTime);
        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup.getId());

        Long userID = postedUser.getId();
        Long groupID = postedGroup.getId();

        User retrievedUser = userDAO.addUserIntoGroup(userID, groupID);
        assertNotNull(retrievedUser);
    }

    @Test
    public void add_not_existing_user_in_non_existing_group_and_null_is_expected() {

        Long userID = 333333L;
        Long groupID = 222222L;

        User dbUser = userDAO.addUserIntoGroup(userID, groupID);
        assertNull(dbUser);
    }

    @Test
    public void delete_user_from_group_and_expected_is_ok() {

        Long creationTime = new Date().getTime();
        String groupName = "group_" + creationTime;
        String email = "" + creationTime + "@gmail.com";

        User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");

        User postedUser = userDAO.postUser(testUser);
        assertNotNull(postedUser.getId());

        Group group = new Group(groupName, "test_group_description", creationTime);
        Group postedGroup = groupDAO.postGroup(group);
        assertNotNull(postedGroup.getId());

        Long userID = postedUser.getId();
        Long groupID = postedGroup.getId();

        User retrievedUser1 = userDAO.addUserIntoGroup(userID, groupID);
        assertNotNull(retrievedUser1);

        User retrievedUser2 = userDAO.removeUserFromGroup(userID, groupID);
        assertNotNull(retrievedUser2);
    }

    @Test
    public void delete_not_existing_user_from_not_existing_group_and_null_is_expected() {

        Long userID = 333333L;
        Long groupID = 222222L;

        User dbUser = userDAO.removeUserFromGroup(userID, groupID);
        assertNull(dbUser);
    }

    @Test
    public void add_user_into_event_and_expected_is_ok() {

        Long date = new Date().getTime();
        String email = "" + date + "@gmail.com";

        User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");
        User postedUser = userDAO.postUser(testUser);
        assertNotNull(postedUser.getId());

        Event event = new Event("test_event_name", "test_event_description", date, date);
        Event postedEvent = eventDAO.postEvent(event);
        assertNotNull(postedEvent);

        Long userID = postedUser.getId();
        Long eventID = postedEvent.getId();

        User retrievedUser = userDAO.addUserIntoEvent(userID, eventID);
        assertNotNull(retrievedUser);
    }

    @Test
    public void add_not_existing_user_in_non_existing_event_and_null_is_expected() {

        Long userID = 333333L;
        Long eventID = 222222L;

        User dbUser = userDAO.addUserIntoEvent(userID, eventID);
        assertNull(dbUser);
    }

    @Test
    public void delete_user_from_event_and_expected_is_ok() {

        Long date = new Date().getTime();
        String email = "" + date + "@gmail.com";

        User testUser = new User("test_f_name", "test_last_name", "test_nickname",
                email, 1234567890L, "test_desc");
        User postedUser = userDAO.postUser(testUser);
        assertNotNull(postedUser.getId());

        Event event = new Event("test_event_name", "test_event_description", date, date);
        Event postedEvent = eventDAO.postEvent(event);
        assertNotNull(postedEvent);

        Long userID = postedUser.getId();
        Long eventID = postedEvent.getId();

        User retrievedUser1 = userDAO.addUserIntoEvent(userID, eventID);
        assertNotNull(retrievedUser1);

        User retrievedUser2 = userDAO.removeUserFromEvent(userID, eventID);
        assertNotNull(retrievedUser2);
    }

    @Test
    public void delete_not_existing_user_from_non_existing_event_and_null_is_expected() {

        Long userID = 333333L;
        Long eventID = 222222L;

        User dbUser = userDAO.removeUserFromEvent(userID, eventID);
        assertNull(dbUser);
    }
}
