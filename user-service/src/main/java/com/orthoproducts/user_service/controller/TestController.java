package com.orthoproducts.user_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class TestController {


    @GetMapping("/getUsers")
    public String getUsers() {
        return "List of users";
    }
}
