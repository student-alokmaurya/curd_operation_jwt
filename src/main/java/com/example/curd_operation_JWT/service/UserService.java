package com.example.curd_operation_JWT.service;

import com.example.curd_operation_JWT.dto.AuthResponse;
import com.example.curd_operation_JWT.dto.LoginRequest;
import com.example.curd_operation_JWT.entity.User;
import com.example.curd_operation_JWT.repository.UserRepo;
import com.example.curd_operation_JWT.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepo.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username and password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username and password");
        }

        return new AuthResponse(
                jwtUtils.generateToken(user.getUsername())
        );
    }


    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUserById(Long id, User user) {

        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());

        // password optional update
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }

        return userRepo.save(existingUser);
    }

    public boolean deleteById(Long id) {

        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepo.delete(user);
        return true;
    }



}
