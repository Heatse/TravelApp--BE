package com.travel_app.travel.service.impl;

import com.travel_app.travel.converter.CommentRoomConverter;
import com.travel_app.travel.dto.CommentLocationDTO;
import com.travel_app.travel.dto.CommentRoomDTO;
import com.travel_app.travel.entity.CommentLocation;
import com.travel_app.travel.entity.CommentRoom;
import com.travel_app.travel.repository.CommentRoomRepository;
import com.travel_app.travel.service.ICommentRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentRoomService implements ICommentRoomService {
    @Autowired
    CommentRoomRepository commentRoomRepository;
    @Autowired
    CommentRoomConverter commentRoomConverter;

    @Override
    public CommentRoom addNewCommentRoom(CommentRoomDTO commentRoomDTO) {
        CommentRoom commentRoom = commentRoomConverter.toEntity(commentRoomDTO);
        commentRoom.setCommentDate(new Date());
        commentRoom = commentRoomRepository.save(commentRoom);
        return commentRoom;
    }

    @Override
    public List<CommentRoom> getCommentByRoomId(Long roomId) {
        List<CommentRoom> commentRooms = commentRoomRepository.findByRoomId(roomId);
//        List<CommentRoomDTO> commentRoomDTOS = new ArrayList<>();
//        for (CommentRoom commentRoom: commentRooms) {
//            CommentRoomDTO commentRoomDTO = commentRoomConverter.toDTO(commentRoom);
//            commentRoomDTOS.add(commentRoomDTO);
//        }
        return commentRooms;
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRoomRepository.deleteById(commentId);
    }

    @Override
    public Long countByRoomId(Long roomId) {
        return commentRoomRepository.countByRoomId(roomId);
    }
}
