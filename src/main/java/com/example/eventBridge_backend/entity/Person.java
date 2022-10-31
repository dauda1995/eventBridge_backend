package com.example.eventBridge_backend.entity;

import lombok.*;
import org.springframework.core.serializer.Serializer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {

    @Id
    @SequenceGenerator(
            name="person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private Long personId;

    @NotBlank(message = "Please add user email")
    private String email;
    private String firstName;
    private String lastName;

    private String password;


//    @OneToMany(mappedBy = "customer", orphanRemoval = true)
//    private List<Ticket> tickets = new ArrayList<>();
}
