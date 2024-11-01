package com.travel_app.travel.converter;

import com.travel_app.travel.dto.BookingVehicleDTO;
import com.travel_app.travel.entity.BookingVehicle;
import com.travel_app.travel.entity.User;
import com.travel_app.travel.entity.Vehicle;
import com.travel_app.travel.repository.UserRepository;
import com.travel_app.travel.repository.VehicleRepository;
import com.travel_app.travel.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static com.travel_app.travel.entity.BookingVehicle.*;

@Component
public class BookingVehicleConverter {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    public BookingVehicle toEntity(BookingVehicleDTO bookingVehicleDTO) {
        BookingVehicle bookingVehicle = new BookingVehicle();
        Optional<User> user= userRepository.findById(bookingVehicleDTO.getUserId());
        bookingVehicle.setUser(user.get());
        Optional<Vehicle> vehicle = vehicleRepository.findById(bookingVehicleDTO.getVehicleId());
        bookingVehicle.setVehicle(vehicle.get());
        bookingVehicle.setSeatNumber(bookingVehicleDTO.getSeatNumber());
        bookingVehicle.setBookingDate(new Date());

        // Seat seat = seatRepository.findBySeatNumber(bookingVehicleDTO.getSeatNumber());
        // bookingVehicle.setSeat(seat);

        return bookingVehicle;
    }

    public BookingVehicleDTO toDTO(BookingVehicle bookingVehicle) {
        BookingVehicleDTO bookingVehicleDTO = new BookingVehicleDTO();
        bookingVehicleDTO.setId(bookingVehicle.getId());
        bookingVehicleDTO.setUserId(bookingVehicle.getUser().getId());
        bookingVehicleDTO.setVehicleId(bookingVehicle.getVehicle().getId());
        // bookingVehicleDTO.setSeatNumber(bookingVehicle.getSeat().getSeatNumber());
        return bookingVehicleDTO;
    }
}
