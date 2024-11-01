package com.travel_app.travel.dto;

import com.travel_app.travel.entity.Location;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class CommentLocationDTO {
    private Long id;
    private int stars;
    private String content;
    private List<String> images;
    private Long locationId;
    private Long userId;
}
