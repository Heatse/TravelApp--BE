package com.travel_app.travel.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String startingLocation; //di chuyen tu
    private String destination; // diem den
    private String local;
    private String image;
    private int rating;
    private String vehicleType;
    private BigDecimal price;
    private int travelTime; //tinh theo gio
    private Boolean status;
    private Long seatTotal;
    private String movingDate;
}
