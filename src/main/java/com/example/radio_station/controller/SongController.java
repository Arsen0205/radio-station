package com.example.radio_station.controller;

import com.example.radio_station.dto.request.UploadSongRequest;
import com.example.radio_station.models.Song;
import com.example.radio_station.service.SongService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class SongController {
    private SongService songService;

    @GetMapping("/song")
    public String song(){return "song";}

    @PostMapping("/song")
    public Song uploadSong(@Valid @RequestParam String title,
                           @RequestParam String artist,
                           @RequestParam MultipartFile file,
                           @RequestParam String userLogin) throws IOException{
        UploadSongRequest request = new UploadSongRequest();
        request.setTitle(title);
        request.setArtist(artist);
        request.setFile(file);

        return songService.uploadSong(request, userLogin);
    }
}
