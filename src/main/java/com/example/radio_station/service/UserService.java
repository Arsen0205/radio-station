package com.example.radio_station.service;

import com.example.radio_station.dto.request.LoginDtoRequest;
import com.example.radio_station.dto.request.RegisterUserDtoRequest;
import com.example.radio_station.dto.response.LoginDtoResponse;
import com.example.radio_station.dto.response.RegisterUserDtoResponse;
import com.example.radio_station.models.User;
import com.example.radio_station.models.enums.Role;
import com.example.radio_station.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserDtoResponse registerUser(RegisterUserDtoRequest request){
        if(userRepository.findByUserLogin(request.getUserLogin()).isPresent()){
            throw new RuntimeException("Данный логин уже существует!");
        }

        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUserLogin(request.getUserLogin());
        user.getRoles().add(Role.LISTENER);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        userRepository.save(user);
        return new RegisterUserDtoResponse("Пользователь успешно зарегистрирован!");
    }

    public LoginDtoResponse loginUser(LoginDtoRequest request){
        User user = userRepository.findByUserLogin(request.getUserLogin())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с таким логином не найден!"));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("Неверный пароль!");
        }
        if(!user.isActive()){
            throw new IllegalArgumentException("Пользователь заблокирован!");
        }
        return new LoginDtoResponse("Вход выполнен успешно");
    }
}
