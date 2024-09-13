package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Customer {
    @GetMapping(path = "/welcome")
    public String getWelcome(){
        return "fuck you";

    }
}
