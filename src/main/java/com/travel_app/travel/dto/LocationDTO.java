package com.travel_app.travel.dto;

import lombok.Data;

import java.util.List;
@Data
public class LocationDTO {
    private Long id;
    private String name;
    private String description;
    private List<String> images;
    private Double avgStars;
    private String shortDes;
    private String address;
    private String temp;
}
