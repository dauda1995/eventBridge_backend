package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import org.apache.catalina.User;

import java.util.List;

public interface UserService {

    public Person saveUser(Person user);

    public List<Person> fetchUserList();

    public Person fetchUserById(Long userId) throws EntityNotFoundException;

    Person upDateUser(Long userId, Person user);

    Person fetchUserByEmail(String userEmail);
}
