package fr.iocean.application.user.controller;

import fr.iocean.application.exception.EntityNotFoundException;
import fr.iocean.application.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import fr.iocean.application.user.service.UserService;

import javax.validation.Valid;
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

        if (user == null) {
            throw new EntityNotFoundException();
        }

        return user;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid User user) {
        userService.save(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable Long id, @RequestBody @Valid User user) {
        user.setId(id);
        userService.save(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        User user = userService.findOne(id);

        if (user == null) {
            throw new EntityNotFoundException();
        }

        userService.delete(user);
    }
}
