package ua.com.joinit.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.BaseAppTest;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by krupet on 08.06.2015.
 */
public class EventDAOTest extends BaseAppTest {

    @Autowired
    private EventDAO eventDAO;

    @Test
    public void post_new_event_end_expected_is_ok() {
        Event event = new Event("test_event_name", "test_event_description",
                                new Date().getTime(), new Date().getTime());

        Event dbEvent = eventDAO.postEvent(event);
        assertNotNull(dbEvent);
    }

    @Test
    public void update_event_and_expected_is_ok() {
        Long id = 2L;
        Event event = new Event("test_update_event_name", "test_update_event_description",
                new Date().getTime(), new Date().getTime());
        event.setId(id);

        Event dbEvent = eventDAO.updateEvent(event);
        assertNotNull(dbEvent);
    }

    @Test
    public void get_event_by_id_and_expected_is_ok() {
        Long id = 2L;
        Event dbEvent = eventDAO.getEventById(id);
        assertNotNull(dbEvent);
    }

    @Test
    public void delete_event_and_null_expected() {
        Long id = 2L;
        Event event = new Event("test_update_event_name", "test_update_event_description",
                new Date().getTime(), new Date().getTime());
        event.setId(id);

        Event dbEvent = eventDAO.deleteEvent(event);
        assertNull(dbEvent);
    }

    @Test(expected = Exception.class)
    public void delete_event_and_exception_expected() {
        Long id = 2L;
        Event event = new Event("test_update_event_name", "test_update_event_description",
                new Date().getTime(), new Date().getTime());
        event.setId(id);

        Event dbEvent = eventDAO.deleteEvent(event);
        assertNull(dbEvent);
    }

    @Test
    public void add_user_to_event_and_expected_is_ok() {

        Long userID = 3L;
        Long eventID = 3L;

        Event dbEvent = eventDAO.addUser(eventID, userID);

        assertNotNull(dbEvent);
    }

    @Test
    public void add_not_existing_user_in_not_existing_event_and_null_expected() {

        Long userID = 3333L;
        Long eventID = 3333L;

        Event dbEvent = eventDAO.addUser(eventID, userID);

        assertNull(dbEvent);
    }

    @Test
    public void remove_user_from_event_and_ok_expected() {

        Long userID = 3L;
        Long eventID = 3L;

        Event dbEvent = eventDAO.deleteUser(eventID, userID);

        assertNotNull(dbEvent);
    }

    @Test
    public void remove_not_existing_user_from_not_existing_event_and_null_expected() {

        Long userID = 3333L;
        Long eventID = 3333L;

        Event dbEvent = eventDAO.deleteUser(eventID, userID);

        assertNull(dbEvent);
    }
}
