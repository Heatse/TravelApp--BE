package com.travel_app.travel.service;

import com.travel_app.travel.dto.CommentLocationDTO;
import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.entity.CommentLocation;
import com.travel_app.travel.entity.Location;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ICommentLocationService {
    CommentLocationDTO addNewCommentLocation(CommentLocationDTO commentLocationDTO);


    List<CommentLocation> findByLocationId(Long locationId);

    void deleteComment(Long commentId);

    Long countByLocationId(Long locationId);
}
