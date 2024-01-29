package com.example.myspringcucumber.service;

import com.example.myspringcucumber.rest.UserRest;
import com.example.myspringcucumber.schema.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRest userRest;

    public List<User> getUsers() {
        return userRest.getUsers();
    }
}
