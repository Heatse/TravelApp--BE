package com.travel_app.travel.service;

import java.util.List;

public interface ISeatService {

    List<Long> findEmptySeats(Long vehicleId);
}
