package com.example.curd_operation_JWT.controller;

import com.example.curd_operation_JWT.dto.AuthResponse;
import com.example.curd_operation_JWT.dto.LoginRequest;
import com.example.curd_operation_JWT.entity.User;
import com.example.curd_operation_JWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {

        return userService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return userService.login(request);
    }


}
