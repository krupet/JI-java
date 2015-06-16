package ua.com.joinit.service;

import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;

import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */
public interface EventService {
    Event postEvent(Event event);

    Event updateEvent(Event event);

    Event getEventById(Long id);

    Event deleteEvent(Event event);

    List<User> getListOfUsersByEventID(Long eventID);
}
