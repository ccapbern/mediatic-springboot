package fr.iocean.application.user.controller;

import fr.iocean.application.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iocean.application.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        List<User> userList = userService.findAll();

        return userList;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable Long id) {
        User user = userService.findOne(id);

        return user;
    }
}
