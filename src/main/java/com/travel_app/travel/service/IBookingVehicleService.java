package com.travel_app.travel.service;

import com.travel_app.travel.dto.BookingVehicleDTO;
import com.travel_app.travel.entity.BookingVehicle;
import com.travel_app.travel.entity.Vehicle;

import java.util.List;

public interface IBookingVehicleService {

    BookingVehicleDTO addNewBookingVehicle(BookingVehicleDTO bookingVehicleDTO);

    List<BookingVehicle> findBookingVehicleById(Long userId);

    BookingVehicle findById(Long bookingVehicleId);

    void deleteById(Long bookingVehicleId);

    List<BookingVehicle> findByDestinationContaining(String keyword);
}
