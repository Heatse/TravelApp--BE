package com.travel_app.travel.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "notification-bookingroom")
public class NotificationBookingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bookingroom_id")
    private BookingRoom bookingRoom;

    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
