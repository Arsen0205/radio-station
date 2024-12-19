package com.example.radio_station.controller;

import com.example.radio_station.dto.request.LoginDtoRequest;
import com.example.radio_station.dto.request.RegisterUserDtoRequest;
import com.example.radio_station.dto.response.LoginDtoResponse;
import com.example.radio_station.dto.response.RegisterUserDtoResponse;
import com.example.radio_station.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public RegisterUserDtoResponse registerUser(@Valid @RequestBody RegisterUserDtoRequest request){
        return userService.registerUser(request);
    }

    @GetMapping("/register")
    public String register(){return "register";}

    @GetMapping("/login")
    public String login(){return "login";}

    @PostMapping("/login")
    public LoginDtoResponse loginUser(@Valid @RequestBody LoginDtoRequest request){
        return userService.loginUser(request);
    }
}
