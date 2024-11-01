package com.travel_app.travel.converter;

import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setDOB(user.getDOB());
        userDTO.setAddress(user.getAddress());
        userDTO.setUrlAvt(user.getUrlAvt());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDOB(userDTO.getDOB());
        user.setAddress(userDTO.getAddress());
        user.setUrlAvt(userDTO.getUrlAvt());
        return user;
    }

    public static User toEntity(UserDTO userDTO, User user) {
        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (userDTO.getDOB() != null) {
            user.setDOB(userDTO.getDOB());
        }
        if (userDTO.getAddress() != null) {
            user.setAddress(userDTO.getAddress());
        }
        if (userDTO.getUrlAvt() != null) {
            user.setUrlAvt(userDTO.getUrlAvt());
        }
        return user;
    }
}
