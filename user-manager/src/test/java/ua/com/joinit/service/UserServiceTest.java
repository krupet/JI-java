package ua.com.joinit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.BaseAppTest;
import ua.com.joinit.DAO.UserDAO;
import ua.com.joinit.entity.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by krupet on 3/3/15.
 */
public class UserServiceTest extends BaseAppTest {
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
        User user = new User("name", "nickName");
        assertEquals(null, user.getId());

//        UserMockDAO userMockDAO = Mockito.mock(UserMockDAOImpl.class);
        User mockedUser = new User("name", "nickName");
        mockedUser.setId(1l);
        when(userMockDAO.postUser(user)).thenReturn(mockedUser);

        User postedUser = userService.postUser(user);
        verify(userMockDAO, times(1)).postUser(user);
        assertEquals(mockedUser.getId(), postedUser.getId());
    }

    @Test
    public  void update_existing_user_and_expected_is_not_null() {
        Long id = 1l;
        User mockedUser = new User();
        User updUser = new User();
        when(userMockDAO.updateUser(id, any(User.class))).thenReturn(mockedUser);
        User updatedUser = userService.updateUser(id, updUser);
        verify(userMockDAO,times(1)).updateUser(id, any(User.class));

        assertNotNull(updatedUser);
    }

    @Test
    public  void putUserByIdAndExpectedIsOk() {
        User putUser = new User("name1", "nickName1");
        putUser.setId(1l);

        User mockedUser = new User("name2", "nickName2");
        mockedUser.setId(2l);
        Long id = 1l;
        User updUser = new User();
        when(userMockDAO.updateUser(id, any(User.class))).thenReturn(mockedUser);
        User updatedUser = userService.updateUser(id, updUser);
        verify(userMockDAO, times(1)).updateUser(id, any(User.class));

        assertEquals("name2", updatedUser.getName());
        assertEquals("nickName2", updatedUser.getNickName());
        assertEquals(2l, (long) updatedUser.getId());
    }

    @Test
    public void get_existing_user_and_expected_is_ok() {
        User retrievedUser = new User();
        Long id = 1l;
        when(userMockDAO.getUser(id)).thenReturn(retrievedUser);
        User retrieved = userService.getUser(id);
        verify(userMockDAO,times(1)).getUser(id);

        assertNotNull(retrieved);
    }

    @Test
    public void get_existing_user_and_expected_is_equality() {
        User mockedUser = new User("mock", "mock");
        Long id = 2l;
        mockedUser.setId(id);
        when(userMockDAO.getUser(id)).thenReturn(mockedUser);
        User retrievedUser = userService.getUser(id);
        verify(userMockDAO, times(1)).getUser(id);

        assertEquals(2l, (long) retrievedUser.getId());
        assertEquals("mock", retrievedUser.getName());
        assertEquals("mock", retrievedUser.getNickName());
    }

    @Test
    public void delete_user_and_expected_is_ok() {
        User mockUser = new User();
        Long id = 2l;
        when(userMockDAO.deleteUser(id)).thenReturn(mockUser);
        User deletedUSer = userService.deleteUser(id);
        verify(userMockDAO, times(1)).deleteUser(id);

        assertNotNull(deletedUSer);
    }

    @Test
    public void delete_existing_user_and_expected_is_equality() {
        User mockUser =new User("mock", "mock");
        Long id = 2l;
        mockUser.setId(id);
        when(userMockDAO.deleteUser(id)).thenReturn(mockUser);
        User deletedUser = userService.deleteUser(id);
        verify(userMockDAO, times(1)).deleteUser(id);

        assertEquals(2l, (long) deletedUser.getId());
        assertEquals("mock", deletedUser.getName());
        assertEquals("mock", deletedUser.getNickName());
    }
}
