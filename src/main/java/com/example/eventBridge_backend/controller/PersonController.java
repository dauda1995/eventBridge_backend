package com.example.eventBridge_backend.controller;

import com.example.eventBridge_backend.config.Config;
import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.PersonDto;
import com.example.eventBridge_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//add url of frontend
@CrossOrigin(origins = Config.HOST)
@RequestMapping("/api/")
@RestController
public class PersonController {

    @Autowired
    private UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);


    @PostMapping("/users")
    public ResponseEntity<Person> savePerson(@Valid @RequestBody Person person){
        LOGGER.info("inside save user");
        return new ResponseEntity<>(userService.saveUser(person), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Person>> fetchUserList(){
        LOGGER.info("inside of department controller");
        return new ResponseEntity<>(userService.fetchUserList(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public Person fetchUserById(@PathVariable("id") Long userId) throws EntityNotFoundException {
        return userService.fetchUserById(userId);
    }


    @GetMapping("/users/byEmail/{email}")
    public Person fetchUserByName( @PathVariable("email") String email){
        System.out.println(email);
        return userService.fetchUserByEmail(email);
    }
}
