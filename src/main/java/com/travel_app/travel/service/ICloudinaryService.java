package com.travel_app.travel.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface ICloudinaryService {
    public Map upload(MultipartFile file) throws IOException;
}
