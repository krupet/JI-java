package ua.com.joinit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.GroupService;

import java.util.List;

/**
 * Created by krupet on 10.06.2015.
 */

@Controller
@RequestMapping(value = "groups")
public class GroupsController {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public ResponseEntity<String> sauHi() {
        return new ResponseEntity<>("hi", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Group> postGroup(@RequestBody Group group){

        if (group == null) throw new RuntimeException("bad request: invalid group json!");
        Group dbGroup = groupService.postGroup(group);
        if (dbGroup == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(dbGroup, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{groupID}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Group> getGroupById(@PathVariable(value = "groupID") Long groupID) {

        if (groupID == null || groupID <= 0L) throw new RuntimeException("bad request: invalid ID!");
        Group dbGroup = groupService.getGroupById(groupID);
        if (dbGroup == null) throw new RuntimeException("bad request: there is no group with such id");

        return new ResponseEntity<>(dbGroup, HttpStatus.OK);
    }

    @RequestMapping(value = "getList", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Group>> getAllGroupsList(){

        List<Group> groupList = groupService.getAllGroups();
        if (groupList == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<Group> updateGroup(@RequestBody(required = true) Group group) {

        if (group == null) throw new RuntimeException("bad request!");
        Group dbGroup = groupService.updateGroup(group);
        if (dbGroup == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(dbGroup, HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<String> deleteGroup(@RequestBody Group group) {

        if (group == null) throw new RuntimeException("bad request!");
        Group dbGroup = groupService.deleteGroup(group);
        if (dbGroup != null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>("{\"status\":\"deleted successfully!\"}", HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "addEvent", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Group> addEvent(@RequestParam(value = "groupID", required = true) Long groupID,
                                          @RequestParam(value = "eventID", required = true) Long eventID) {

        if (groupID == null || groupID <= 0 || eventID == null || eventID <= 0) throw new RuntimeException("bad request: check users input!");
        Group dbGroup = groupService.addEvent(groupID, eventID);
        if (dbGroup == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(dbGroup, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "removeEvent", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Group> removeEvent(@RequestParam(value = "groupID", required = true) Long groupID,
                                             @RequestParam(value = "eventID", required = true) Long eventID) {

        if (groupID == null || groupID <= 0 || eventID == null || eventID <= 0) throw new RuntimeException("bad request: check users input!");
        Group dbGroup = groupService.removeEvent(groupID, eventID);
        if (dbGroup == null) throw new RuntimeException("internal server error!");

        return new ResponseEntity<>(dbGroup, HttpStatus.ACCEPTED);
    }

//    List<User> getAllUsersInAGroupByGroupID(Long groupID){ return null;}

    @ExceptionHandler(Exception.class)
    @RequestMapping(produces = "application/json")
    public ResponseEntity<String> handleGroupControllerException(Exception ex) {
        String excInfo = ex.getMessage();
        return new ResponseEntity<>("{\"reason\":\"" + excInfo + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
