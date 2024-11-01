package com.travel_app.travel.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "provin")
@Data
public class Provin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
    private String code;
}
