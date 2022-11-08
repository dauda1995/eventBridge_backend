package com.example.eventBridge_backend.entity;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Categories {

    private String preference;
}
