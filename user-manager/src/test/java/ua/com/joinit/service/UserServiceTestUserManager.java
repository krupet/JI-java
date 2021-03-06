package ua.com.joinit.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.UserManagerBaseAppTest;
import ua.com.joinit.dao.UserDAO;
import ua.com.joinit.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
/**
 * Created by krupet on 3/3/15.
 */
public class UserServiceTestUserManager extends UserManagerBaseAppTest {
    @Autowired
    @Mock
    private UserDAO userMockDAO; // why this is not working instead of (1)


    @Autowired
    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // this is why! just forget to add this(
    }

    @Test
    public void postNewUserAndExpectedIsOk() {

        User user = new User();

//        UserMockDAO userMockDAO = Mockito.mock(UserMockDAOImpl.class); // (1)
        when(userMockDAO.postUser(user)).thenReturn(new User());
        User postedUser = userService.postUser(user);
        verify(userMockDAO,times(1)).postUser(user);
        assertNotNull(postedUser);
    }

    @Test
    public void postNewUserAndExpectedHisIdIsNotNull() {

        User user = new User();
        user.setFirstName("test_first_name");
        user.setLastName("test_last_name");
        user.setNickName("test_nick_name");
        user.setEmail("testEmail@gmail.com");
        user.setPhone(1234567890L);
        user.setAboutYourself("test_about_yourself");
        assertEquals(0L, user.getId());

        User mockedUser = new User();
        mockedUser.setFirstName("test_first_name");
        mockedUser.setLastName("test_last_name");
        mockedUser.setNickName("test_nick_name");
        mockedUser.setEmail("testEmail@gmail.com");
        mockedUser.setPhone(1234567890L);
        mockedUser.setAboutYourself("test_about_yourself");
        mockedUser.setId(1L);

        when(userMockDAO.postUser(user)).thenReturn(mockedUser);

        User postedUser = userService.postUser(user);
        verify(userMockDAO, times(1)).postUser(user);
        assertEquals(mockedUser.getId(), postedUser.getId());
    }
//
    @Test
    public  void update_existing_user_and_expected_is_not_null() {

        User mockedUser = new User();
        User updUser = new User();
        when(userMockDAO.updateUser(any(User.class))).thenReturn(mockedUser);
        User updatedUser = userService.updateUser(updUser);
        verify(userMockDAO,times(1)).updateUser(any(User.class));

        assertNotNull(updatedUser);
    }
//
    @Test
    public  void putUserByIdAndExpectedIsOk() {

        User mockedUser = new User();
        mockedUser.setFirstName("test_first_name");
        mockedUser.setLastName("test_last_name");
        mockedUser.setNickName("test_nick_name");
        mockedUser.setEmail("testEmail@gmail.com");
        mockedUser.setPhone(1234567890L);
        mockedUser.setAboutYourself("test_about_yourself");
        mockedUser.setId(2L);

        User updUser = new User();
        when(userMockDAO.updateUser(any(User.class))).thenReturn(mockedUser);
        User updatedUser = userService.updateUser(updUser);
        verify(userMockDAO, times(1)).updateUser(any(User.class));

        assertEquals("test_first_name", updatedUser.getFirstName());
        assertEquals("test_nick_name", updatedUser.getNickName());
        assertEquals(2L, updatedUser.getId());
    }

    @Test
    public void get_existing_user_and_expected_is_ok() {

        User retrievedUser = new User();
        Long id = 1L;
        when(userMockDAO.getUser(id)).thenReturn(retrievedUser);
        User retrieved = userService.getUser(id);
        verify(userMockDAO,times(1)).getUser(id);

        assertNotNull(retrieved);
    }

    @Test
    public void get_existing_user_and_expected_is_equality() {
        User mockedUser = new User();
        mockedUser.setFirstName("test_first_name");
        mockedUser.setLastName("test_last_name");
        mockedUser.setNickName("test_nick_name");
        mockedUser.setEmail("testEmail@gmail.com");
        mockedUser.setPhone(1234567890L);
        mockedUser.setAboutYourself("test_about_yourself");
        Long id = 2L;
        mockedUser.setId(id);

        when(userMockDAO.getUser(id)).thenReturn(mockedUser);
        User retrievedUser = userService.getUser(id);
        verify(userMockDAO, times(1)).getUser(id);

        assertEquals(2L, retrievedUser.getId());
        assertEquals("test_first_name", retrievedUser.getFirstName());
        assertEquals("test_nick_name", retrievedUser.getNickName());
    }

    @Test
    public void get_list_of_all_users_and_expected_not_empty_list() {

        User mockedUser = new User();
        mockedUser.setFirstName("test_first_name");
        mockedUser.setLastName("test_last_name");
        mockedUser.setNickName("test_nick_name");
        mockedUser.setEmail("testEmail@gmail.com");
        mockedUser.setPhone(1234567890L);
        mockedUser.setAboutYourself("test_about_yourself");
        Long id = 1L;
        mockedUser.setId(id);

        List<User> mockUsersList = new ArrayList<>();
        mockUsersList.add(mockedUser);

        when(userMockDAO.getAllUsers()).thenReturn(mockUsersList);
        List<User> users = userService.getAllUsers();
        verify(userMockDAO, times(1)).getAllUsers();

        assertNotNull(users);
        assertNotNull(users.get(0));
        assertEquals(1L, users.get(0).getId());
    }

    @Test
    public void get_user_by_email_and_expected_is_ok() {

        String email = "email@gmail.com";
        User mockedUser = new User();
        mockedUser.setId(1L);
        mockedUser.setEmail(email);

        when(userMockDAO.getUserByEmail(email)).thenReturn(mockedUser);
        User retrievedUser = userService.getUserByEmail(email);
        assertNotNull(retrievedUser);
        verify(userMockDAO, times(1)).getUserByEmail(email);
    }

    @Test
    public void add_user_into_group() {

        when(userMockDAO.addUserIntoGroup(anyLong(), anyLong())).thenReturn(new User());
        User retrievedUser = userService.addUserIntoGroup(1L, 1L);
        verify(userMockDAO, times(1)).addUserIntoGroup(anyLong(), anyLong());
        assertNotNull(retrievedUser);
    }

    @Test
    public void remove_user_from_group() {

        when(userMockDAO.removeUserFromGroup(anyLong(), anyLong())).thenReturn(new User());
        User retrievedUser = userService.removeUserFromGroup(1L, 1L);
        verify(userMockDAO, times(1)).removeUserFromGroup(anyLong(), anyLong());
        assertNotNull(retrievedUser);
    }

    @Test
    public void add_user_into_event() {

        when(userMockDAO.addUserIntoEvent(anyLong(), anyLong())).thenReturn(new User());
        User retrievedUser = userService.addUserIntoEvent(1L, 1L);
        verify(userMockDAO, times(1)).addUserIntoEvent(anyLong(), anyLong());
        assertNotNull(retrievedUser);
    }

    @Test
    public void remove_user_from_event() {

        when(userMockDAO.removeUserFromEvent(anyLong(), anyLong())).thenReturn(new User());
        User retrievedUser = userService.removeUserFromEvent(1L, 1L);
        verify(userMockDAO, times(1)).removeUserFromEvent(anyLong(), anyLong());
        assertNotNull(retrievedUser);
    }
}
