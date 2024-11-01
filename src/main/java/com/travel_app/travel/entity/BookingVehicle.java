package com.travel_app.travel.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "bookingVehicle")
public class BookingVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // @ManyToOne
    // @JoinColumn(name = "seat_id")
    // private Seat seat;

    private Long seatNumber;

    private Date bookingDate;
}
