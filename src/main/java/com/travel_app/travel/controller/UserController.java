package com.travel_app.travel.controller;

import com.travel_app.travel.dto.UserDTO;
import com.travel_app.travel.entity.User;
import com.travel_app.travel.repository.UserRepository;
import com.travel_app.travel.security.jwt.JwtUtils;
import com.travel_app.travel.security.payload.request.ChangePasswordRequest;
import com.travel_app.travel.security.payload.response.UserInfoResponse;
import com.travel_app.travel.security.services.PasswordGenerator;
import com.travel_app.travel.security.services.UserDetailsImpl;
import com.travel_app.travel.security.services.UserDetailsServiceImpl;
import com.travel_app.travel.service.IUserService;
import com.travel_app.travel.service.impl.CloudinaryService;
import com.travel_app.travel.service.impl.MailService;
import com.travel_app.travel.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    MailService mailService;

    @Autowired
    UserRepository userRepository;



    @PostMapping("/user/{userId}/uploadAvt")
    public ResponseEntity<?> uploadAvt (@PathVariable(value = "userId") Long userId, @RequestParam("image") MultipartFile file) throws IOException {
        Map data = this.cloudinaryService.upload(file);
        String url = (String) data.get("url");
        UserDTO userDTO = userService.upLoadAvt(userId, url);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUser (@PathVariable(value = "userId") Long userId) {
        UserDTO user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateUser (@PathVariable(value = "userId") Long userId, @RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUser(userId, userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping("/auth/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        Object userCurrent = userDetailsService.getCurrentUser();
        Long userId = null;
        if (userCurrent instanceof UserDetails) {
            userId = ((UserDetailsImpl) userCurrent).getId();
        } else if (userCurrent instanceof OAuth2User) {
            UserDTO userDTO = userService.findUserByEmail(((OAuth2User) userCurrent).getAttribute("email"));
            userId = userDTO.getId();
        } else if (userCurrent == null) {
            return new ResponseEntity<>("Người dùng không tồn tại11", HttpStatus.NOT_FOUND);
        }

        UserDTO currentUser = userService.findUserById(userId);

        // Xác thực mật khẩu cũ
        if (!passwordEncoder.matches(request.getCurrentPassword(), currentUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu cũ không đúng");
        }

        // Mã hóa mật khẩu mới
        String newPasswordEncoded = passwordEncoder.encode(request.getNewPassword());

        userService.changePassword(userId, newPasswordEncoded);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        UserDTO user = userService.findUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại");
        }

        String newPassword = PasswordGenerator.generateRandomPassword(8);
        userService.resetPassword(user.getId(), newPassword);
        mailService.sendNewPasswordEmail(user.getEmail(), newPassword);

        return ResponseEntity.ok("Mật khẩu mới đã được gửi tới email của bạn");
    }


    @DeleteMapping("/user/delete")
    public ResponseEntity<String> deleteUser(){
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

        Boolean check = userService.deleteUser(userId);
        if (check) {
            return new ResponseEntity<>("Xóa người dùng thành công", HttpStatus.OK);
        } else return new ResponseEntity<>("Lỗi khi xóa người dùng", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/user/findUserCurrent")
    public ResponseEntity<?> findUserCurrent () {
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
        UserDTO userDTO = userService.findUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @GetMapping("/signinGoogle")
    public RedirectView signinGG () {
        return new RedirectView("/oauth2/authorization/google");
    }



    @GetMapping("/loginSuccess")
    public ResponseEntity<?> loginSuccess(HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail(oauth2User.getAttribute("email"));
                userDTO.setUrlAvt(oauth2User.getAttribute("picture"));
                userDTO.setLastName(oauth2User.getAttribute("family_name"));
                userDTO.setFirstName(oauth2User.getAttribute("given_name"));
                userDTO = userService.save(userDTO);

                Optional<User> userOptional = userRepository.findByEmail(oauth2User.getAttribute("email"));
                User user = userOptional.get();

                // Tạo UserDetailsImpl
                UserDetailsImpl userDetails = new UserDetailsImpl(
                        user.getId(),
                        user.getEmail(),
                        null, // không có mật khẩu nếu sử dụng OAuth2
                        user.getFirstName(),
                        user.getLastName(),
                        null, // Không có số điện thoại
                        null, // Không có ngày sinh
                        null, // Không có địa chỉ
                        user.getUrlAvt(),
                        new ArrayList<>() // Không có vai trò
                );

                // Tạo JWT từ UserDetailsImpl
                String jwt = jwtUtils.generateTokenFromEmail(userDetails.getEmail());

                // Thêm JWT vào cookie trong response
                response.addHeader(HttpHeaders.SET_COOKIE, jwt);

                return ResponseEntity.ok()
                        .body(new UserInfoResponse(
                                userDetails.getId(),
                                userDetails.getFirstName(),
                                userDetails.getLastName(),
                                userDetails.getEmail(),
                                userDetails.getPhoneNumber(),
                                userDetails.getDOB(),
                                userDetails.getAddress(),
                                userDetails.getUrlAvt(),
                                userDetails.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()),
                                jwt));

            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid principal type");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    

}
