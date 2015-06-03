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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import ua.com.joinit.BaseAppTest;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by krupet on 25.02.2015.
 */
public class ControllerTests extends BaseAppTest {
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
        this.mockMvc = standaloneSetup(userController).build();
//        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void simple() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_user_by_id_and_expected_is_ok200() throws Exception {
        mockMvc.perform(get("/1"))
                .andExpect(status().is(200));
    }

    @Test
    public void get_user_by_id_and_expected_is_valid_user() throws Exception {
        Long id = 1234567L;
        User mockUser = new User();
        mockUser.setFirstName("test_first_name");
        mockUser.setLastName("test_last_name");
        mockUser.setNickName("test_nick_name");
        mockUser.setEmail("testEmail@gmail.com");
        mockUser.setPhone(1234567890L);
        mockUser.setAboutYourself("test_about_yourself");
        mockUser.setId(id);

        when(userService.getUser(id)).thenReturn(mockUser);
        Gson gson = new Gson();
        String expectedJsonUser = gson.toJson(mockUser);

        mockMvc.perform(get("/" + id.toString()))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedJsonUser));
//                .andDo(print());

        verify(userService, times(1)).getUser(id);
    }

    @Test
    public void post_user_and_expected_is_ok() throws Exception {
        User postUser = new User();
        Gson gson = new Gson();
        String content = gson.toJson(postUser);
        mockMvc.perform(post("/").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201));
    }

    @Test
    public void post_user_and_expected_is_json() throws Exception {
        User mockUser = new User();
        mockUser.setFirstName("test_first_name");
        mockUser.setLastName("test_last_name");
        mockUser.setNickName("test_nick_name");
        mockUser.setEmail("testEmail@gmail.com");
        mockUser.setPhone(1234567890L);
        mockUser.setAboutYourself("test_about_yourself");
        mockUser.setId(12345678L);

        Gson gson = new Gson();
        String mockUserJson = gson.toJson(mockUser);

        when(userService.postUser(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(post("/")
                .content(mockUserJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andDo(print());
    }

    @Test
    public void post_user_and_expected_is_valid_json() throws Exception {

        User mockUser = new User();
        mockUser.setFirstName("test_first_name");
        mockUser.setLastName("test_last_name");
        mockUser.setNickName("test_nick_name");
        mockUser.setEmail("testEmail@gmail.com");
        mockUser.setPhone(1234567890L);
        mockUser.setAboutYourself("test_about_yourself");

        mockUser.setId(12345678L);
        User postedUser = new User();
        Gson gson = new Gson();
        String mockUserJson = gson.toJson(postedUser);

        when(userService.postUser(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(post("/")
                .content(mockUserJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(content().json("{\"id\":12345678,\"firstName\":\"test_first_name\",\"lastName\":\"test_last_name\""
                        + ",\"nickName\":\"test_nick_name\",\"email\":\"testEmail@gmail.com\",\"phone\":1234567890"
                        + ",\"aboutYourself\":\"test_about_yourself\"}"))
                .andDo(print());
    }

    @Test
    public void put_user_and_expected_is_ok() throws Exception {

        Long id = 1234567L;
        User putUser = new User();
        putUser.setFirstName("test_first_name");
        putUser.setLastName("test_last_name");
        putUser.setNickName("test_nick_name");
        putUser.setEmail("testEmail@gmail.com");
        putUser.setPhone(1234567890L);
        putUser.setAboutYourself("test_about_yourself");

        putUser.setId(id);

        Gson gson = new Gson();
        String putJsonUser = gson.toJson(putUser);

        mockMvc.perform(put("/" + id.toString())
                .content(putJsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));
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
    public void put_user_and_expekted_is_valid_json_user() throws Exception {

        Long id = 1234567L;
        User putUser = new User();
        putUser.setFirstName("test_first_name");
        putUser.setLastName("test_last_name");
        putUser.setNickName("test_nick_name");
        putUser.setEmail("testEmail@gmail.com");
        putUser.setPhone(1234567890L);
        putUser.setAboutYourself("test_about_yourself");

        putUser.setId(id);

        Gson gson = new Gson();
        String putJsonUser = gson.toJson(putUser);

        User updatedUser = new User();
        updatedUser.setFirstName("upd_test_first_name");
        updatedUser.setLastName("upd_test_last_name");
        updatedUser.setNickName("upd_test_nick_name");
        updatedUser.setEmail("upd_testEmail@gmail.com");
        updatedUser.setPhone(1234567890L);
        updatedUser.setAboutYourself("upd_test_about_yourself");

        updatedUser.setId(id);
        when(userService.updateUser(eq(id), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/" + id.toString())
                .content(putJsonUser)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(200));

        verify(userService, times(1)).updateUser(eq(id), any(User.class));
    }

    @Test
    public void internal_server_exception_message_expected_and_status_501() throws Exception {
        Long id = 123L;
        String errMessage = "some unknown internal server error";
        when(userService.getUser(eq(id))).thenThrow(new RuntimeException(errMessage));
//        when(userService.getUser(eq(id))).thenThrow(new Exception(errMessage));
//        org.mockito.exceptions.base.MockitoException:
//        Checked exception is invalid for this method!
//                Invalid: java.lang.Exception: some unknown internal server error
        mockMvc.perform(get("/{id}", id.toString()))
                .andDo(print())
                .andExpect(content().json("{\"reason\":\"" + errMessage + "\"}"))
                .andExpect(status().is(500));
        verify(userService, times(1)).getUser(eq(id));
    }

    @Test
    public void get_all_users_test_and_expected_is_ok() throws Exception {

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_all_users_list_and_expected_valid_users_list() throws Exception {

        List<User> mockedUsersList = new ArrayList<>();

        Long id = 1234567L;
        User mockUser = new User();
        mockUser.setFirstName("test_first_name");
        mockUser.setLastName("test_last_name");
        mockUser.setNickName("test_nick_name");
        mockUser.setEmail("testEmail@gmail.com");
        mockUser.setPhone(1234567890L);
        mockUser.setAboutYourself("test_about_yourself");
        mockUser.setId(id);

        mockedUsersList.add(mockUser);
        mockedUsersList.add(mockUser);
        mockedUsersList.add(mockUser);

        Gson gson = new Gson();
        String expectedList = gson.toJson(mockedUsersList);

        when(userService.getAllUsers()).thenReturn(mockedUsersList);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(expectedList)) //TODO: exception because of array comparing
//                .andExpect(content().string(expectedList))
                .andExpect(content().string(expectedList));
//                .andDo(print());

        verify(userService, times(1)).getAllUsers();

    }
}
