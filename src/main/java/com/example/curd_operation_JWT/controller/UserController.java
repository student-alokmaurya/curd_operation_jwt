package com.example.curd_operation_JWT.controller;

import com.example.curd_operation_JWT.entity.User;
import com.example.curd_operation_JWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    private List<User> getAllUser(){

        return userService.findAll();
    }

    @GetMapping("/user-by/{id}")
    private User getUserById(@PathVariable Long id){

        return userService.findById(id);
    }

    @PutMapping("/update-user-by/{id}")
    private User updateUserById(@PathVariable Long id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("/delete-user-by/{id}")
    private boolean deleteUserById(@PathVariable Long id){

       return  userService.deleteById(id);
    }

}
