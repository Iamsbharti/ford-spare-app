package com.ford.spare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {
    @GetMapping("/")
    private String homePage(){
        return("<html><body><legend>This is The Ford Spare Booking  - App API.</legend></body></html>");
    }

    @GetMapping("/api/health/check")
    private String healthCheck(){
        return "Ford Spare Booking API Health Ok";
    }
}
