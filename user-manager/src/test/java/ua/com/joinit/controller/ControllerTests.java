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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Long id = 1234567l;
        User mockUser = new User("mock", "mock");
        mockUser.setId(id);
        when(userService.getUser(id)).thenReturn(mockUser);

        mockMvc.perform(get("/" + id.toString()))
                .andExpect(status().is(200))
                .andDo(print());

        verify(userService, times(1)).getUser(id);
    }

    @Test
    public void post_user_and_expected_is_ok() throws Exception {
        mockMvc.perform(post("/"))
                .andExpect(status().is(200));
    }

    @Test
    public void post_user_and_expected_is_json() throws Exception {
        User mockUser = new User("mock", "mock");
        mockUser.setId(12345678l);
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
        String name = "mockName";
        String nickName = "mockNickName";
        User mockUser = new User(name, nickName);
        mockUser.setId(12345678l);
        User postedUser = new User();
        Gson gson = new Gson();
        String mockUserJson = gson.toJson(postedUser);

        when(userService.postUser(any(User.class))).thenReturn(mockUser);

        mockMvc.perform(post("/")
                .content(mockUserJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(content().json("{\"id\":12345678,\"name\":\"mockName\",\"nickName\":\"mockNickName\"}"))
//                .andExpect(content().json("{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"))
                .andDo(print());
    }
}
