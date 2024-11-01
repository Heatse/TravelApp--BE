package com.travel_app.travel.controller;

import com.travel_app.travel.dto.BookingVehicleDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.BookingVehicle;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.security.services.UserDetailsServiceImpl;
import com.travel_app.travel.service.IUserService;
import com.travel_app.travel.service.impl.BookingVehicleService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BookingVehicleController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    IUserService userService;

    @Autowired
    BookingVehicleService bookingVehicleService;


    @PostMapping("/bookingvehicle/create")
    public ResponseEntity<?> createBookingVehicle(@RequestBody BookingVehicleDTO bookingVehicleDTO) {
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

        bookingVehicleDTO.setUserId(userId);
        bookingVehicleDTO  = bookingVehicleService.addNewBookingVehicle(bookingVehicleDTO);
        return new ResponseEntity<>(bookingVehicleDTO, HttpStatus.CREATED);
    }

    @GetMapping("/bookingvehicle/findAll")
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

        List<BookingVehicle> bookingVehicles = bookingVehicleService.findBookingVehicleById(userId);
        return new ResponseEntity<>(bookingVehicles, HttpStatus.OK);
    }

    @GetMapping("/bookingvehicle/findById/{bookingvehicleId}")
    public ResponseEntity<?> findById(@PathVariable(value = "bookingvehicleId") Long bookingvehicleId) {
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

        BookingVehicle bookingVehicle = bookingVehicleService.findById(bookingvehicleId);
        return new ResponseEntity<>(bookingVehicle, HttpStatus.OK);
    }

    @DeleteMapping("/bookingvehicle/deleteById/{bookingvehicleId}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "bookingvehicleId") Long bookingvehicleId) {
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
        BookingVehicle bookingVehicle = bookingVehicleService.findById(bookingvehicleId);
        Long bookingUserId = bookingVehicle.getUser().getId();
        if (bookingUserId == userId) {
            bookingVehicleService.deleteById(bookingvehicleId);
            return new ResponseEntity<>("Huy dat ve thanh cong", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Huy dat ve that bai", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/bookingVehicle/findByDestination")
    public List<BookingVehicle> findByDestination(@RequestBody String keyword) {
        return bookingVehicleService.findByDestinationContaining(keyword.substring(1, keyword.length()-1));
    }


}
