package com.travel_app.travel.controller;

import com.travel_app.travel.dto.ScheduleDTO;
import com.travel_app.travel.dto.ScheduleDetailDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.Schedule;
import com.travel_app.travel.entity.ScheduleDetail;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.security.services.UserDetailsServiceImpl;
import com.travel_app.travel.service.IUserService;
import com.travel_app.travel.service.impl.ScheduleDetailService;
import com.travel_app.travel.service.impl.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ScheduleDetailController {

    @Autowired
    ScheduleDetailService scheduleDetailService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    IUserService userService;


    @PostMapping("/scheduleDetail/create/{scheduleId}")
    public ResponseEntity<?> addNewSchedule(@PathVariable("scheduleId") Long scheduleId, @RequestBody ScheduleDetailDTO scheduleDetailDTO) {
        scheduleDetailDTO.setScheduleId(scheduleId);
        System.out.println(scheduleDetailDTO);
        ScheduleDetail scheduleDetail = scheduleDetailService.addNewScheduleDetail(scheduleDetailDTO);
        return new ResponseEntity<>(scheduleDetail, HttpStatus.CREATED);
    }

    @GetMapping("/scheduleDetail/findAll/{scheduleId}")
    public ResponseEntity<?> findAll(@PathVariable(value = "scheduleId") Long scheduleId) {
        List<ScheduleDetail> scheduleDetails = scheduleDetailService.findAllByScheduleId(scheduleId);
        return new ResponseEntity<>(scheduleDetails, HttpStatus.OK);
    }

    @PutMapping("/scheduleDetail/update/{scheduleDetailId}")
    public ResponseEntity<?> updateScheduleDetail(@PathVariable("scheduleDetailId") Long scheduleDetailId, @RequestBody ScheduleDetailDTO scheduleDetailDTO) {
        ScheduleDetail scheduleDetail = scheduleDetailService.updateScheduleDetail(scheduleDetailDTO, scheduleDetailId);
        return new ResponseEntity<>(scheduleDetail, HttpStatus.OK);
    }

    @DeleteMapping("scheduleDetail/delete/{scheduleDetailId}")
    public ResponseEntity<?> deleteScheduleDetail(@PathVariable("scheduleDetailId") Long scheduleDetailId) {
        scheduleDetailService.deleteScheduleDetail(scheduleDetailId);
        return new ResponseEntity<>("xoa scheduleDetail thanh cong", HttpStatus.OK);
    }
}
