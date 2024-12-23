package com.example.radio_station.controller;

import com.example.radio_station.dto.request.DeleteSongDtoRequest;
import com.example.radio_station.dto.request.UploadSongRequest;
import com.example.radio_station.models.Song;
import com.example.radio_station.models.User;
import com.example.radio_station.repository.SongRepository;
import com.example.radio_station.repository.UserRepository;
import com.example.radio_station.service.AdminService;
import com.example.radio_station.service.SongService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class SongController {
    private SongService songService;
    private SongRepository songRepository;
    private UserRepository userRepository;
    private AdminService adminService;

    @GetMapping("/song")
    public String song(Model model, Principal principal){
        String username = principal.getName();
        User user = userRepository.findByUserLogin(username).orElseThrow(() -> new IllegalArgumentException("Пользователя нет"));

        List<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("user", user);
        return "song";
    }

    @PostMapping("/song/addSong")
    public String uploadSong(@Valid @RequestParam String title,
                             @RequestParam String artist,
                             @RequestParam MultipartFile file,
                             Principal principal) throws IOException{
        UploadSongRequest request = new UploadSongRequest();
        request.setTitle(title);
        request.setArtist(artist);
        request.setFile(file);

        songService.uploadSong(request, principal);

        return "redirect:/song";
    }

    @PostMapping("/song/delete/{id}")
    public String deleteSong(@PathVariable Long id){
        adminService.deleteSong(id);

        return "redirect:/song";
    }
}
