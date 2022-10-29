package com.example.eventBridge_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(
                name = "building",
                column = @Column(name = "building_name")
        ),
        @AttributeOverride(
                name = "street",
                column = @Column(name = "building_street")
        ),
        @AttributeOverride(
                name="city",
                column = @Column(name= "building_city")
        ),
        @AttributeOverride(
                name = "state",
                column = @Column(name = "building_state")
        ),
        @AttributeOverride(
                name = "latitude",
                column = @Column(name = "latitude")
        ),
        @AttributeOverride(
                name = "longitude",
                column = @Column(name = "longitude")
        )

})

public class Address {

    private String building;
    private String Street;
    private String city;
    private String state;
    private double latitude;
    private double longitude;
}
