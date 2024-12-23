package com.example.radio_station.controller;

import com.example.radio_station.dto.request.LoginDtoRequest;
import com.example.radio_station.dto.request.RegisterUserDtoRequest;
import com.example.radio_station.dto.response.LoginDtoResponse;
import com.example.radio_station.dto.response.RegisterUserDtoResponse;
import com.example.radio_station.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegisterUserDtoRequest request){
        userService.registerUser(request);
        System.out.println("Переданные данные: " + request);
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String register(){return "register";}

    @GetMapping("/login")
    public String login(){return "login";}

    @PostMapping("/login")
    public String loginUser(@Valid @RequestBody LoginDtoRequest request){
        userService.loginUser(request);
        return "/song";
    }
}
