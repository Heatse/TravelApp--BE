package com.travel_app.travel.controller;

import com.travel_app.travel.dto.CommentLocationDTO;
import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.CommentLocation;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.repository.CommentLocationRepository;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.security.services.UserDetailsServiceImpl;
import com.travel_app.travel.service.ICloudinaryService;
import com.travel_app.travel.service.IUserService;
import com.travel_app.travel.service.impl.CommentLocationService;
import com.travel_app.travel.service.impl.LocationService;
import com.travel_app.travel.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CommentLocationController {

    @Autowired
    CommentLocationService commentLocationService;

    @Autowired
    LocationService locationService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    IUserService userService;

    @Autowired
    ICloudinaryService cloudinaryService;

    @Autowired
    CommentLocationRepository commentLocationRepository;


    @PostMapping("/location/{locationId}/comment/create")
    public ResponseEntity<?> createCommentLocation (@PathVariable(value = "locationId") Long locationId,
                                                    @RequestParam("stars") int stars,
                                                    @RequestParam("content") String content,
                                                    @RequestParam(value = "images", required = false) MultipartFile[] files) throws IOException {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }

        CommentLocationDTO commentLocationDTO = new CommentLocationDTO();
        commentLocationDTO.setStars(stars);
        commentLocationDTO.setContent(content);
        commentLocationDTO.setLocationId(locationId);
        if (files != null && files.length > 0) {
            List<String> imgs = new ArrayList<>();
            for (MultipartFile file: files) {
                Map<String, String> data = this.cloudinaryService.upload(file);
                imgs.add(data.get("url"));
            }
            commentLocationDTO.setImages(imgs);
        } else {
            commentLocationDTO.setImages((new ArrayList<>()));
        }

        commentLocationDTO.setUserId(userId);

        commentLocationDTO = commentLocationService.addNewCommentLocation(commentLocationDTO);

        return new ResponseEntity<>(commentLocationDTO, HttpStatus.CREATED);
    }

    @GetMapping("/location/{locationId}/comment/findAll")
    public ResponseEntity<?> findAllCommnet(@PathVariable("locationId") Long locationId) {
        List<CommentLocation> commentLocations = commentLocationService.findByLocationId(locationId);
        return new ResponseEntity<>(commentLocations, HttpStatus.OK);
    }

    @DeleteMapping("/location/comment/delete/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "commentId") Long commentId) {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }
        Optional<CommentLocation> commentLocation = commentLocationRepository.findById(commentId);
        Long commentUserId = commentLocation.get().getUser().getId();
        if (commentUserId == userId) {
            commentLocationService.deleteComment(commentId);
            return new ResponseEntity<>("xoa comment thanh cong", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("co phai qua m dau m xoa", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/location/{locationId}/totalComments")
    public ResponseEntity<?> countByLocationId(@PathVariable("locationId") Long locationId) {
        Long count = commentLocationRepository.countByLocationId(locationId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
