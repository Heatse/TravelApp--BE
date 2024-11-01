package com.travel_app.travel.dto;

import com.travel_app.travel.entity.BookingRoom;
import lombok.Data;

@Data
public class BookingRoomResponse {
    private String message;
    private BookingRoom bookingRoom;

}
