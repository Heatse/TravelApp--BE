package com.travel_app.travel.service.impl;


import com.travel_app.travel.dto.VehicleSearch;
import com.travel_app.travel.entity.Vehicle;
import com.travel_app.travel.repository.BookingVehicleRepository;
import com.travel_app.travel.repository.VehicleRepository;
import com.travel_app.travel.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findVehicleByLocation(String startingLocation, String destination) {
        return vehicleRepository.findVehicleByLocation(startingLocation, destination);
    }

    @Override
    public List<Vehicle> findRandomVehicles() {
        return vehicleRepository.findRandomVehicles();
    }

    @Override
    public List<Vehicle> searchForVehicles(VehicleSearch vehicleSearch) {
        return vehicleRepository.searchVehicles(vehicleSearch.getBrand(), vehicleSearch.getDestination(),
                                vehicleSearch.getPriceMin(), vehicleSearch.getPriceMax(),
                                vehicleSearch.getStartingLocation(), vehicleSearch.getVehicleTypes(), vehicleSearch.getMovingDate());
    }
}
