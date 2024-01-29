package com.example.myspringcucumber.rest;

import com.example.myspringcucumber.schema.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserRest {

    @Value("${user.host}")
    private String host;

    @Autowired
    RestTemplate restTemplate;

    public List<User> getUsers() {
        ResponseEntity<List<User>> responseEntityUsers = restTemplate.exchange(
                host + "user",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){});
        if (responseEntityUsers != null && responseEntityUsers.getStatusCode().is2xxSuccessful()) {
            return responseEntityUsers.getBody();
        }
        return null;
    }
}
