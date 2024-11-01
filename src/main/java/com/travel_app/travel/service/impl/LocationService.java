package com.travel_app.travel.service.impl;

import com.travel_app.travel.converter.LocationConverter;
import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.dto.RoomDTO;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.entity.RoomEntity;
import com.travel_app.travel.repository.LocationRepository;
import com.travel_app.travel.service.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements ILocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    LocationConverter locationConverter;

    @Override
    public LocationDTO addNewLocation(LocationDTO locationDTO) {
        Location existingLocation = locationRepository.findByName(locationDTO.getName());
        System.out.println(existingLocation);
        if (existingLocation != null) {
            Long id = existingLocation.getId();
            existingLocation = locationConverter.toEntity(locationDTO);
            existingLocation.setId(id);
            existingLocation = locationRepository.save(existingLocation);
            return locationConverter.toDTO(existingLocation);
        } else {
            Location newLocation = locationConverter.toEntity(locationDTO);
            newLocation = locationRepository.save(newLocation);
            return locationConverter.toDTO(newLocation);
        }
    }

    @Override
    public List<Location> getRandomLocations() {
        List<Location> locations = locationRepository.findRandomLocations();
        return locations;
    }

    @Override
    public Location findLocationById(Long locationId) {
        Optional<Location> locationOptional = locationRepository.findById(locationId);
        Location location = locationOptional.get();
//        location.setImages(Arrays.asList(location.getTemp().split("\\|\\|\\|")));
//        locationRepository.save(location);
        return location;
    }

    @Override
    public Double getAverageStars(Long locationId) {
        return locationRepository.getAverageStars(locationId);
    }

    @Override
    public List<LocationDTO> findLocationsByKey(String key) {
        List<LocationDTO> locationDTOS = new ArrayList<>();
        List<Location> locations = locationRepository.findLocationsByKey(key);
        for (Location location: locations) {
            LocationDTO locationDTO = locationConverter.toDTO(location);
            locationDTO.setAvgStars(locationRepository.getAverageStars(location.getId()));
            locationDTOS.add(locationDTO);
        }
        return locationDTOS;
    }

    @Override
    public List<Location> findByKeyName(String keyword) {
        List<Location> locations = locationRepository.findByKeyName(keyword);
        return locations;
    }

    @Override
    public List<LocationDTO> getTopLocationsByAvgStars() {
        List<LocationDTO> locationDTOS = new ArrayList<>();
        List<Location> locations = locationRepository.findTopLocationsByAvgStars();
        for (Location location: locations) {
            LocationDTO locationDTO = locationConverter.toDTO(location);
            locationDTO.setAvgStars(locationRepository.getAverageStars(location.getId()));
            locationDTOS.add(locationDTO);
        }
        return locationDTOS;
    }

}
