package com.travel_app.travel.controller;

import com.travel_app.travel.dto.*;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.entity.RoomEntity;
import com.travel_app.travel.entity.Vehicle;
import com.travel_app.travel.repository.RoomRepository;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.service.ICloudinaryService;
import com.travel_app.travel.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    ICloudinaryService cloudinaryService;

    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/room/create")
    public ResponseEntity<?> createLocation(@RequestParam("accommodationName") String accommodationName,
                                            @RequestParam("accommodationType") String accommodationType,
                                            @RequestParam("location") String location,
                                            @RequestParam("roomNumber") Integer roomNumber,
                                            @RequestParam("type") String type,
                                            @RequestParam("description") String description,
                                            @RequestParam("capacity") Integer capacity,
                                            @RequestParam("price") BigDecimal price,
                                            @RequestParam("available") Boolean available,
                                            @RequestParam("images") MultipartFile[] files) throws IOException {

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomNumber(roomNumber);
        roomDTO.setType(type);
        roomDTO.setDescription(description);
        roomDTO.setCapacity(capacity);
        roomDTO.setPrice(price);
        List<String> imgs = new ArrayList<>();
        for (MultipartFile file: files) {
            Map<String, String> data = this.cloudinaryService.upload(file);
            imgs.add(data.get("url"));
        }
        roomDTO.setImages(imgs);

        roomDTO = roomService.addNewRoom(roomDTO);

        return new ResponseEntity<>(roomDTO, HttpStatus.CREATED);
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable(value = "roomId") Long roomId) {
        RoomEntity room = roomService.findRoomByID(roomId);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
//    @GetMapping("")
//    public ResponseEntity<?> findAll() {
//        List<RoomEntity> roomEntities = roomRepository.findAll();
//        for (RoomEntity roomEntity: roomEntities) {
//            roomEntity.setImages(Arrays.asList(roomEntity.getTemp().split(",")));
//            roomRepository.save(roomEntity);
//        }
//        return new ResponseEntity<>(roomEntities, HttpStatus.OK);
//    }

    @GetMapping("/rooms/random")
    public ResponseEntity<?> getRandomRooms() {
        List<RoomEntity> randomRooms = roomService.getRandomRooms();
        return ResponseEntity.ok(randomRooms);
    }

    @GetMapping("/room/{roomId}/averageStars")
    public ResponseEntity<?> getAverageStars(@PathVariable(value = "roomId") Long roomId) {
        Double averageStars = roomService.getAverageStars(roomId);
        int roundedAverageStars = (int) Math.round(averageStars);
        return new ResponseEntity<>(roundedAverageStars, HttpStatus.OK);
    }

    @GetMapping("/toprooms")
    public ResponseEntity<?> getTopRoomsWithHighestAverageStars() {
        List<RoomDTO> rooms = roomService.getTopRoomsByAvgStars();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }


    @PostMapping("/rooms/search")
    public List<RoomEntity> searchRooms(@RequestBody RoomSearch roomSearch) {
        return roomService.searchRooms(roomSearch);
    }

    @GetMapping("/room/findByProvinId/{provinId}")
    public ResponseEntity<?> findByProvinId(@PathVariable("provinId") Long provinId) {
        List<RoomEntity> roomEntities = roomService.findByAccommodationLocationProvinId(provinId);
        return new ResponseEntity<>(roomEntities, HttpStatus.OK);
    }

    @PostMapping("/room/findByKeyAccName")
    public ResponseEntity<?> findByKeyAccName(@RequestBody String keyword) {
        List<RoomEntity> roomEntities = roomService.findByKeyAccName(keyword.substring(1, keyword.length() - 1));
        return new ResponseEntity<>(roomEntities, HttpStatus.OK);
    }

    @GetMapping("/ERROR")
    public ResponseEntity<?> getAll() {
        List<RoomEntity> roomEntities = roomRepository.findAll();
        for(RoomEntity roomEntity: roomEntities) {
            roomEntity.setImages(Arrays.asList(roomEntity.getTemp().split("\\|\\|\\|")));
            roomRepository.save(roomEntity);
        }
        return new ResponseEntity<>(roomEntities, HttpStatus.OK);
    }




}