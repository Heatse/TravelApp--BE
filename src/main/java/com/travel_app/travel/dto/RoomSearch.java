package com.travel_app.travel.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RoomSearch {
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private List<String> accommodationTypes;
    private String accommodationName;
    private String type;
}
