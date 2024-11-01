package com.travel_app.travel.converter;

import com.travel_app.travel.dto.ScheduleDTO;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.entity.Schedule;
import com.travel_app.travel.entity.User;
import com.travel_app.travel.repository.LocationRepository;
import com.travel_app.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ScheduleConverter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        Optional<User> user = userRepository.findById(scheduleDTO.getUserId());
        schedule.setUser(user.get());
        Optional<Location> location = locationRepository.findById(scheduleDTO.getLocationId());
        schedule.setLocation(location.get());

        return schedule;
    }

    public ScheduleDTO toEntity(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setUserId(schedule.getUser().getId());
        scheduleDTO.setLocationId(schedule.getLocation().getId());

        return scheduleDTO;
    }
}
