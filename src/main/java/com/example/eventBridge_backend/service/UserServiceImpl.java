package com.example.eventBridge_backend.service;

import com.example.eventBridge_backend.entity.Person;
import com.example.eventBridge_backend.error.EntityNotFoundException;
import com.example.eventBridge_backend.payload.PersonDto;
import com.example.eventBridge_backend.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Person saveUser(Person user) {
        return userRepository.save(user);
    }

    @Override
    public List<Person> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    public Person fetchUserById(Long userId) throws EntityNotFoundException {
        Optional<Person> person = userRepository.findById(userId);
        if(!person.isPresent()){
            throw new EntityNotFoundException("User Not Available");
        }
        return person.get();
    }

    @Override
    public Person upDateUser(Long userId, Person user) {
        return null;
    }

    @Override
    public Person fetchUserByEmail(String userEmail) {
        List<Person> personList = userRepository.findAll();
        Person personDto = null;
        for(Person person: personList){
            if ((person.getEmail().equals(userEmail))
            ) {
                return person;
            }
        }
        throw new RuntimeException("user could not be found by email");
    }
}
