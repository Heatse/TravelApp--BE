package com.travel_app.travel.converter;

import com.travel_app.travel.dto.CommentLocationDTO;
import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.CommentLocation;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.service.ILocationService;
import com.travel_app.travel.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentLocationConverter {

    @Autowired
    LocationConverter locationConverter;

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;

    @Autowired
    ILocationService locationService;

    public CommentLocationDTO toDTO(CommentLocation commentLocation) {
        if (commentLocation == null) {
            return null;
        }
        CommentLocationDTO commentLocationDTO = new CommentLocationDTO();
        commentLocationDTO.setId(commentLocation.getId());
        commentLocationDTO.setStars(commentLocation.getStars());
        commentLocationDTO.setContent(commentLocation.getContent());
        commentLocationDTO.setImages(commentLocation.getImages());
        commentLocationDTO.setLocationId(commentLocation.getLocation().getId());
        commentLocationDTO.setUserId(commentLocation.getUser().getId());
        return commentLocationDTO;
    }

    public  CommentLocation toEntity(CommentLocationDTO commentLocationDTO) {
        if (commentLocationDTO == null) {
            return null;
        }
        CommentLocation commentLocation = new CommentLocation();
        commentLocation.setStars(commentLocationDTO.getStars());
        commentLocation.setContent(commentLocationDTO.getContent());
        commentLocation.setImages(commentLocationDTO.getImages());
        Location location = locationService.findLocationById(commentLocationDTO.getLocationId());
        commentLocation.setLocation(location);
        UserDTO userDTO = userService.findUserById(commentLocationDTO.getUserId());
        commentLocation.setUser(userConverter.toEntity(userDTO));
        return commentLocation;
    }
}
