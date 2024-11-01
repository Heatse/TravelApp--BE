package com.travel_app.travel.service;

import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.User;

public interface IUserService {

    UserDTO save(UserDTO userDTO);
    UserDTO upLoadAvt(Long userId, String url);
    UserDTO updateUser(Long userId, UserDTO userDTO);

    void changePassword (Long userId, String Password);

    Boolean deleteUser(Long userId);

    UserDTO findUserById(Long userId);
    UserDTO findUserByEmail(String email);
}
