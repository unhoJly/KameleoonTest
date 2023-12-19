package com.kameleoon.developers.controllers;

import com.kameleoon.developers.entities.User;
import com.kameleoon.developers.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return usersService.findAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        usersService.saveUser(user);

        return user;
    }
}