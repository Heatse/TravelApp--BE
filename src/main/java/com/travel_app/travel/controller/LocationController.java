package com.travel_app.travel.controller;


import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.dto.RoomDTO;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.repository.LocationRepository;
import com.travel_app.travel.service.ICloudinaryService;
import com.travel_app.travel.service.impl.CommentLocationService;
import com.travel_app.travel.service.impl.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    ICloudinaryService cloudinaryService;

    @Autowired
    LocationService locationService;

    @Autowired
    CommentLocationService commentLocationService;

    @Autowired
    LocationRepository locationRepository;

    @PostMapping("/location/create")
    public ResponseEntity<?> createLocation(@RequestParam("name") String name,
                                            @RequestParam("description") String description,
                                            @RequestParam("images") MultipartFile[] files) throws IOException {

        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setName(name);
        locationDTO.setDescription(description);
        List<String> imgs = new ArrayList<>();
        for (MultipartFile file: files) {
            Map<String, String> data = this.cloudinaryService.upload(file);
            imgs.add(data.get("url"));
        }
        locationDTO.setImages(imgs);


        locationDTO = locationService.addNewLocation(locationDTO);

        return new ResponseEntity<>(locationDTO, HttpStatus.CREATED);
    }

    @GetMapping("/locations/random")
    public ResponseEntity<?> getRandomLocations() {
        List<Location> randomLocations = locationService.getRandomLocations();
        return ResponseEntity.ok(randomLocations);
    }

    @GetMapping("/location/{locationId}/averageStars")
    public ResponseEntity<?> getAverageStars(@PathVariable(value = "locationId") Long locationId) {
        Double averageStars = locationService.getAverageStars(locationId);
        int roundedAverageStars = (int) Math.round(averageStars);
        return new ResponseEntity<>(roundedAverageStars, HttpStatus.OK);
    }

    @GetMapping("/toplocations")
    public ResponseEntity<?> getTopLocationsWithHighestAverageStars() {
        List<LocationDTO> locations = locationService.getTopLocationsByAvgStars();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/locations/search")
    public ResponseEntity<?> searchLocations(@RequestParam("keyword") String keyword) {
        List<LocationDTO> locationDTOS = locationService.findLocationsByKey(keyword);
        return new ResponseEntity<>(locationDTOS, HttpStatus.OK);
    }

    @GetMapping("/location/findById/{locationId}")
    public ResponseEntity<?> findLocationById(@PathVariable("locationId") Long locationId) {
       Location location = locationService.findLocationById(locationId);
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @PostMapping("/location/findByKeyName")
    public ResponseEntity<?> findByKeyName(@RequestBody String keyword) {
        System.out.println("keyword: " + keyword.substring(1, keyword.length()-1));
        List<Location> locations = locationService.findByKeyName(keyword.substring(1, keyword.length()-1));
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("DCM")
    public ResponseEntity<?> findAll() {
        List<Location> locations = locationRepository.findAll();
        for(Location location: locations) {
            location.setImages(Arrays.asList(location.getTemp().split("\\|\\|\\|")));
            locationRepository.save(location);
        }
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }


}
