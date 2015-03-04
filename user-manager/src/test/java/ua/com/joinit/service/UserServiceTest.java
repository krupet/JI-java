package ua.com.joinit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.BaseAppTest;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.mock.UserMockDAO;
import ua.com.joinit.service.mock.UserMockDAOImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by krupet on 3/3/15.
 */
public class UserServiceTest extends BaseAppTest {
    @Autowired
    @Mock
    private UserMockDAO userMockDAO; // why this is not working instead of (1)


    @Autowired
    @InjectMocks
    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this); // this is why!
    }

    @Test
    public void postNewUserAndExpectedIsOk() {
        User user = new User();

//        UserMockDAO userMockDAO = Mockito.mock(UserMockDAOImpl.class); // (1)
        when(userMockDAO.postUser(user)).thenReturn(new User());
        User postedUser = userService.postUser(user);
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
        assertEquals(mockedUser.getId(), postedUser.getId());
    }
}
