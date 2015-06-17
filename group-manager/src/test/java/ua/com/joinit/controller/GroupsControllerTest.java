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
import ua.com.joinit.GroupManagerBaseAppTest;
import ua.com.joinit.entity.Group;
import ua.com.joinit.service.GroupService;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by krupet on 17.06.2015.
 */
public class GroupsControllerTest extends GroupManagerBaseAppTest {

    public static final String URL_BASE = "/groups";

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    @Mock
    private GroupService groupMockService;

    @Autowired
    @InjectMocks
    private GroupsController groupsController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(groupsController).build();
    }

    @Test
    public void test_say_hi() throws Exception {

        mockMvc.perform(get(URL_BASE + "/sayHi")).andExpect(status().isOk());
    }

    @Test
    public void testPostGroupAndExpectedIsAccepted() throws Exception {

        Group postGroup = new Group();
        Gson gson = new Gson();
        String jsonGroup = gson.toJson(postGroup);

        when(groupMockService.postGroup(any(Group.class))).thenReturn(new Group("test_group_name", "test_group_description", 1L));

        mockMvc.perform(post(URL_BASE).content(jsonGroup).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(1)).postGroup(any(Group.class));
    }

    /*
        getting everything accept for proper message(
     */
    @Test
    public void testPostWithoutJsonGroupAndErrorMessageExpected() throws Exception {

        mockMvc.perform(post(URL_BASE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(0)).postGroup(any(Group.class));
    }

    @Test
    public void get_group_by_id_and_expected_is_ok() throws Exception {

        Long id = 1L;
        when(groupMockService.getGroupById(anyLong())).thenReturn(new Group("test_group_name", "test_group_description", 1L));

        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(1)).getGroupById(anyLong());
    }

    @Test
    public void get_group_by_not_valid_id_and_expected_is_error_message() throws Exception {

        Long id = -1L;

        mockMvc.perform(get(URL_BASE + "/" + id))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());

        verify(groupMockService, times(0)).getGroupById(anyLong());
    }

    @Test
    public void get_group_without_id_and_expected_is_error_message() throws Exception {

        mockMvc.perform(get(URL_BASE + "/"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());

        verify(groupMockService, times(0)).getGroupById(anyLong());
    }

    @Test
    public void get_list_of_groups() throws Exception {

        when(groupMockService.getAllGroups()).thenReturn(new ArrayList<>());

        mockMvc.perform(get(URL_BASE + "/getList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(1)).getAllGroups();
    }

    @Test
    public void update_group_and_expected_is_ok() throws Exception {

        Group group = new Group("test_group_name", "test_group_descr", 1L);
        Gson gson = new Gson();
        String jsonGroup = gson.toJson(group);

        when(groupMockService.updateGroup(any(Group.class))).thenReturn(group);

        mockMvc.perform(put(URL_BASE).content(jsonGroup).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(1)).updateGroup(any(Group.class));
    }

    @Test
    public void delete_group_and_expected_is_ok() throws Exception {

        Group group = new Group("test_group_name", "test_group_descr", 1L);
        Gson gson = new Gson();
        String jsonGroup = gson.toJson(group);

        when(groupMockService.deleteGroup(any(Group.class))).thenReturn(null);

        mockMvc.perform(delete(URL_BASE).content(jsonGroup).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(1)).deleteGroup(any(Group.class));
    }

    @Test
    public void add_event_to_group_and_expected_is_ok() throws Exception {

        Long groupID = 10L;
        Long eventID = 10L;

        when(groupMockService.addEvent(anyLong(), anyLong())).thenReturn(new Group("test_name", "test_descr", 1L));

        mockMvc.perform(post(URL_BASE + "/addEvent?groupID=" + groupID + "&eventID=" + eventID))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(1)).addEvent(anyLong(), anyLong());
    }

    @Test
    public void add_event_to_group_with_not_valid_ids_and_expected_is_error_message() throws Exception {

        Long groupID = -10L;
        Long eventID = 10L;

        mockMvc.perform(post(URL_BASE + "/addEvent?groupID=" + groupID + "&eventID=" + eventID))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(0)).addEvent(anyLong(), anyLong());
    }

    @Test
    public void remove_event_to_group_and_expected_is_ok() throws Exception {

        Long groupID = 10L;
        Long eventID = 10L;

        when(groupMockService.removeEvent(anyLong(), anyLong())).thenReturn(new Group("test_name", "test_descr", 1L));

        mockMvc.perform(delete(URL_BASE + "/removeEvent?groupID=" + groupID + "&eventID=" + eventID))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(1)).removeEvent(anyLong(), anyLong());
    }

    @Test
    public void remove_event_to_group_with_not_valid_ids_and_expected_is_error_message() throws Exception {

        Long groupID = -10L;
        Long eventID = 10L;

        mockMvc.perform(delete(URL_BASE + "/removeEvent?groupID=" + groupID + "&eventID=" + eventID))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(groupMockService, times(0)).removeEvent(anyLong(), anyLong());
    }
}