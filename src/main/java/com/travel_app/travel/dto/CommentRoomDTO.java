package com.travel_app.travel.dto;

import com.travel_app.travel.entity.RoomEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;

@Data
public class CommentRoomDTO {
    private Long id;
    private int stars;
    private String content;
    private List<String> images;
    private Long roomId;
    private Long userId;
}
