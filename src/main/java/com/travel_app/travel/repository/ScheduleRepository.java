package com.travel_app.travel.repository;

import com.travel_app.travel.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByUserId(Long userId);
    Schedule findByLocationId(Long locationId);

    @Query("SELECT s FROM Schedule s " +
            "WHERE s.location.name LIKE %:keyword%")
    List<Schedule> findByLocationName(@Param("keyword") String keyword);
}
