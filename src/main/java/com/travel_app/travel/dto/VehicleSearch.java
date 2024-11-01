package com.travel_app.travel.dto;

import lombok.Data;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

@Data
public class VehicleSearch {
    private String brand;
    private String destination;
    private BigDecimal priceMin;
    private BigDecimal priceMax;
    private String startingLocation;
    private List<String> vehicleTypes;
    private String movingDate;
}
