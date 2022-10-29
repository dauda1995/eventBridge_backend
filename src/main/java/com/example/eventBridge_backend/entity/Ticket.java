package com.example.eventBridge_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "event_id",
            referencedColumnName = "eventID",
            nullable = false
    )
    private Event event;

   @ManyToOne(
           cascade = CascadeType.ALL,
           fetch = FetchType.LAZY
   )
   @JoinColumn(
           name = "person_id",
           referencedColumnName = "personId"
   )
    private Person customer;
    private String dateCreated;

}
