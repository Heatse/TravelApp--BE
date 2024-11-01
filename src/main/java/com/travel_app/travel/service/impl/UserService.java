package com.travel_app.travel.service.impl;

import com.travel_app.travel.converter.UserConverter;
import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.User;
import com.travel_app.travel.repository.UserRepository;
import com.travel_app.travel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
     private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO save(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            Optional<User> usertmp = userRepository.findByEmail(userDTO.getEmail());
            User user = usertmp.get();
            user = userConverter.toEntity(userDTO, user);
            user = userRepository.save(user);
            return userConverter.toDTO(user);
        } else {
            User user = userConverter.toEntity(userDTO);
            user = userRepository.save(user);
            return userConverter.toDTO(user);
        }

    }

    @Override
    public UserDTO upLoadAvt(Long userId, String url) {
        User user = userRepository.findById(userId)
               .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        user.setUrlAvt(url);
        user = userRepository.save(user);
        return userConverter.toDTO(user);

    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        user = userConverter.toEntity(userDTO,user);
        user = userRepository.save(user);
        return userConverter.toDTO(user);

    }

    @Override
    public void changePassword(Long userId, String Password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        user.setPassword(Password);
        user = userRepository.save(user);
    }

    public void resetPassword(Long userId, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }



    @Override
    public Boolean deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserDTO findUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return userConverter.toDTO(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        User userTmp = user.get();
        return userConverter.toDTO(userTmp);

    }
}
