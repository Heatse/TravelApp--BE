package com.travel_app.travel.dto;

import com.travel_app.travel.entity.User;
import com.travel_app.travel.entity.Vehicle;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class BookingVehicleDTO {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private Long seatNumber;
}
