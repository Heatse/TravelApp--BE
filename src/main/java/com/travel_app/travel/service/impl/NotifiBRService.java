package com.travel_app.travel.service.impl;

import com.travel_app.travel.entity.BookingRoom;
import com.travel_app.travel.entity.BookingVehicle;
import com.travel_app.travel.repository.BookingRoomRepository;
import com.travel_app.travel.repository.BookingVehicleRepository;
import com.travel_app.travel.repository.NotificationBookingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotifiBRService {

    @Autowired
    NotificationBookingRoomRepository notificationBookingRoomRepository;

    @Autowired
    BookingRoomRepository bookingRoomRepository;

    @Autowired
    MailService mailService;

    @Autowired
    BookingVehicleRepository bookingVehicleRepository;

    @Scheduled(cron = "0 00 01 * * ?")
    public void checkCheckinBookingsAndNotify() {
        System.out.println("siuuuu");
        LocalDate today = LocalDate.now();
        List<BookingRoom> bookings = bookingRoomRepository.findByCheckInDate(today.toString());
        for(BookingRoom bookingRoom: bookings) {
            mailService.sendCheckInNotificationEmail(
                    bookingRoom.getUser().getEmail(),
                    bookingRoom.getUser().getFirstName(),
                    bookingRoom.getUser().getLastName(),
                    bookingRoom.getRoom().getAccommodation().getName(),
                    bookingRoom.getRoom().getRoomNumber(),
                    bookingRoom.getCheckInDate());
        }
    }

    @Scheduled(cron = "0 11 01 * * ?")
    public void checkCheckoutBookingsAndNotify() {
        System.out.println("di bo vuot rau");
        LocalDate today = LocalDate.now();
        List<BookingRoom> bookings = bookingRoomRepository.findByCheckOutDate(today.toString());
        for(BookingRoom bookingRoom: bookings) {
            mailService.sendCheckOutNotificationEmail(
                    bookingRoom.getUser().getEmail(),
                    bookingRoom.getUser().getFirstName(),
                    bookingRoom.getUser().getLastName(),
                    bookingRoom.getRoom().getAccommodation().getName(),
                    bookingRoom.getRoom().getRoomNumber(),
                    bookingRoom.getCheckOutDate());
        }
    }

    @Scheduled(cron = "0 16 02 * * ?")
    public void checkBookingVehicleAndNotify() {
        System.out.println("siiiiiiiii");
        LocalDate today = LocalDate.now();
        List<BookingVehicle> bookings = bookingVehicleRepository.findByMovingDate(today.toString());
        for(BookingVehicle bookingVehicle: bookings) {
            mailService.sendBookingVehicleNotificationEmail(
                    bookingVehicle.getUser().getEmail(),
                    bookingVehicle.getUser().getFirstName(),
                    bookingVehicle.getUser().getLastName(),
                    bookingVehicle.getSeatNumber(),
                    bookingVehicle.getVehicle().getVehicleType(),
                    bookingVehicle.getVehicle().getBrand(),
                    bookingVehicle.getVehicle().getStartingLocation(),
                    bookingVehicle.getVehicle().getDestination(),
                    bookingVehicle.getVehicle().getTravelTime(),
                    bookingVehicle.getVehicle().getPrice()
            );
        }
    }




}
