package ua.com.joinit.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import ua.com.joinit.BaseAppTest;
import ua.com.joinit.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by krupet on 25.02.2015.
 */
public class ControllerTests extends BaseAppTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

//    @Autowired
//    @Mock
//    private UserService userService;
//
//    @Autowired
//    @InjectMocks
//    private UserController userController;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();

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
        String id = "12345";
        mockMvc.perform(get("/" + id))
                .andExpect(status().is(200))
                .andDo(print());
    }
}
