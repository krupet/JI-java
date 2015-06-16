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

    @RequestMapping(value = "say_hi", method = RequestMethod.GET)
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

    @RequestMapping(value = "{userID}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable(value = "userID") Long userID) {

        if (userID <= 0L) throw new RuntimeException("bad request: invalid ID!");
        User dbUser = userService.getUser(userID);
        if (dbUser == null) throw new RuntimeException("bad request: no user with such id!");
        return  new ResponseEntity<>(dbUser, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        User postedUser = userService.postUser(user);
        return new ResponseEntity<>(postedUser, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<User> putUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

//    @RequestMapping(value = "/getAllUsersList", method = RequestMethod.GET, produces = "application/json")
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> usersList = userService.getAllUsers();
//        return new ResponseEntity<>(usersList, HttpStatus.OK);
//    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(produces = "application/json")
    public ResponseEntity<String> handleUserControllerException(Exception ex) {
        String excInfo = ex.getMessage();
        return new ResponseEntity<>("{\"reason\":\"" + excInfo + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
