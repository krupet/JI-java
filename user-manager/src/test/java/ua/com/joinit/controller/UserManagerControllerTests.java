package ua.com.joinit.controller;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ua.com.joinit.UserManagerBaseAppTest;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by krupet on 25.02.2015.
 */
public class UserManagerControllerTests extends UserManagerBaseAppTest {

    public static final String URL_BASE = "/users";

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    @Mock
    private UserService userService;

    @Autowired
    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        /*
            forget wtf is this and why it is here?? don`t worry and look here:
            http://www.petrikainulainen.net/programming/spring-framework/unit-testing-of-spring-mvc-controllers-configuration/
         */
        this.mockMvc = standaloneSetup(userController).build(); //stand alone set up for testing only one controller
//        this.mockMvc = webAppContextSetup(this.wac).build(); // could test all controllers in this "wac"
    }

    @Test
    public void simple() throws Exception {
        mockMvc.perform(get(URL_BASE + "/say_hi"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_user_by_id_and_expected_is_ok200() throws Exception {

        Long id = 2L;
        when(userService.getUser(id)).thenReturn(new User());

        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect(status().is(200));

        verify(userService, times(1)).getUser(id);
    }

    @Test
    public void get_user_by_id_and_expected_is_valid_user() throws Exception {

        Long id = 1234567L;
        User mockUser = new User("test_first_name", "test_last_name", "test_nick_name", "testEmail@gmail.com",
                1234567890L, "test_about_yourself");
        mockUser.setId(id);

        when(userService.getUser(id)).thenReturn(mockUser);
        Gson gson = new Gson();
        String expectedJsonUser = gson.toJson(mockUser);

        mockMvc.perform(get(URL_BASE + "/" + id.toString()))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedJsonUser));

        verify(userService, times(1)).getUser(id);
    }

    @Test
    public void post_user_and_expected_is_ok() throws Exception {

        User postUser = new User();
        Gson gson = new Gson();
        String content = gson.toJson(postUser);

        when(userService.postUser(any(User.class))).thenReturn(new User());

        mockMvc.perform(post(URL_BASE).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));

        verify(userService, times(1)).postUser(any(User.class));
    }

    @Test
    public void post_user_and_expected_is_json() throws Exception {

        User mockUser = new User("test_first_name", "test_last_name", "test_nick_name", "testEmail@gmail.com",
                1234567890L, "test_about_yourself");
        mockUser.setId(12345678L);

        Gson gson = new Gson();
        String mockUserJson = gson.toJson(mockUser);

        when(userService.postUser(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(post(URL_BASE)
                .content(mockUserJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));

        verify(userService, times(1)).postUser(any(User.class));
    }

    @Test
    public void post_user_and_expected_is_valid_json() throws Exception {

        User mockUser = new User("test_first_name", "test_last_name", "test_nick_name", "testEmail@gmail.com",
                1234567890L, "test_about_yourself");
        mockUser.setId(12345678L);
        User postedUser = new User();
        Gson gson = new Gson();
        String mockUserJson = gson.toJson(postedUser);

        when(userService.postUser(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(post(URL_BASE)
                .content(mockUserJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(content().json("{\"id\":12345678,\"firstName\":\"test_first_name\",\"lastName\":\"test_last_name\""
                        + ",\"nickName\":\"test_nick_name\",\"email\":\"testEmail@gmail.com\",\"phone\":1234567890"
                        + ",\"aboutYourself\":\"test_about_yourself\"}"));
    }

    @Test
    public void put_user_and_expected_is_ok() throws Exception {

        Long id = 1234567L;
        User putUser = new User("test_first_name", "test_last_name", "test_nick_name", "testEmail@gmail.com",
                1234567890L, "test_about_yourself");
        putUser.setId(id);

        Gson gson = new Gson();
        String putJsonUser = gson.toJson(putUser);

        when(userService.updateUser(any(User.class))).thenReturn(putUser);

        mockMvc.perform(put(URL_BASE)
                .content(putJsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        verify(userService, times(1)).updateUser(putUser);
    }

    /*
    This exception may occur if matchers are combined with raw values:
    //incorrect:
    someMethod(anyObject(), "raw String");
    When using matchers, all arguments have to be provided by matchers.
    For example:
    //correct:
    someMethod(anyObject(), eq("String by matcher"));

    For more info see javadoc for Matchers class.

    see when(...) clause
     */
    @Test
    public void put_user_and_expected_is_valid_json_user() throws Exception {

        Long id = 1234567L;
        User putUser = new User("test_first_name", "test_last_name", "test_nick_name", "testEmail@gmail.com",
                1234567890L, "test_about_yourself");
        putUser.setId(id);

        Gson gson = new Gson();
        String putJsonUser = gson.toJson(putUser);

        User updatedUser = new User("upd_test_first_name", "upd_test_first_name", "upd_test_nick_name",
                "upd_testEmail@gmail.com", 1234567890L, "upd_test_about_yourself");
        updatedUser.setId(id);

        when(userService.updateUser(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put(URL_BASE)
                .content(putJsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        verify(userService, times(1)).updateUser(any(User.class));
    }

    @Test
    public void internal_server_exception_message_expected_and_status_501() throws Exception {

        Long id = 123L;
        String errMessage = "some unknown internal server error";
        when(userService.getUser(eq(id))).thenThrow(new RuntimeException(errMessage));

        mockMvc.perform(get(URL_BASE + "/{id}", id.toString()))
                .andExpect(content().json("{\"reason\":\"" + errMessage + "\"}"))
                .andExpect(status().is(500));
        verify(userService, times(1)).getUser(eq(id));
    }

    @Test
    public void get_all_users_test_and_expected_is_ok() throws Exception {

        mockMvc.perform(get(URL_BASE + "/getAllUsersList"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_all_users_list_and_expected_valid_users_list() throws Exception {

        when(userService.getAllUsers()).thenReturn(new ArrayList<User>());

        mockMvc.perform(get(URL_BASE + "/getAllUsersList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void get_user_by_email() throws Exception {

        String email = "9cb14b499be76bb04fdbef92f29c744b"; // MD5

        when(userService.getUserByEmail(email)).thenReturn(new User());

        mockMvc.perform(get(URL_BASE + "?email=" + email))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).getUserByEmail(email);
    }

    @Test
    public void add_user_into_group_and_expected_is_ok() throws Exception {

        when(userService.addUserIntoGroup(anyLong(), anyLong())).thenReturn(new User());

        mockMvc.perform(post(URL_BASE + "/group?userID=12&groupID=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).addUserIntoGroup(anyLong(), anyLong());
    }

    @Test
    public void add_user_into_group_with_invalid_ids_and_expected_is_error_message() throws Exception {

        Long userID = 10L;
        Long groupID = -10L;
        mockMvc.perform(post(URL_BASE + "/group?userID=" + userID + "&groupID=" + groupID))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(0)).addUserIntoGroup(anyLong(), anyLong());
    }

    @Test
    public void remove_user_from_group_and_expected_is_ok() throws Exception {

        when(userService.removeUserFromGroup(anyLong(), anyLong())).thenReturn(new User());

        mockMvc.perform(delete(URL_BASE + "/group?userID=12&groupID=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).removeUserFromGroup(anyLong(), anyLong());
    }

    @Test
    public void remove_user_from_group_with_invalid_ids_and_expected_is_error_message() throws Exception {

        Long userID = 10L;
        Long groupID = -10L;
        mockMvc.perform(delete(URL_BASE + "/group?userID=" + userID + "&groupID=" + groupID))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(0)).removeUserFromGroup(anyLong(), anyLong());
    }

    @Test
    public void add_user_into_event_and_expected_is_ok() throws Exception {

        when(userService.addUserIntoEvent(anyLong(), anyLong())).thenReturn(new User());

        mockMvc.perform(post(URL_BASE + "/event?userID=12&eventID=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).addUserIntoEvent(anyLong(), anyLong());
    }

    @Test
    public void add_user_into_event_with_invalid_ids_and_expected_is_error_message() throws Exception {

        Long userID = 10L;
        Long groupID = -10L;
        mockMvc.perform(post(URL_BASE + "/event?userID=" + userID + "&eventID=" + groupID))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(0)).addUserIntoEvent(anyLong(), anyLong());
    }

    @Test
    public void remove_user_from_event_and_expected_is_ok() throws Exception {

        when(userService.removeUserFromEvent(anyLong(), anyLong())).thenReturn(new User());

        mockMvc.perform(delete(URL_BASE + "/event?userID=12&eventID=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(1)).removeUserFromEvent(anyLong(), anyLong());
    }

    @Test
    public void remove_user_from_event_with_invalid_ids_and_expected_is_error_message() throws Exception {

        Long userID = 10L;
        Long groupID = -10L;
        mockMvc.perform(delete(URL_BASE + "/event?userID=" + userID + "&eventID=" + groupID))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userService, times(0)).removeUserFromEvent(anyLong(), anyLong());
    }
}
