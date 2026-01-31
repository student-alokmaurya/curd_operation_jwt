package com.example.curd_operation_JWT.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
