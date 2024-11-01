package com.travel_app.travel.service;

import com.travel_app.travel.dto.VehicleSearch;
import com.travel_app.travel.entity.Vehicle;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IVehicleService {
    List<Vehicle> getAllVehicle();

    List<Vehicle> findVehicleByLocation( String startingLocation, String destination);
    List<Vehicle> findRandomVehicles();

    List<Vehicle> searchForVehicles(VehicleSearch vehicleSearch);
}
