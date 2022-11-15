package com.example.eventBridge_backend.payload;

import com.example.eventBridge_backend.entity.Address;
import com.example.eventBridge_backend.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventDto {

    private String eventName;
    private String organiserName;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String summary;
    private String cost;
    private String imgUrl;
    private double latitude;
    private double longitude;
    private String location;
    private String preference;

    private String eventType;

    public Address getAddress(){
        Address address = Address.builder()
                .latitude(this.latitude)
                .longitude(this.longitude)
                .city(this.location).build();

        return address;
    }

    public Categories getCategory(){
        Categories categories = Categories.builder()
                .preference(this.preference)
                .build();
        return categories;
    }


    public EventDto eventDto(){
        EventDto eventDto = EventDto.builder()
                .eventName(getEventName())
                .startDate(getStartDate())
                .startTime(getStartTime())
                .endDate(getEndDate())
                .endTime(getEndTime())
                .summary(getSummary())
                .cost(getCost())
                .imgUrl(getImgUrl())
                .address(getAddress())
                .categories(getCategory())
                .eventType(getEventType())
                .organiserName(organiserName).build();


        return eventDto;
    }



}
