package com.lendingcart.timetable.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {
    @GetMapping("/")
    private String homePage(){
        return("<html><body><legend>This is a Time-Table - App API.</legend></body></html>");
    }
}
