package com.travel_app.travel.security.payload.request;

import java.util.Set;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
//    @NotBlank
//    @Size(min = 3, max = 20)
//    private String username;

    private String firstName;

    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> listRole;



    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
//
    private String phoneNumber;

    private String address;

    private String DOB;

    private String urlAvt;

    public SignupRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


}
