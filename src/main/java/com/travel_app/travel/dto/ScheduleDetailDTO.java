package com.travel_app.travel.dto;

import com.travel_app.travel.entity.Schedule;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
public class ScheduleDetailDTO {
    private Long id;
    private String des;
    private String start;
    private String end;
    private Long scheduleId;
}
