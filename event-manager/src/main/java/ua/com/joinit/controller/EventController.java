package ua.com.joinit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.joinit.entity.Event;
import ua.com.joinit.service.EventService;

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

    @RequestMapping(value = "/adduser", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Event> addUserToEvent(@RequestParam(value = "eventID", required = false) Long eventID,
                                                @RequestParam(value = "userID", required = false) Long userID) {
        if (eventID == null || userID == null || eventID < 1 || userID < 1) throw new RuntimeException("bad request!");
        Event dbEvent = eventService.addUser(eventID, userID);
        if (dbEvent == null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>(dbEvent, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Event> deleteUserFromEvent(@RequestParam(value = "eventID", required = false) Long eventID,
                                                     @RequestParam(value = "userID", required = false) Long userID) {
        if (eventID == null || userID == null || eventID < 1 || userID < 1) throw new RuntimeException("bad request!");
        Event dbEvent = eventService.deleteUser(eventID, userID);
        if (dbEvent == null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>(dbEvent, HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleEventControllerException(Exception ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>("{\"reason\":\"" + message + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
