package ua.com.joinit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.service.EventService;

/**
 * Created by krupet on 10.06.2015.
 */
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDAO eventDAO;

    @Override
    public Event postEvent(Event event) {
        return eventDAO.postEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDAO.updateEvent(event);
    }

    @Override
    public Event getEventById(Long id) {
        return eventDAO.getEventById(id);
    }

    @Override
    public Event deleteEvent(Event event) {
        return eventDAO.deleteEvent(event);
    }

    @Override
    public Event deleteUser(Long eventID, Long userID) {
        return eventDAO.deleteUser(eventID, userID);
    }

    @Override
    public Event addUser(Long eventID, Long userID) {
        return eventDAO.addUser(eventID, userID);
    }
}
