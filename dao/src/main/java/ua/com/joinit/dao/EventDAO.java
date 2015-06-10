package ua.com.joinit.dao;

import ua.com.joinit.entity.Event;

/**
 * Created by krupet on 08.06.2015.
 */
public interface EventDAO {
    Event postEvent(Event event);

    Event updateEvent(Event event);

    Event getEventById(Long id);

    Event deleteEvent(Event event);

    Event addUser(Long eventID, Long userID);

    Event deleteUser(Long eventID, Long userID);
}
