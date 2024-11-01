package com.travel_app.travel.repository;
import com.travel_app.travel.entity.CommentRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRoomRepository extends JpaRepository<CommentRoom, Long>{

    List<CommentRoom> findByRoomId(Long roomId);

    Long findByUserId(long userId);

    @Query(value = "SELECT COUNT(*) FROM comment_room WHERE room_id = :room_id", nativeQuery = true)
    Long countByRoomId(@Param("room_id") Long room_id);



}
