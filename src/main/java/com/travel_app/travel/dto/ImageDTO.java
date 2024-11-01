package com.travel_app.travel.dto;

import com.travel_app.travel.entity.RoomEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ImageDTO {
    private Long id;
    private String publicId;
    private String url;
    private String format;
    private int width;
    private int height;
    private RoomDTO room;
}
