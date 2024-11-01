package com.travel_app.travel.repository;

import com.travel_app.travel.entity.BookingRoom;
import com.travel_app.travel.entity.BookingVehicle;
import com.travel_app.travel.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingVehicleRepository extends JpaRepository<BookingVehicle, Long> {
    List<BookingVehicle> findByUserId(Long userId);

    @Query("SELECT bv FROM BookingVehicle bv " +
            "WHERE bv.vehicle.destination LIKE %:keyword%")
    List<BookingVehicle> findByDestinationContaining(@Param("keyword") String keyword);

    List<BookingVehicle> findByVehicleId(Long vehicleId);

    @Query("SELECT b FROM BookingVehicle b WHERE b.vehicle.movingDate = :currentDate")
    List<BookingVehicle> findByMovingDate(@Param("currentDate") String currentDate);
}
