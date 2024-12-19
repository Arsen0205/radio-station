package com.example.radio_station.service;


import com.example.radio_station.dto.request.ChangeUserRoleRequest;
import com.example.radio_station.dto.request.DeleteSongDtoRequest;
import com.example.radio_station.dto.response.ChangeUserDtoResponse;
import com.example.radio_station.dto.response.DeleteSongDtoResponse;
import com.example.radio_station.models.Song;
import com.example.radio_station.models.User;
import com.example.radio_station.repository.SongRepository;
import com.example.radio_station.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public ChangeUserDtoResponse banUser(ChangeUserRoleRequest request) {
        User user = userRepository.findByUserLogin(request.getUserLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователь с таким логином не найден"));

        if (!user.isActive()) {
            throw new IllegalArgumentException("Пользователь уже заблокирован");
        }

        user.setActive(false);
        userRepository.save(user);

        return new ChangeUserDtoResponse("Вы успешно поменяли роль");
    }

    public DeleteSongDtoResponse deleteSong(DeleteSongDtoRequest request) {
        Song song = songRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("Трек с таким id не найден"));
        songRepository.delete(song);

        return new DeleteSongDtoResponse("Вы успешно удалили трек");
    }

    public ChangeUserDtoResponse changeUserRole(ChangeUserRoleRequest request) {
        User user = userRepository.findByUserLogin(request.getUserLogin()).orElseThrow(() -> new IllegalArgumentException("Пользователь с таким логином не найден"));

        user.getRoles().clear();
        user.getRoles().add(request.getNewRole());

        userRepository.save(user);
        return new ChangeUserDtoResponse("Вы успешно поменяли роль!");
    }
}