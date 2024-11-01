package com.travel_app.travel.service;

import com.travel_app.travel.dto.ScheduleDetailDTO;
import com.travel_app.travel.entity.ScheduleDetail;

import java.util.List;

public interface IScheduleDetailService {
    ScheduleDetail addNewScheduleDetail(ScheduleDetailDTO scheduleDetailDTO);
    List<ScheduleDetail> findAllByScheduleId (Long scheduleId);

    ScheduleDetail updateScheduleDetail(ScheduleDetailDTO scheduleDetailDTO, Long scheduleDetailId);

    void deleteScheduleDetail(Long scheduleDetailId);
}
