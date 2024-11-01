package com.travel_app.travel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String LastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String DOB;
    private String address;
    private String urlAvt;
    private Set<String> roles = new HashSet<>();
}
