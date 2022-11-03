package com.example.eventBridge_backend.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String firstName;
    private String email;
    private String password;
}
