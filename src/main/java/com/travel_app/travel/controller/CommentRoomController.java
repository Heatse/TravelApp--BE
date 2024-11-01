package com.travel_app.travel.controller;
import com.travel_app.travel.dto.CommentLocationDTO;
import com.travel_app.travel.dto.CommentRoomDTO;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.CommentRoom;
import com.travel_app.travel.repository.CommentRoomRepository;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.security.services.UserDetailsServiceImpl;
import com.travel_app.travel.service.ICloudinaryService;
import com.travel_app.travel.service.ICommentRoomService;
import com.travel_app.travel.service.IUserService;
import com.travel_app.travel.service.impl.CommentRoomService;
import com.travel_app.travel.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CommentRoomController {

    @Autowired
    CommentRoomService commentRoomService;


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    IUserService userService;

    @Autowired
    ICloudinaryService cloudinaryService;

    @Autowired
    CommentRoomRepository commentRoomRepository;


    @PostMapping("/room/{roomId}/comment/create")
    public ResponseEntity<?> createCommentRoom (@PathVariable(value = "roomId") Long roomId,
                                                    @RequestParam("stars") int stars,
                                                    @RequestParam("content") String content,
                                                    @RequestParam(value = "images", required = false) MultipartFile[] files) throws IOException {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }

        CommentRoomDTO commentRoomDTO = new CommentRoomDTO();
        commentRoomDTO.setStars(stars);
        commentRoomDTO.setContent(content);
        commentRoomDTO.setRoomId(roomId);
        if (files != null && files.length > 0) {
            List<String> imgs = new ArrayList<>();
            for (MultipartFile file : files) {
                Map<String, String> data = this.cloudinaryService.upload(file);
                imgs.add(data.get("url"));
            }
            commentRoomDTO.setImages(imgs);
        } else {
            commentRoomDTO.setImages(new ArrayList<>());
        }
        commentRoomDTO.setUserId(userId);

        CommentRoom commentRoom = commentRoomService.addNewCommentRoom(commentRoomDTO);

        return new ResponseEntity<>(commentRoom, HttpStatus.CREATED);
    }

    @GetMapping("/room/{roomId}/comment/findAll")
    public ResponseEntity<?> findAllCommnet(@PathVariable("roomId") Long roomId) {
        List<CommentRoom> commentRooms = commentRoomService.getCommentByRoomId(roomId);
        return new ResponseEntity<>(commentRooms, HttpStatus.OK);
    }

    @DeleteMapping("/room/comment/delete/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "commentId") Long commentId) {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại", HttpStatus.NOT_FOUND);
        }
        Optional<CommentRoom> commentRoom = commentRoomRepository.findById(commentId);
        Long commentUserId = commentRoom.get().getUser().getId();
        if (commentUserId == userId) {
            commentRoomService.deleteComment(commentId);
            return new ResponseEntity<>("xoa comment thanh cong", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("co phai qua m dau m xoa", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/room/{roomId}/totalComments")
    public ResponseEntity<?> countByLocationId(@PathVariable("roomId") Long roomId) {
        Long count = commentRoomService.countByRoomId(roomId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
