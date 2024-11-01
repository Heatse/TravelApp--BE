package com.travel_app.travel.service.impl;

import com.travel_app.travel.converter.CommentLocationConverter;
import com.travel_app.travel.converter.LocationConverter;
import com.travel_app.travel.dto.CommentLocationDTO;
import com.travel_app.travel.dto.LocationDTO;
import com.travel_app.travel.entity.CommentLocation;
import com.travel_app.travel.entity.Location;
import com.travel_app.travel.repository.CommentLocationRepository;
import com.travel_app.travel.service.ICommentLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentLocationService implements ICommentLocationService {

    @Autowired
    CommentLocationRepository commentLocationRepository;

    @Autowired
    CommentLocationConverter commentLocationConverter;

    @Autowired
    LocationConverter locationConverter;

    @Override
    public CommentLocationDTO addNewCommentLocation(CommentLocationDTO commentLocationDTO) {
        CommentLocation commentLocation = commentLocationConverter.toEntity(commentLocationDTO);
        commentLocation.setCommentDate(new Date());
        commentLocation = commentLocationRepository.save(commentLocation);
        return commentLocationConverter.toDTO(commentLocation);
    }

    @Override
    public List<CommentLocation> findByLocationId(Long locationId) {
        return commentLocationRepository.findByLocationId(locationId);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentLocationRepository.deleteById(commentId);
    }

    @Override
    public Long countByLocationId(Long locationId) {
        return commentLocationRepository.countByLocationId(locationId);
    }

}
