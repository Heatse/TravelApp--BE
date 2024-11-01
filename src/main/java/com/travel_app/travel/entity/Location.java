package com.travel_app.travel.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "location")
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private  String description;
    private String shortDes;
    private String address;
    private List<String> images;
    @ManyToOne
    @JoinColumn(name = "provin_id")
    private Provin provin;
    private String temp;
}
