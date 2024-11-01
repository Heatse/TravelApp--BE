package com.travel_app.travel.repository;

import com.travel_app.travel.entity.Location;
import com.travel_app.travel.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByName(String name);

    @Query(value = "SELECT * FROM location ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Location> findRandomLocations();

    @Query(value = "SELECT COALESCE(AVG(c.stars), 0) FROM comment_location c WHERE c.location_id = :locationId", nativeQuery = true)
    Double getAverageStars(@Param("locationId") Long locationId);

    @Query(value = "SELECT l.*, AVG(c.stars) AS avgStars " +
            "FROM location l " +
            "JOIN comment_location c ON l.id = c.location_id " +
            "GROUP BY l.id " +
            "ORDER BY avgStars DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Location> findTopLocationsByAvgStars();

    @Query(value = "SELECT * " +
            "FROM location " +
            "WHERE name LIKE CONCAT('%', :key, '%');", nativeQuery = true)
    List<Location> findLocationsByKey(@Param("key") String key);

    @Query("SELECT l FROM Location l  " +
            "WHERE l.name LIKE %:keyword%")
    List<Location> findByKeyName(@Param("keyword") String keyword);

}
