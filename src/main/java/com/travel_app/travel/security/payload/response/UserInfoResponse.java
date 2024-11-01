package com.travel_app.travel.security.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String DOB;
	private String address;
	private String urlAvt;
	private List<String> roles;
	private String jwt;
}
