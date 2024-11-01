package com.travel_app.travel.dto;

import com.travel_app.travel.entity.RoomEntity;
import com.travel_app.travel.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRoomDTO {
    private Long id;
    private String checkInDate;
    private String checkOutDate;
    private Long roomId;
    private Long userId;
//    private Date bookingDate;
}
