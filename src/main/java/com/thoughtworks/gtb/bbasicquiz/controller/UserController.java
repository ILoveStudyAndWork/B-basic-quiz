package com.thoughtworks.gtb.bbasicquiz.controller;

import com.thoughtworks.gtb.bbasicquiz.domain.User;
import com.thoughtworks.gtb.bbasicquiz.exception.UserNotExistException;
import com.thoughtworks.gtb.bbasicquiz.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) throws UserNotExistException {
        return userService.getUserById(id);
    }


    @PostMapping
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}
