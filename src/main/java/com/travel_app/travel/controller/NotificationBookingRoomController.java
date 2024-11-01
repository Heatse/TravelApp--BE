package com.travel_app.travel.controller;


import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.BookingRoom;
import com.travel_app.travel.repository.BookingRoomRepository;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.security.services.UserDetailsServiceImpl;
import com.travel_app.travel.service.IUserService;
import com.travel_app.travel.service.impl.MailService;
import com.travel_app.travel.service.impl.NotifiBRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class NotificationBookingRoomController {

    @Autowired
    private BookingRoomRepository bookingRoomRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    IUserService userService;

    @Autowired
    MailService mailService;

    @Autowired
    NotifiBRService notifiBRService;

    @GetMapping("/bookingRoom/checkintoday")
    public ResponseEntity<?> getBookingsForToday() {
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

//        List<BookingRoom> bookings = notifiBRService.checkCheckinBookingsAndNotify();
        return ResponseEntity.ok("");
    }
}
