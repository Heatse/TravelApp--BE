package com.travel_app.travel.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "notification-bookingvehicle")
public class NotificationBookingVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @OneToOne
    @JoinColumn(name = "bookingvehicle_id")
    private BookingVehicle bookingVehicle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
