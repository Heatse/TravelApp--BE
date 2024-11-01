package com.travel_app.travel.controller;


import com.travel_app.travel.dto.ScheduleDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.BookingVehicle;
import com.travel_app.travel.entity.Schedule;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.security.services.UserDetailsServiceImpl;
import com.travel_app.travel.service.IUserService;
import com.travel_app.travel.service.impl.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    IUserService userService;

    @PostMapping("/schedule/create/{locationId}")
    public ResponseEntity<?> addNewSchedule(@PathVariable("locationId") Long locationId) {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setLocationId(locationId);
        scheduleDTO.setUserId(userId);

        Schedule schedule = scheduleService.addNewSchedule(scheduleDTO);
        return new ResponseEntity<>(schedule, HttpStatus.CREATED);
    }

    @PostMapping("/schedule/createOrGet/{locationId}")
    public ResponseEntity<?> createOrGetSchedule(@PathVariable("locationId") Long locationId) {
        Schedule schedule = scheduleService.findByLocationId(locationId);
        if (schedule != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("scheduleId", schedule.getId());
            response.put("status", "get");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            Object userCurrent = userDetailsService.getCurrentUser();
            Long userId = null;
            if (userCurrent instanceof UserDetails) {
                userId = ((UserDetailsImpl) userCurrent).getId();
            } else if (userCurrent instanceof OAuth2User) {
                UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
                userId = userDTO.getId();
            } else if (userCurrent == null) {
                return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
            }
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setLocationId(locationId);
            scheduleDTO.setUserId(userId);

            schedule = scheduleService.addNewSchedule(scheduleDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("schedule", schedule.getId());
            response.put("status", "create");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping("/schedule/findAll")
    public ResponseEntity<?> findAll() {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }

        List<Schedule> schedule = scheduleService.findAllByUserId(userId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }


    @GetMapping("/schedule/findByLocationId/{locationId}")
    public ResponseEntity<?> findByLoactionId(@PathVariable("locationId") Long locationId) {

        Schedule schedule = scheduleService.findByLocationId(locationId);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @PostMapping("/schedule/findByLocation")
    public List<Schedule> findByLocation(@RequestBody String keyword) {
        return scheduleService.findByLocationName(keyword.substring(1, keyword.length()-1));
    }
}
