package com.example.myspringcucumber.controller;

import com.example.myspringcucumber.schema.User;
import com.example.myspringcucumber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
