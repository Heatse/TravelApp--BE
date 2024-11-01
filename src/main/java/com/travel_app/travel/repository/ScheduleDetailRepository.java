package com.travel_app.travel.repository;

import com.travel_app.travel.entity.ScheduleDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleDetailRepository extends JpaRepository<ScheduleDetail, Long> {

    List<ScheduleDetail> findByScheduleId(Long scheduleId);
}
