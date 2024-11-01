package com.travel_app.travel.service;

import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.dto.RoomDTO;
import com.travel_app.travel.dto.RoomSearch;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.entity.RoomEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRoomService {
    RoomDTO addNewRoom(RoomDTO roomDTO);

    RoomEntity findRoomByID(Long roomId);

    RoomDTO save(RoomDTO roomDTO);

    List<RoomEntity> getRandomRooms();

    List<RoomDTO> getTopRoomsByAvgStars();

    Double getAverageStars(Long roomId);

//    List<RoomDTO> findRoomsByKey(String key);
//
    List<RoomEntity> searchRooms(RoomSearch roomSearch);
//
//    List<RoomEntity> findByLocationContaining(String keyword);

    List<RoomEntity> findByAccommodationLocationProvinId(Long provinId);

    List<RoomEntity> findByKeyAccName(String keyword);

//    RoomDTO updateRoom(RoomDTO roomDTO);

}
