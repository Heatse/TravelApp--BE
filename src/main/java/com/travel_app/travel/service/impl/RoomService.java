package com.travel_app.travel.service.impl;

import com.travel_app.travel.converter.RoomConverter;
import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.dto.RoomDTO;
import com.travel_app.travel.dto.RoomSearch;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.entity.RoomEntity;
import com.travel_app.travel.repository.RoomRepository;
import com.travel_app.travel.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomConverter roomConverter;
    @Override
    public RoomDTO addNewRoom(RoomDTO roomDTO) {
        RoomEntity roomEntity = roomConverter.toEntity(roomDTO);
        roomEntity = roomRepository.save(roomEntity);
        return roomConverter.toDTO(roomEntity);
    }

    public RoomEntity findRoomByID(Long roomId) {
        Optional<RoomEntity> roomEntityOptional = roomRepository.findById(roomId);
        return roomEntityOptional.get();
    }


    @Override
    public RoomDTO save(RoomDTO roomDTO) {
        RoomEntity roomEntity = roomConverter.toEntity(roomDTO);
        roomEntity = roomRepository.save(roomEntity);
        return roomConverter.toDTO(roomEntity);
    }

    @Override
    public List<RoomEntity> getRandomRooms() {
        List<RoomEntity> roomEntities = roomRepository.findRandomRooms();
        return roomEntities;
    }

    @Override
    public List<RoomDTO> getTopRoomsByAvgStars() {
        List<RoomDTO> roomDTOS = new ArrayList<>();
        List<RoomEntity> roomEntities = roomRepository.findTopRoomsByAvgStars();
        for (RoomEntity room: roomEntities) {
            RoomDTO roomDTO = roomConverter.toDTO(room);
            roomDTO.setAvgStars(roomRepository.getAverageStars(room.getId()));
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    @Override
    public Double getAverageStars(Long roomId) {
        return roomRepository.getAverageStars(roomId);
    }

//    @Override
//    public List<RoomDTO> findRoomsByKey(String key) {
//        List<RoomDTO> roomDTOS = new ArrayList<>();
//        List<RoomEntity> roomEntities = roomRepository.findRoomsByKey(key);
//        for (RoomEntity room: roomEntities) {
//            RoomDTO roomDTO = roomConverter.toDTO(room);
//            roomDTO.setAvgStars(roomRepository.getAverageStars(room.getId()));
//            roomDTOS.add(roomDTO);
//        }
//        return roomDTOS;
//    }

    @Override
    public List<RoomEntity> searchRooms(RoomSearch roomSearch) {
        return roomRepository.searchRooms(roomSearch.getAccommodationTypes(), roomSearch.getAccommodationName(),
                roomSearch.getPriceMin(), roomSearch.getPriceMax(), roomSearch.getType());
    }

//    @Override
//    public List<RoomEntity> findByLocationContaining(String keyword) {
//        return roomRepository.findByLocationContaining(keyword);
//    }

    @Override
    public List<RoomEntity> findByAccommodationLocationProvinId(Long provinId) {
        return roomRepository.findByAccommodationProvinId(provinId);
    }

    @Override
    public List<RoomEntity> findByKeyAccName(String keyword) {
        return roomRepository.findByKeyAccName(keyword);
    }

}