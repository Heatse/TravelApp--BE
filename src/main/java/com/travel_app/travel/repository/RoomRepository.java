package com.travel_app.travel.repository;

import com.travel_app.travel.entity.Location;
import com.travel_app.travel.entity.RoomEntity;
import com.travel_app.travel.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query(value = "SELECT * FROM room ORDER BY RAND() LIMIT 2", nativeQuery = true)
    List<RoomEntity> findRandomRooms();

    @Query(value = "SELECT COALESCE(AVG(c.stars), 0) FROM comment_room c WHERE c.room_id = :roomId", nativeQuery = true)
    Double getAverageStars(@Param("roomId") Long roomId);

    @Query(value = "SELECT r.*, AVG(c.stars) AS avgStars " +
            "FROM room r " +
            "JOIN comment_room c ON r.id = c.room_id " +
            "GROUP BY r.id " +
            "ORDER BY avgStars DESC " +
            "LIMIT 3", nativeQuery = true)
    List<RoomEntity> findTopRoomsByAvgStars();

//    @Query(value = "SELECT * " +
//            "FROM Room " +
//            "WHERE location LIKE CONCAT('%', :key, '%') " +
//            "   OR hotel LIKE CONCAT('%', :key, '%');", nativeQuery = true)
//    List<RoomEntity> findRoomsByKey(@Param("key") String key);

    @Query(value = "SELECT r FROM RoomEntity r " +
            "JOIN r.accommodation a " +
            "WHERE (:accommodationTypes IS NULL OR a.type IN :accommodationTypes) " +
            "AND (:accommodationName IS NULL OR a.name LIKE CONCAT('%', :accommodationName, '%')) " +
            "AND (:priceMin IS NULL OR r.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR r.price <= :priceMax) " +
            "AND (:type IS NULL OR r.type LIKE CONCAT('%', :type, '%')) ")
    List<RoomEntity> searchRooms(
            @Param("accommodationTypes") List<String> accommodationTypes,
            @Param("accommodationName") String accommodationName,
            @Param("priceMin") BigDecimal priceMin,
            @Param("priceMax") BigDecimal priceMax,
            @Param("type") String type
    );

//    @Query("SELECT r FROM RoomEntity r WHERE r.location LIKE %:keyword%")
//    List<RoomEntity> findByLocationContaining(@Param("keyword") String keyword);

    @Query(value = "SELECT r FROM RoomEntity r WHERE r.accommodation.provin.id = :provinId ORDER BY RAND() LIMIT 5")
    List<RoomEntity> findByAccommodationProvinId(@Param("provinId") Long provinId);

    @Query("SELECT r FROM RoomEntity r  " +
            "JOIN r.accommodation a " +
            "WHERE a.name LIKE %:keyword%")
    List<RoomEntity> findByKeyAccName(@Param("keyword") String keyword);
}
