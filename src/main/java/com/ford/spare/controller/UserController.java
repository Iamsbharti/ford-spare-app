package com.ford.spare.controller;

import com.ford.spare.common.ApiResponse;
import com.ford.spare.model.User;
import com.ford.spare.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    private ResponseEntity registerUser(@RequestBody User user){
        ApiResponse apiResponse = userService.registerUser(user);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity loginUser(@RequestParam String loginId, @RequestParam String password){
        ApiResponse apiResponse = userService.loginUser(loginId,password);
        return new ResponseEntity(apiResponse, HttpStatus.OK);
    }

}
