package com.example.eventBridge_backend.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

public class Ticket {

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private Long ticketId;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "event_id",
            referencedColumnName = "eventID",
            nullable = false
    )
    private Event event;

   @ManyToOne(
           cascade = CascadeType.ALL,
           fetch = FetchType.EAGER
   )
   @JoinColumn(
           name = "person_id",
           referencedColumnName = "personId"
   )
    private Person customer;
    private String dateCreated;

}
