package ua.com.joinit.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.dao.DAOBaseAppTest;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by krupet on 08.06.2015.
 */
public class EventDAOTestDAO extends DAOBaseAppTest {

    @Autowired
    private EventDAO eventDAO;

    @Test
    public void post_new_event_end_expected_is_ok() {

        Long date = new Date().getTime();

        Event event = new Event("test_event_name", "test_event_description", date, date);

        Event dbEvent = eventDAO.postEvent(event);
        assertNotNull(dbEvent);
    }

    @Test
    public void update_event_and_expected_is_ok() {

        Long date = new Date().getTime();
        Event event = new Event("test_event_name", "test_event_description", date, date);
        Event dbEvent = eventDAO.postEvent(event);

        assertNotNull(dbEvent);
        Long id = dbEvent.getId();

        event = new Event("test_update_event_name", "test_update_event_description", date, date);
        event.setId(id);

        Event updatedEvent = eventDAO.updateEvent(event);
        assertNotNull(updatedEvent);
    }

    @Test
    public void get_event_by_id_and_expected_is_ok() {

        Long date = new Date().getTime();
        Event event = new Event("test_event_name", "test_event_description", date, date);
        Event dbEvent = eventDAO.postEvent(event);
        assertNotNull(dbEvent);
        Long id = dbEvent.getId();

        Event dbEvent1 = eventDAO.getEventById(id);
        assertNotNull(dbEvent1);
    }

    @Test
    public void delete_event_and_null_expected() {

        Long date = new Date().getTime();
        Event event = new Event("test_event_name", "test_event_description", date, date);
        Event dbEvent = eventDAO.postEvent(event);

        assertNotNull(dbEvent);

        Event deletedDbEvent = eventDAO.deleteEvent(event);
        assertNull(deletedDbEvent );
    }

    @Test(expected = Exception.class)
    public void delete_not_existing_event_and_exception_expected() {

        Long id = 22222222L;
        Long date = new Date().getTime();
        Event event = new Event("test_update_event_name", "test_update_event_description",
                                date, date);
        event.setId(id);

        Event dbEvent = eventDAO.deleteEvent(event);
        assertNull(dbEvent);
    }
}
