package com.travel_app.travel.converter;

import com.travel_app.travel.dto.*;
import com.travel_app.travel.entity.CommentLocation;
import com.travel_app.travel.entity.CommentRoom;
import com.travel_app.travel.entity.RoomEntity;
import com.travel_app.travel.repository.RoomRepository;
import com.travel_app.travel.service.IRoomService;
import com.travel_app.travel.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentRoomConverter {
    @Autowired
    private IRoomService roomService;

    @Autowired
    private RoomConverter roomConverter;

    @Autowired
    UserConverter userConverter;

    @Autowired
    UserService userService;

    @Autowired
    RoomRepository roomRepository;

    public CommentRoomDTO toDTO(CommentRoom commentRoom) {
        if (commentRoom == null) {
            return null;
        }
        CommentRoomDTO commentRoomDTO = new CommentRoomDTO();
        commentRoomDTO.setId(commentRoom.getId());
        commentRoomDTO.setStars(commentRoom.getStars());
        commentRoomDTO.setContent(commentRoom.getContent());
        commentRoomDTO.setImages(commentRoom.getImages());
        commentRoomDTO.setRoomId(commentRoom.getRoom().getId());
        commentRoomDTO.setUserId(commentRoom.getUser().getId());
        return commentRoomDTO;
    }

    public  CommentRoom toEntity(CommentRoomDTO commentRoomDTO) {
        if (commentRoomDTO == null) {
            return null;
        }
        CommentRoom commentRoom = new CommentRoom();
        commentRoom.setStars(commentRoomDTO.getStars());
        commentRoom.setContent(commentRoomDTO.getContent());
        commentRoom.setImages(commentRoomDTO.getImages());
        Optional<RoomEntity> roomEntity  = roomRepository.findById(commentRoomDTO.getRoomId());
        commentRoom.setRoom(roomEntity.get());
        UserDTO userDTO = userService.findUserById(commentRoomDTO.getUserId());
        commentRoom.setUser(userConverter.toEntity(userDTO));
        return commentRoom;
    }


}
