package com.travel_app.travel.repository;

import com.travel_app.travel.entity.BookingRoom;
import com.travel_app.travel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom, Long> {
    Optional<List<BookingRoom>> findByUserId(Long id);

    @Query("SELECT br FROM BookingRoom br " +
            "WHERE br.room.id = :roomId " +
            "AND ((br.checkInDate BETWEEN :checkInDate AND :checkOutDate) " +
            "OR (br.checkOutDate BETWEEN :checkInDate AND :checkOutDate))")
    List<BookingRoom> findConflictingBookings(@Param("roomId") Long roomId,
                                              @Param("checkInDate") String checkInDate,
                                              @Param("checkOutDate") String checkOutDate);

//    @Query("SELECT br FROM BookingRoom br " +
//            "WHERE br.room.accommodationName LIKE %:keyword%")
//    List<BookingRoom> findByAccommodationNameContaining(@Param("keyword") String keyword);

    @Query("SELECT b FROM BookingRoom b WHERE b.user.id = :userId AND b.checkInDate = :currentDate")
    List<BookingRoom> findByUserIdAndCheckInDate(@Param("userId") Long userId, @Param("currentDate") String currentDate);

    @Query("SELECT b FROM BookingRoom b WHERE b.checkInDate = :currentDate")
    List<BookingRoom> findByCheckInDate(@Param("currentDate") String currentDate);

    @Query("SELECT b FROM BookingRoom b WHERE b.checkOutDate = :currentDate")
    List<BookingRoom> findByCheckOutDate(@Param("currentDate") String currentDate);
}
