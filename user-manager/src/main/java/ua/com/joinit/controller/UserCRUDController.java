package ua.com.joinit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.joinit.entity.User;
import ua.com.joinit.service.UserCRUDService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by krupet on 08.02.2015.
 */
@Controller
@RequestMapping("/")
public class UserCRUDController {

//    @Autowired
    @Resource(name = "userCRUDService")
    private UserCRUDService userCRUDService;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        List<User> users = userCRUDService.getAllUsers();
        model.addAttribute("users", users);
        return "userspage";
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public String getAddUser(Model model) {
        model.addAttribute("userAttribute", new User());
        return "addpage";
    }

    @RequestMapping(value = "users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("userAttribute") User user) {
        userCRUDService.addUser(user);
        return "addedpage";
    }

    @RequestMapping(value = "users/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(value = "id", required = true) Long id, Model model) {
        userCRUDService.deleteUser(id);
        model.addAttribute("id", id);
        return "deletepage";
    }

    @RequestMapping(value = "users/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("userAttribute", userCRUDService.getUser(id));
        return "editepage";
    }

    @RequestMapping(value = "users/edit", method = RequestMethod.POST)
    public String saveeditedUser(@ModelAttribute(value = "userAttribute") User user,
                                 @RequestParam(value = "id", required = true) Long id,
                                 Model model) {
        user.setId(id);
        userCRUDService.editUser(user);
        model.addAttribute("id", id);
        return "editepage";
    }
}
