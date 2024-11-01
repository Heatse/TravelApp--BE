package com.travel_app.travel.converter;


import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.entity.Location;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LocationConverter {
    public Location toEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setId(locationDTO.getId());
        location.setName(locationDTO.getName());
        location.setDescription(locationDTO.getDescription());
        location.setImages(locationDTO.getImages());
        location.setShortDes(locationDTO.getShortDes());
        location.setAddress(locationDTO.getAddress());
        return location;
    }

    public LocationDTO toDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setDescription(location.getDescription());
//        locationDTO.setImages(location.getImages());
        locationDTO.setImages(Arrays.asList(location.getTemp().split(",")));
        locationDTO.setAddress(location.getAddress());
        locationDTO.setShortDes(location.getShortDes());

        return locationDTO;
    }
}
