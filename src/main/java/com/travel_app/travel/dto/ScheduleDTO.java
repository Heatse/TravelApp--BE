package com.travel_app.travel.dto;

import com.travel_app.travel.entity.Location;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ScheduleDTO {

    private Long id;
    private Long locationId;
    private Long userId;
}
