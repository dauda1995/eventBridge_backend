package com.example.eventBridge_backend.payload;

import com.example.eventBridge_backend.entity.Address;
import com.example.eventBridge_backend.entity.Categories;
import com.example.eventBridge_backend.entity.Person;
import lombok.Data;

@Data
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
    private Address address;
    private Categories categories;


}
