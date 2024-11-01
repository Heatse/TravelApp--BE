package com.travel_app.travel;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class TravelApplication {
	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}
	@Bean
	public Cloudinary cloudinary(){
		Cloudinary c = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "df0wme1wh",
				"api_key", "364634374243923",
				"api_secret", "HoNe098kBGhAa17snQsCrecUNuE",
				"secure", true
		));
		return  c;
	}
}
