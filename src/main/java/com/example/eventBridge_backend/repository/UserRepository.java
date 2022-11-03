package com.example.eventBridge_backend.repository;

import com.example.eventBridge_backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {
//   public Person findPersonByEmailEqualsIgnoreCase();

    Optional<Person> findByEmail(String email);
    Optional<Person> findByFirstNameOrEmail(String username, String email);
    Optional<Person> findByFirstName(String username);
    Boolean existsByFirstName(String username);
    Boolean existsByEmail(String email);
}
