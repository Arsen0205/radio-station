package com.example.radio_station.dto.request;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadSongRequest {
    private String title;
    private String artist;
    private MultipartFile file;
}
