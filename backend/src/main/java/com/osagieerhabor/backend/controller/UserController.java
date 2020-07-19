package com.osagieerhabor.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class UserController {
    @GetMapping("/hi")
    public String logout(){
        return "You've looged out";
    }
}
