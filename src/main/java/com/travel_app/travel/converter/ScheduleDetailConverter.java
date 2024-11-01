package com.travel_app.travel.converter;

import com.travel_app.travel.dto.ScheduleDTO;
import com.travel_app.travel.dto.ScheduleDetailDTO;
import com.travel_app.travel.entity.Schedule;
import com.travel_app.travel.entity.ScheduleDetail;
import com.travel_app.travel.repository.ScheduleDetailRepository;
import com.travel_app.travel.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ScheduleDetailConverter {

    @Autowired
    ScheduleRepository scheduleRepository;

    public ScheduleDetail toEntity(ScheduleDetailDTO scheduleDetailDTO) {
        ScheduleDetail scheduleDetail = new ScheduleDetail();
        scheduleDetail.setDes(scheduleDetailDTO.getDes());
        scheduleDetail.setStart(scheduleDetailDTO.getStart());
        scheduleDetail.setEnd(scheduleDetailDTO.getEnd());
        Optional<Schedule> schedule = scheduleRepository.findById(scheduleDetailDTO.getScheduleId());
        scheduleDetail.setSchedule(schedule.get());

        return scheduleDetail;
    }

    public ScheduleDetailDTO toDTO(ScheduleDetail scheduleDetail) {
        ScheduleDetailDTO scheduleDetailDTO = new ScheduleDetailDTO();
        scheduleDetailDTO.setDes(scheduleDetail.getDes());
        scheduleDetailDTO.setStart(scheduleDetail.getStart());
        scheduleDetailDTO.setEnd(scheduleDetail.getEnd());
        scheduleDetailDTO.setId(scheduleDetail.getId());
        scheduleDetailDTO.setScheduleId(scheduleDetail.getSchedule().getId());

        return scheduleDetailDTO;
    }

    public ScheduleDetail toEntityUpdate(ScheduleDetailDTO scheduleDetailDTO, ScheduleDetail scheduleDetail) {

        if (scheduleDetailDTO.getDes() != null) {
            scheduleDetail.setDes(scheduleDetailDTO.getDes());
        }
        if (scheduleDetailDTO.getStart() != null) {
            scheduleDetail.setStart(scheduleDetailDTO.getStart());
        }
        if (scheduleDetailDTO.getEnd() != null) {
            scheduleDetail.setEnd(scheduleDetailDTO.getEnd());
        }

        if (scheduleDetailDTO.getScheduleId() != null) {
            Optional<Schedule> schedule = scheduleRepository.findById(scheduleDetailDTO.getScheduleId());
            scheduleDetail.setSchedule(schedule.get());
        }
        return scheduleDetail;
    }
}
