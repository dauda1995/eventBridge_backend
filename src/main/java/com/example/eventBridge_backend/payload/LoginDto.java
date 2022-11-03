package com.example.eventBridge_backend.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String firstNameOrEmail;
    private String password;
}
