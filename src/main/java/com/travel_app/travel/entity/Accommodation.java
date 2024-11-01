package com.travel_app.travel.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@Table(name = "accommodation")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String images;
    @ManyToOne
    @JoinColumn(name ="provin_id")
    private Provin provin;
}
