package com.example.eventBridge_backend.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Categories {

    private String preference;
}
