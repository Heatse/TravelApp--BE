package com.travel_app.travel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class ScheduleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String des;
    private String start;
    private String end;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
