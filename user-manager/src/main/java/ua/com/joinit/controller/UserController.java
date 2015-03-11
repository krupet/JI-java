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

/**
 * Created by krupet on 03.02.15.
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getMessageFromUser(){
        return new ResponseEntity<>("hi", HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
//    public ResponseEntity<String> getUserById(@PathVariable("id") Long id) {
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUser(id);

//        following code was used for much older version of this controller...
//        you`ll ask why?
//        because I don`t implement object mapper (json) in my context class.
//
//        Gson gson = new Gson();
//        return  new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);

        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        User postedUser = userService.postUser(user);
        return new ResponseEntity<>(postedUser, HttpStatus.CREATED);
    }
}
