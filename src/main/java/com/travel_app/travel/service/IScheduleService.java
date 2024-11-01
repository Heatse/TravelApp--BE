package com.travel_app.travel.service;

import com.travel_app.travel.dto.ScheduleDTO;
import com.travel_app.travel.entity.Schedule;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IScheduleService {

    Schedule addNewSchedule(ScheduleDTO scheduleDTO);
    List<Schedule> findAllByUserId(Long userId);

    Schedule findByLocationId(Long locationId);

    List<Schedule> findByLocationName(String keyword);
}
