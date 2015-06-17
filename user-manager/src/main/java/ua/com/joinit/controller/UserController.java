package ua.com.joinit.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.UserService;

import java.util.List;

/**
 * Created by krupet on 03.02.15.
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/say_hi", method = RequestMethod.GET)
    public ResponseEntity<String> getMessageFromUser(){
        return new ResponseEntity<>("hi", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUserById(@RequestParam(value = "email", required = true) String email) {

        if (email == null) throw new RuntimeException("bad request: no query parameter!");
        User dbUser = userService.getUserByEmail(email);
        if (dbUser == null) throw new RuntimeException("bad request: no user with such email!");
        return  new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable(value = "userID") Long userID) {

        if (userID <= 0L) throw new RuntimeException("bad request: invalid ID!");
        User dbUser = userService.getUser(userID);
        if (dbUser == null) throw new RuntimeException("bad request: no user with such id!");
        return  new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        if (user == null) throw new RuntimeException("bad request: check users input!");
        User postedUser = userService.postUser(user);
        if (postedUser == null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>(postedUser, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<User> putUser(@RequestBody User user) {
        if (user == null) throw new RuntimeException("bad request: check users input!");
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) throw new RuntimeException("bad request: there is no user with such id, check input!");
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllUsersList", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        if (usersList == null) throw new RuntimeException("internal server error!");
        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> addUserIntoGroup(@RequestParam(value = "userID", required = true) Long userID,
                                                 @RequestParam(value = "groupID", required = true) Long groupID) {

        if (groupID == null || groupID <= 0 || userID == null || userID <= 0) throw new RuntimeException("bad request: check users input!");
        User dbUser = userService.addUserIntoGroup(userID, groupID);
        if (dbUser == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/group", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<User> removeUserFromGroup(@RequestParam(value = "userID", required = true) Long userID,
                                                    @RequestParam(value = "groupID", required = true) Long groupID) {

        if (groupID == null || groupID <= 0 || userID == null || userID <= 0) throw new RuntimeException("bad request: check users input!");
        User dbUser = userService.removeUserFromGroup(userID, groupID);
        if (dbUser == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> addUserIntoEvent(@RequestParam(value = "userID", required = true) Long userID,
                                                 @RequestParam(value = "eventID", required = true) Long eventID) {

        if (eventID == null || eventID <= 0 || userID == null || userID <= 0) throw new RuntimeException("bad request: check users input!");
        User dbUser = userService.addUserIntoEvent(userID, eventID);
        if (dbUser == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/event", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<User> removeUserFromEvent(@RequestParam(value = "userID", required = true) Long userID,
                                                    @RequestParam(value = "eventID", required = true) Long eventID) {

        if (eventID == null || eventID <= 0 || userID == null || userID <= 0) throw new RuntimeException("bad request: check users input!");
        User dbUser = userService.removeUserFromEvent(userID, eventID);
        if (dbUser == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(produces = "application/json")
    public ResponseEntity<String> handleUserControllerException(Exception ex) {
        String excInfo = ex.getMessage();
        return new ResponseEntity<>("{\"reason\":\"" + excInfo + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
