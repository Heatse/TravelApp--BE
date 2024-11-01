package com.travel_app.travel.converter;

import com.travel_app.travel.dto.BookingRoomDTO;
import com.travel_app.travel.dto.RoomDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.BookingRoom;
import com.travel_app.travel.entity.RoomEntity;
import com.travel_app.travel.entity.User;
import com.travel_app.travel.repository.RoomRepository;
import com.travel_app.travel.repository.UserRepository;
import com.travel_app.travel.service.impl.RoomService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class BookingRoomConverter {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomService roomService;

    public BookingRoomDTO toDTO(BookingRoom bookingRoom) {
        BookingRoomDTO bookingRoomDto = new BookingRoomDTO();
        bookingRoomDto.setId(bookingRoom.getId());
        bookingRoomDto.setCheckInDate(bookingRoom.getCheckInDate());
        bookingRoomDto.setCheckOutDate(bookingRoom.getCheckOutDate());
        bookingRoomDto.setRoomId(bookingRoom.getRoom().getId());
        bookingRoomDto.setUserId(bookingRoom.getUser().getId());
//        bookingRoomDto.setBookingDate(bookingRoom.getBookingDate());
        return bookingRoomDto;
    }

    public BookingRoom toEntity(BookingRoomDTO bookingRoomDTO) {
        BookingRoom bookingRoom = new BookingRoom();
        bookingRoom.setCheckInDate(bookingRoomDTO.getCheckInDate());
        bookingRoom.setCheckOutDate(bookingRoomDTO.getCheckOutDate());
        Optional<RoomEntity> room = roomRepository.findById(bookingRoomDTO.getRoomId());
        bookingRoom.setRoom(room.get());
        Optional<User> user = userRepository.findById(bookingRoomDTO.getUserId());
        bookingRoom.setUser(user.get());
//        bookingRoom.setBookingDate(bookingRoomDTO.getBookingDate());
        return bookingRoom;
    }
}
