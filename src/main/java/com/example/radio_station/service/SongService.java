package com.example.radio_station.service;

import com.example.radio_station.dto.request.UploadSongRequest;
import com.example.radio_station.models.Song;
import com.example.radio_station.models.User;
import com.example.radio_station.repository.SongRepository;
import com.example.radio_station.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SongService {
    //Работа с таблицей в базе данных
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    //Путь к папке, в которой сохраняются загружаемые файлы
    private final String uploadDir;

    public SongService(SongRepository songRepository, UserRepository userRepository, @Value("${file.upload-dir}") String uploadDir){
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.uploadDir =uploadDir;
    }

    public Song uploadSong(UploadSongRequest request, String userLogin) throws IOException {
        //Проверка пользователя
        User user = userRepository.findByUserLogin(userLogin).orElseThrow(() -> new RuntimeException("Пользователь не найден!"));
        if(!user.isPerformer()){
            throw new RuntimeException("Пользователь не имеет прав для загрузки песен!");
        }

        //Обработка файла
        MultipartFile file = request.getFile();
        //Извлекает имя загружаемого файла
        String fileName = file.getOriginalFilename();
        //Создает путь к названию файла
        Path path = Paths.get(uploadDir, fileName);

        //Запись файла на диск
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        //Сохранение файла в БД
        Song song = new Song();
        song.setTitle(request.getTitle());
        song.setArtist(request.getArtist());
        song.setFilePath(path.toString());
        song.setUser(user);

        return songRepository.save(song);
    }
}