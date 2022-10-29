package com.example.eventBridge_backend.error;

import com.example.eventBridge_backend.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException entityNotFoundException, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, entityNotFoundException.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

}
