package com.travel_app.travel.service;

import com.travel_app.travel.dto.BookingRoomDTO;
import com.travel_app.travel.dto.BookingRoomResponse;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.BookingRoom;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBookingRoomService {

    BookingRoomResponse createBookingRoom(BookingRoomDTO bookingRoomDTO);

    List<BookingRoom> findBookingRoom(Long userId);

    BookingRoom findById(Long bookingRoomId);
    void deleteById(Long bookingRoomId);

//    List<BookingRoom> findByAccommodationNameContaining(@Param("keyword") String keyword);
}
