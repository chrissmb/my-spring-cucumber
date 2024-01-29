package com.example.myspringcucumber.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class VersionController {

    @GetMapping("/version")
    public String getVersion() {
//        ResponseEntity<String> x = new RestTemplate().getForEntity("http://localhost:8089/body-file", String.class);
//        System.out.println(x.getBody());
        return "1.0";
    }
}
