package com.travel_app.travel.repository;

import com.travel_app.travel.entity.CommentLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentLocationRepository extends JpaRepository<CommentLocation, Long> {
    List<CommentLocation> findByLocationId(Long locationId);

    @Query(value = "SELECT COUNT(*) FROM comment_location WHERE location_id = :locationId", nativeQuery = true)
    Long countByLocationId(@Param("locationId") Long locationId);
}
