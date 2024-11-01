package com.travel_app.travel.service.impl;


import com.travel_app.travel.converter.ScheduleDetailConverter;
import com.travel_app.travel.dto.ScheduleDetailDTO;
import com.travel_app.travel.entity.ScheduleDetail;
import com.travel_app.travel.repository.ScheduleDetailRepository;
import com.travel_app.travel.service.IScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleDetailService implements IScheduleDetailService {

    @Autowired
    ScheduleDetailRepository scheduleDetailRepository;

    @Autowired
    ScheduleDetailConverter scheduleDetailConverter;


    @Override
    public ScheduleDetail addNewScheduleDetail(ScheduleDetailDTO scheduleDetailDTO) {
        ScheduleDetail scheduleDetail = scheduleDetailConverter.toEntity(scheduleDetailDTO);
        scheduleDetail = scheduleDetailRepository.save(scheduleDetail);
        return scheduleDetail;
    }

    @Override
    public List<ScheduleDetail> findAllByScheduleId(Long scheduleId) {
        return scheduleDetailRepository.findByScheduleId(scheduleId);
    }

    @Override
    public ScheduleDetail updateScheduleDetail(ScheduleDetailDTO scheduleDetailDTO, Long scheduleDetailId) {

        Optional<ScheduleDetail> scheduleDetail = scheduleDetailRepository.findById(scheduleDetailId);
        ScheduleDetail scheduleDetail1 = scheduleDetailConverter.toEntityUpdate(scheduleDetailDTO, scheduleDetail.get());
        scheduleDetail1.setId(scheduleDetailId);
        scheduleDetail1 = scheduleDetailRepository.save(scheduleDetail1);
        return scheduleDetail1;
    }

    @Override
    public void deleteScheduleDetail(Long scheduleDetailId) {
        scheduleDetailRepository.deleteById(scheduleDetailId);
    }
}
