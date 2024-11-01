package com.travel_app.travel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "room")
@Data
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int roomNumber;
    private String type;
    private String description;
    private int capacity;
    private BigDecimal price;
    private List<String> images;
    private String temp;
    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

}