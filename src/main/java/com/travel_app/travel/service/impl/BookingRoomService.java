package com.travel_app.travel.service.impl;

import com.travel_app.travel.converter.BookingRoomConverter;
import com.travel_app.travel.converter.RoomConverter;
import com.travel_app.travel.converter.UserConverter;
import com.travel_app.travel.dto.BookingRoomDTO;
import com.travel_app.travel.dto.BookingRoomResponse;
import com.travel_app.travel.dto.RoomDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.BookingRoom;
import com.travel_app.travel.entity.RoomEntity;
import com.travel_app.travel.repository.BookingRoomRepository;
import com.travel_app.travel.repository.RoomRepository;
import com.travel_app.travel.security.payload.response.MessageResponse;
import com.travel_app.travel.service.IBookingRoomService;
import com.travel_app.travel.service.IRoomService;
import com.travel_app.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingRoomService implements IBookingRoomService {

    @Autowired
    private BookingRoomRepository bookingRoomRepository;

    @Autowired
    private BookingRoomConverter bookingRoomConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private IUserService userService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomConverter roomConverter;


    @Override
    public BookingRoomResponse createBookingRoom(BookingRoomDTO bookingRoomDTO) {
        BookingRoomResponse bookingRoomResponse = new BookingRoomResponse();
        if (isBookingConflict(bookingRoomDTO.getRoomId(), bookingRoomDTO.getCheckInDate(), bookingRoomDTO.getCheckOutDate())) {
            bookingRoomResponse.setMessage("Phong khong kha dung trong thoi gian nay");
            return bookingRoomResponse;
        } else {
            BookingRoom bookingRoom = bookingRoomConverter.toEntity(bookingRoomDTO);
            bookingRoom.setBookingDate(new Date());
            bookingRoom = bookingRoomRepository.save(bookingRoom);
            bookingRoomResponse.setBookingRoom(bookingRoom);
            bookingRoomResponse.setMessage("dat phong thanh cong");
            return bookingRoomResponse;
        }

    }

    @Override
    public List<BookingRoom> findBookingRoom(Long userId) {
        Optional<List<BookingRoom>> bookingRooms = bookingRoomRepository.findByUserId(userId);

//        List<BookingRoomDTO> bookingRoomDTOS = new ArrayList<>();
//        for (BookingRoom bookingRoom : bookingRooms.get()) {
//            BookingRoomDTO bookingRoomDTO = bookingRoomConverter.toDTO(bookingRoom);
//            bookingRoomDTOS.add(bookingRoomDTO);
//        }

        return bookingRooms.get();

    }

    @Override
    public BookingRoom findById(Long bookingRoomId) {
        Optional<BookingRoom> bookingRoom = bookingRoomRepository.findById(bookingRoomId);
        return bookingRoom.get();
    }

    @Override
    public void deleteById(Long bookingRoomId) {
        bookingRoomRepository.deleteById(bookingRoomId);
    }

//    @Override
//    public List<BookingRoom> findByAccommodationNameContaining(String keyword) {
//        return bookingRoomRepository.findByAccommodationNameContaining(keyword);
//    }

    public boolean isBookingConflict(Long roomId, String checkInDate, String checkOutDate) {
        List<BookingRoom> conflictingBookings = bookingRoomRepository.findConflictingBookings(roomId, checkInDate, checkOutDate);
        return !conflictingBookings.isEmpty();
    }
}
