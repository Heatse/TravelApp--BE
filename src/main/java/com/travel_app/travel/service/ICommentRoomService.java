package com.travel_app.travel.service;

import com.travel_app.travel.dto.CommentRoomDTO;
import com.travel_app.travel.entity.CommentRoom;

import java.util.List;

public interface ICommentRoomService {
    CommentRoom addNewCommentRoom(CommentRoomDTO commentRoomDTO);

    List<CommentRoom> getCommentByRoomId(Long roomId);

    void deleteComment(Long commentId);

    Long countByRoomId(Long roomId);
}

