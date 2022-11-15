package com.example.eventBridge_backend.payload;

import com.example.eventBridge_backend.entity.Address;
import com.example.eventBridge_backend.entity.Categories;
import com.example.eventBridge_backend.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long eventID;
    private Person organiser;

    private String eventName;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String summary;
    private String cost;
    private String imgUrl;
    private String organiserName;
    private String eventType;

    private Address address;
    private Categories categories;


}
