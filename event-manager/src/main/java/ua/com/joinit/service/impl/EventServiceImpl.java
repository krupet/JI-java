package ua.com.joinit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.com.joinit.dao.EventDAO;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.EventService;

import java.util.List;

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
    public List<User> getListOfUsersByEventID(Long eventID) {
        return eventDAO.getListOfUsersByEventID(eventID);
    }
}
