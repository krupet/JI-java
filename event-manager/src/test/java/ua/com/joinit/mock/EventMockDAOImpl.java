package ua.com.joinit.mock;

import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;

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
    public Event addUser(Long eventID, Long userID) {
        return null;
    }

    @Override
    public Event deleteUser(Long eventID, Long userID) {
        return null;
    }
}
