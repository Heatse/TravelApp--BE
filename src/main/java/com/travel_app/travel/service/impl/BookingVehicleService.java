package com.travel_app.travel.service.impl;

import com.travel_app.travel.converter.BookingVehicleConverter;
import com.travel_app.travel.dto.BookingVehicleDTO;
import com.travel_app.travel.entity.BookingVehicle;
import com.travel_app.travel.repository.BookingVehicleRepository;
import com.travel_app.travel.service.IBookingVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingVehicleService implements IBookingVehicleService {

    @Autowired
    private BookingVehicleRepository bookingVehicleRepository;

    @Autowired
    private BookingVehicleConverter bookingVehicleConverter;


    @Override
    public BookingVehicleDTO addNewBookingVehicle(BookingVehicleDTO bookingVehicleDTO) {
        BookingVehicle bookingVehicle = bookingVehicleConverter.toEntity(bookingVehicleDTO);
        bookingVehicle = bookingVehicleRepository.save(bookingVehicle);

//        bỏ bảng Seat đi ==================================
//        Seat seat = new Seat();
//        seat.setSeatNumber(bookingVehicleDTO.getSeatNumber());
//        seat.setStatus(true);
//        seat.setVehicle(bookingVehicle.getVehicle());
//        seatRepository.save(seat);

        return bookingVehicleConverter.toDTO(bookingVehicle);
    }

    @Override
    public List<BookingVehicle> findBookingVehicleById(Long userId) {
        return bookingVehicleRepository.findByUserId(userId);
    }

    @Override
    public BookingVehicle findById(Long bookingVehicleId) {
        Optional<BookingVehicle> bookingVehicle = bookingVehicleRepository.findById(bookingVehicleId);
        return bookingVehicle.get();
    }

    @Override
    public void deleteById(Long bookingVehicleId) {
        bookingVehicleRepository.deleteById(bookingVehicleId);
    }

    @Override
    public List<BookingVehicle> findByDestinationContaining(String keyword) {
        return bookingVehicleRepository.findByDestinationContaining(keyword);
    }
}
