package ua.com.joinit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.joinit.service.UserService;
import ua.com.joinit.service.UserTestService;

import javax.annotation.Resource;

/**
 * Created by krupet on 03.02.15.
 */
@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserTestService userTestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getMessageFromUser(){
        return new ResponseEntity<>(userTestService.getMessage(), HttpStatus.OK);
    }
}
