package com.example.eventBridge_backend.entity;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Event{

    @Id
    @SequenceGenerator(
            name="event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long eventID;

    @ManyToOne(
//            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "organiser_id",
            referencedColumnName = "personId"
    )
    private Person organiser;

    private String organiserName;
    private String eventName;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    @Lob
    private String summary;
    private String cost;

    @Lob
    private String imgUrl;
    private String eventType;

    @Embedded
    private Address address;


    @Embedded
    private Categories categories;


//    @OneToMany(mappedBy = "event",  orphanRemoval = true)
//    private List<Ticket> tickets = new ArrayList<>();
}
