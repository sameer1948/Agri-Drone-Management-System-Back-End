package com.agri_drone_management_system_back_end.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
