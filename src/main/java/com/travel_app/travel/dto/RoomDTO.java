package com.travel_app.travel.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RoomDTO {
    private Long id;
    private int roomNumber;
    private String type;
    private String description;
    private int capacity;
    private BigDecimal price;
    private Double avgStars;
    private List<String> images;
    private String temp;
}
