package com.travel_app.travel.service.impl;

import com.travel_app.travel.repository.NotificationBookingVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotofiBVService {

    @Autowired
    NotificationBookingVehicleRepository notificationBookingVehicleRepository;
}
