package com.travel_app.travel.service;

import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.entity.Location;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILocationService {

    LocationDTO addNewLocation(LocationDTO locationDTO);

    List<Location> getRandomLocations();

    Location findLocationById(Long locationId);

    List<LocationDTO> getTopLocationsByAvgStars();

    Double getAverageStars(Long locationId);

    List<LocationDTO> findLocationsByKey(String key);

    List<Location> findByKeyName(@Param("keyword") String keyword);
}
