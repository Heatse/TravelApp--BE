package com.travel_app.travel.service.impl;

import com.travel_app.travel.converter.ScheduleConverter;
import com.travel_app.travel.dto.ScheduleDTO;
import com.travel_app.travel.entity.Schedule;
import com.travel_app.travel.repository.ScheduleRepository;
import com.travel_app.travel.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService implements IScheduleService {


    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    ScheduleConverter scheduleConverter;


    @Override
    public Schedule addNewSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleConverter.toEntity(scheduleDTO);
        schedule = scheduleRepository.save(schedule);
        return schedule;
    }

    @Override
    public List<Schedule> findAllByUserId(Long userId) {
        return scheduleRepository.findByUserId(userId);
    }

    @Override
    public Schedule findByLocationId(Long locationId) {
        return scheduleRepository.findByLocationId(locationId);
    }

    @Override
    public List<Schedule> findByLocationName(String keyword) {
        return scheduleRepository.findByLocationName(keyword);
    }
}
