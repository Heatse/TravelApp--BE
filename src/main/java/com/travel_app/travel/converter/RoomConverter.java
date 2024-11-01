package com.travel_app.travel.converter;

import com.travel_app.travel.dto.ImageDTO;
import com.travel_app.travel.dto.RoomDTO;
import com.travel_app.travel.entity.RoomEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class RoomConverter {
    public static RoomDTO toDTO(RoomEntity roomEntity) {
        if (roomEntity == null) {
            return null;
        }
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(roomEntity.getId());
        roomDTO.setRoomNumber(roomEntity.getRoomNumber());
        roomDTO.setType(roomEntity.getType());
        roomDTO.setDescription(roomEntity.getDescription());
        roomDTO.setCapacity(roomEntity.getCapacity());
        roomDTO.setPrice(roomEntity.getPrice());
        roomDTO.setImages(roomEntity.getImages());
        return roomDTO;
    }

    public static   RoomEntity toEntity(RoomDTO roomDTO) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber(roomDTO.getRoomNumber());
        roomEntity.setType(roomDTO.getType());
        roomEntity.setDescription(roomDTO.getDescription());
        roomEntity.setCapacity(roomDTO.getCapacity());
        roomEntity.setPrice(roomDTO.getPrice());
        roomEntity.setImages(roomDTO.getImages());
        return roomEntity;
    }

    public static RoomEntity toEntity(RoomDTO roomDTO, RoomEntity exroomEntity) {
        if(roomDTO.getRoomNumber() != 0) {
            exroomEntity.setRoomNumber(roomDTO.getRoomNumber());
        }
        if(roomDTO.getType() != null) {
            exroomEntity.setType(roomDTO.getType());
        }
        if(roomDTO.getDescription() != null) {
            exroomEntity.setDescription(roomDTO.getDescription());
        }
        if(roomDTO.getCapacity() != 0) {
            exroomEntity.setCapacity(roomDTO.getCapacity());
        }
        if(roomDTO.getPrice() != null) {
            exroomEntity.setPrice(roomDTO.getPrice());
        }
        if(roomDTO.getImages() != null) {
            exroomEntity.setImages(roomDTO.getImages());
        }
        if(roomDTO.getTemp() != null) {
            exroomEntity.setTemp(roomDTO.getTemp());
        }
        return exroomEntity;
    }
}