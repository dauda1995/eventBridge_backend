package com.example.eventBridge_backend.repository;

import com.example.eventBridge_backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {
//   public Person findPersonByEmailEqualsIgnoreCase();
}
