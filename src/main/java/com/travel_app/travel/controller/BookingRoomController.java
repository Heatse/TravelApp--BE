package com.travel_app.travel.controller;

import com.travel_app.travel.dto.BookingRoomDTO;
import com.travel_app.travel.dto.BookingRoomResponse;
import com.travel_app.travel.dto.RoomDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.BookingRoom;
import com.travel_app.travel.entity.User;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.security.services.UserDetailsServiceImpl;
import com.travel_app.travel.service.IBookingRoomService;
import com.travel_app.travel.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BookingRoomController {

    @Autowired
    IBookingRoomService bookingRoomService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    IUserService userService;


    @PostMapping("/bookingroom/create")
    public ResponseEntity<?> createBookingRoom(@RequestBody BookingRoomDTO bookingRoomDTO) {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }
        bookingRoomDTO.setUserId(userId);
        BookingRoomResponse bookingRoomResponse = bookingRoomService.createBookingRoom(bookingRoomDTO);
        return new ResponseEntity<>(bookingRoomResponse, HttpStatus.CREATED);
    }



    @GetMapping("/bookingroom/findAll")
    public ResponseEntity<?> findAll() {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }
        List<BookingRoom> bookingRooms = bookingRoomService.findBookingRoom(userId);
        return new ResponseEntity<>(bookingRooms, HttpStatus.OK);
    }

    @GetMapping("/bookingroom/findById/{bookingRoomId}")
    public ResponseEntity<?> findById(@PathVariable(value = "bookingRoomId") Long bookingRoomId) {
//        Object userCurrent = userDetailsService.getCurrentUser();
//        Long userId = null;
//        if (userCurrent instanceof UserDetails) {
//            userId = ((UserDetailsImpl) userCurrent).getId();
//        } else if (userCurrent instanceof OAuth2User) {
//            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
//            userId = userDTO.getId();
//        } else if (userCurrent == null) {
//            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
//        }
        BookingRoom bookingRoom = bookingRoomService.findById(bookingRoomId);
        return new ResponseEntity<>(bookingRoom, HttpStatus.OK);
    }

    @DeleteMapping("/bookingroom/deleteById/{bookingRoomId}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "bookingRoomId") Long bookingRoomId) {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }
        BookingRoom bookingRoom = bookingRoomService.findById(bookingRoomId);
        Long bookingUserId = bookingRoom.getUser().getId();
        if (bookingUserId == userId) {
            bookingRoomService.deleteById(bookingRoomId);
            return new ResponseEntity<>("Huy dat phong thanh cong", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Huy dat phong that bai", HttpStatus.BAD_REQUEST);
        }

    }


//    @PostMapping("/bookingroom/findByAccName")
//    public List<BookingRoom> findByAccommodationNameContaining(@RequestBody String keyword) {
//        return bookingRoomService.findByAccommodationNameContaining(keyword.substring(1, keyword.length()-1));
//    }



}
