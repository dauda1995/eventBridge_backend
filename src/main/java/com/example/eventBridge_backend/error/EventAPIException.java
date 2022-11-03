package com.example.eventBridge_backend.error;

import org.springframework.http.HttpStatus;

public class EventAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public EventAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public EventAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
