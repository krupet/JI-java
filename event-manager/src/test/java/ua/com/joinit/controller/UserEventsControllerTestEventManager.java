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
import ua.com.joinit.EventManagerBaseAppTest;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.EventService;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by krupet on 10.06.2015.
 */

public class UserEventsControllerTestEventManager extends EventManagerBaseAppTest {

    private static final String URL_PREFIX = "/events";

    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    @Mock
    private EventService eventService;

    @Autowired
    @InjectMocks
    private EventController eventController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        /*
            TODO: improve real stand alone setUP()
            errors happens: loads all controllers even from different modules
         */
        this.mockMvc = standaloneSetup(eventController).build();
    }

    @Test
    public void get_response_from_controller() throws Exception {

        mockMvc.perform(get(URL_PREFIX + "/test"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_json_response_from_controller() throws Exception {

        mockMvc.perform(get(URL_PREFIX + "/test"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void post_event_and_expected_is_accepted() throws Exception {

        when(eventService.postEvent(any(Event.class))).thenReturn(new Event("dbname", "dbdesc", 1L, 1L));

        mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_JSON).content(getJsonEvent()))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(eventService, times(1)).postEvent(any(Event.class));
    }

    @Test
    public void post_event_and_internal_server_error_message_expected() throws Exception {

        when(eventService.postEvent(any(Event.class))).thenReturn(null);

        mockMvc.perform(post(URL_PREFIX).contentType(MediaType.APPLICATION_JSON).content(getJsonEvent()))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(eventService, times(1)).postEvent(any(Event.class));
    }

    @Test
    public void update_event_and_expected_is_ok() throws Exception {

        when(eventService.updateEvent(any(Event.class))).thenReturn(new Event("upd_name", "upd_desc", 1L, 1L));

        mockMvc.perform(put(URL_PREFIX).contentType(MediaType.APPLICATION_JSON).content(getJsonEvent()))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":0,\"eventName\":\"upd_name\",\"description\":\"upd_desc\","
                        + "\"eventDate\":1,\"creationDate\":1,\"users\":null}"));

        verify(eventService, times(1)).updateEvent(any(Event.class));
    }

    @Test
    public void put_event_and_error_message_expected() throws Exception {

        when(eventService.updateEvent(any(Event.class))).thenReturn(null);

        mockMvc.perform(put(URL_PREFIX).contentType(MediaType.APPLICATION_JSON).content(getJsonEvent()))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"reason\":\"internal server error!\"}"))
                .andDo(print());

        verify(eventService, times(1)).updateEvent(any(Event.class));
    }

    @Test
    public void get_event_by_id_and_expected_is_ok() throws Exception {

        Long id = 1L;
        Event event = new Event("test_name", "test_desc", 1L, 1L);
        event.setId(id);

        when(eventService.getEventById(anyLong())).thenReturn(event);

        mockMvc.perform(get(URL_PREFIX + "?id=" + id.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"eventName\":\"test_name\",\"description\":\"test_desc\","
                        + "\"eventDate\":1,\"creationDate\":1,\"users\":null}"));

        verify(eventService, times(1)).getEventById(id);
    }

    @Test
    public void get_event_and_error_message_expected() throws Exception {

        when(eventService.getEventById(1L)).thenReturn(null);

        mockMvc.perform(get(URL_PREFIX + "?eventID=1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"reason\":\"internal server error!\"}"));

        verify(eventService, times(1)).getEventById(1L);
    }

    @Test
    public void get_users_list_by_event_id_by_event_id_and_expected_is_accepted() throws Exception {

        when(eventService.getListOfUsersByEventID(1L)).thenReturn(new ArrayList<User>());

        mockMvc.perform(get(URL_PREFIX + "/getusers?eventID=1"))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(eventService, times(1)).getListOfUsersByEventID(1L);
    }

    @Test
    public void get_users_list_by_event_id_by_event_id_and_expected_is_error_message() throws Exception {

        when(eventService.getListOfUsersByEventID(anyLong())).thenReturn(null);

        mockMvc.perform(get(URL_PREFIX + "/getusers?eventID=1"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        verify(eventService, times(1)).getListOfUsersByEventID(1L);
    }

    @Test
     public void delete_event_and_ok_is_expected() throws Exception {

        when(eventService.deleteEvent(any(Event.class))).thenReturn(null);

        mockMvc.perform(delete(URL_PREFIX).contentType(MediaType.APPLICATION_JSON).content(getJsonEventWithID(1L)))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        verify(eventService, times(1)).deleteEvent(any(Event.class));
    }

    @Test
    public void delete_event_and_500_error_message_is_expected() throws Exception {

        when(eventService.deleteEvent(any(Event.class))).thenReturn(new Event());

        mockMvc.perform(delete(URL_PREFIX).contentType(MediaType.APPLICATION_JSON).content(getJsonEventWithID(1L)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        verify(eventService, times(1)).deleteEvent(any(Event.class));
    }

    @Test
    public void delete_event_and_bad_request_message_is_expected() throws Exception {

        mockMvc.perform(delete(URL_PREFIX).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        verify(eventService, times(0)).deleteEvent(any(Event.class));
    }

    private String getJsonEvent() {
        Event event = new Event("test_name", "test_desc", new Date().getTime(), new Date().getTime());
        Gson gson = new Gson();
        return gson.toJson(event);
    }

    private String getJsonEventWithID(Long id) {
        Event event = new Event("test_name", "test_desc", new Date().getTime(), new Date().getTime());
        event.setId(id);
        Gson gson = new Gson();
        return gson.toJson(event);
    }
}
