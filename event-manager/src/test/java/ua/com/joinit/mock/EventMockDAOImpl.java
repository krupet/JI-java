package ua.com.joinit.mock;

import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */
public class EventMockDAOImpl implements EventDAO {
    @Override
    public Event postEvent(Event event) {
        return null;
    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public Event getEventById(Long id) {
        return null;
    }

    @Override
    public Event deleteEvent(Event event) {
        return null;
    }

    @Override
    public List<User> getListOfUsersByEventID(Long eventID) {
        return null;
    }
}
