package ua.com.joinit.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.EventManagerBaseAppTest;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by krupet on 10.06.2015.
 */
public class EventsServiceTestEventManager extends EventManagerBaseAppTest {

    @Autowired
    @Mock
    private EventDAO eventMockDAO;

    @Autowired
    @InjectMocks
    private EventService eventService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void post_new_event_and_expected_is_ok() {

        Event newEvent = new Event();
        when(eventMockDAO.postEvent(newEvent)).thenReturn(new Event());
        Event serviceEvent = eventService.postEvent(newEvent);
        verify(eventMockDAO, times(1)).postEvent(any(Event.class));
        assertNotNull(serviceEvent);
    }

    @Test
    public void update_event_and_expected_is_ok() {
        Event event = new Event("upd_name", "upd_descr", new Date().getTime(), new Date().getTime());
        when(eventMockDAO.updateEvent(any(Event.class))).thenReturn(event);
        Event serviceEvent = eventService.updateEvent(new Event());
        verify(eventMockDAO, times(1)).updateEvent(any(Event.class));
        assertNotNull(serviceEvent);
        assertTrue(serviceEvent.equals(event));
    }

    @Test
    public void get_event_by_id_and_expected_is_ok() {
        Long id = 1L;
        Event event = new Event("name", "descr", new Date().getTime(), new Date().getTime());
        event.setId(id);

        when(eventMockDAO.getEventById(id)).thenReturn(event);
        Event serviceEvent = eventService.getEventById(id);
        verify(eventMockDAO, times(1)).getEventById(anyLong());
        assertNotNull(serviceEvent);
        assertNotNull(serviceEvent.getId());
    }

    @Test
    public void delete_event_and_expected_ok() {

        when(eventMockDAO.deleteEvent(any(Event.class))).thenReturn(null);
        Event serviceEvent = eventService.deleteEvent(new Event());
        verify(eventMockDAO, times(1)).deleteEvent(any(Event.class));
        assertNull(serviceEvent);
    }

    @Test
    public void delete_user_from_event_by_id_and_expected_is_ok() {

        when(eventMockDAO.getListOfUsersByEventID(anyLong())).thenReturn(new ArrayList<User>());
        List<User> userList = eventService.getListOfUsersByEventID(1L);
        verify(eventMockDAO, times(1)).getListOfUsersByEventID(anyLong());
        assertNotNull(userList);
    }
}
