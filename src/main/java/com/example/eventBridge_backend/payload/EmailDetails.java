package com.example.eventBridge_backend.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {


    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;

}
