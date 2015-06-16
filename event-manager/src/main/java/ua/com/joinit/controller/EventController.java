package ua.com.joinit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.joinit.entity.Event;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.EventService;

import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<>("{\"message\":\"Hello from event controller!\"}", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Event> postNewEvent(@RequestBody Event event) {
        Event dbEvent = eventService.postEvent(event);
        if (dbEvent == null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>(dbEvent, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        Event dbEvent = eventService.updateEvent(event);
        if (dbEvent == null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>(dbEvent, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Event> getEventByID(@RequestParam(value = "eventID", required = true) Long id) {
        Event dbEvent = eventService.getEventById(id);
        if (dbEvent == null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>(dbEvent, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> deleteEvent(@RequestBody(required = false) Event event) {
        if (event == null) throw new RuntimeException("bad request!");
        Event dbEvent = eventService.deleteEvent(event);
        if (dbEvent != null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>("{\"status\":\"delete complete successfully!\"}", HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getusers", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getUsersListByEventID(@RequestParam(value = "eventID", required = true) Long eventID) {
        if (eventID == null || eventID < 1) throw new RuntimeException("bad request!");
        List<User> usersList = eventService.getListOfUsersByEventID(eventID);
        if (usersList == null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>(usersList, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleEventControllerException(Exception ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>("{\"reason\":\"" + message + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
