package ua.com.joinit.service;

import ua.com.joinit.entity.Event;

/**
 * Created by krupet on 10.06.2015.
 */
public interface EventService {
    Event postEvent(Event event);

    Event updateEvent(Event event);

    Event getEventById(Long id);

    Event deleteEvent(Event event);

    Event deleteUser(Long eventID, Long userID);

    Event addUser(Long eventID, Long userID);
}
