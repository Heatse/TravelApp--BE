package com.travel_app.travel.controller;

import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.dto.VehicleSearch;
import com.travel_app.travel.entity.BookingVehicle;
import com.travel_app.travel.entity.Vehicle;
import com.travel_app.travel.repository.BookingVehicleRepository;
import com.travel_app.travel.repository.VehicleRepository;
import com.travel_app.travel.service.impl.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    BookingVehicleRepository bookingVehicleRepository;

    @GetMapping("/vehicle/findByLocation")
    public ResponseEntity<?> findVehicleByLocation(@RequestParam(value = "startingLocation") String startingLocation, @RequestParam(value = "destination") String destination) {
        List<Vehicle> vehicleList = vehicleService.findVehicleByLocation(startingLocation, destination);
        return new ResponseEntity<>(vehicleList, HttpStatus.OK);
    }

    @GetMapping("/vehicle/findById/{vehicleId}")
    public ResponseEntity<?> findVehicleById(@PathVariable(value = "vehicleId") Long vehicleId) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        if (!vehicleOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Vehicle vehicle = vehicleOptional.get();

        List<BookingVehicle> bookingVehicleList = bookingVehicleRepository.findByVehicleId(vehicleId);
        List<String> bookedSeats = new ArrayList<>();
        for (BookingVehicle bookingVehicle : bookingVehicleList) {
            bookedSeats.add(String.valueOf(bookingVehicle.getSeatNumber()));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("vehicle", vehicle);
        response.put("bookedSeats", bookedSeats);

        return ResponseEntity.ok(response);

    }
    @GetMapping("/vehicles/random")
    public ResponseEntity<?> getRandomVehicles() {
        List<Vehicle> vehicleList = vehicleService.findRandomVehicles();
        return ResponseEntity.ok(vehicleList);
    }

    @PostMapping("/vehicles/search")
    public List<Vehicle> searchVehicles(@RequestBody VehicleSearch vehicleSearch) {
        return vehicleService.searchForVehicles(vehicleSearch);
    }

}
