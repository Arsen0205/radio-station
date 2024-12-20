package com.example.radio_station.controller;

import com.example.radio_station.dto.request.ChangeUserRoleRequest;
import com.example.radio_station.dto.request.DeleteSongDtoRequest;
import com.example.radio_station.dto.response.ChangeUserDtoResponse;
import com.example.radio_station.dto.response.DeleteSongDtoResponse;
import com.example.radio_station.models.enums.Role;
import com.example.radio_station.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @GetMapping("/ban")
    public String ban(){return "ban";}

    @PostMapping("/ban")
    public ChangeUserDtoResponse banUser(@Valid @RequestBody ChangeUserRoleRequest request){
        return adminService.banUser(request);
    }

    @GetMapping("/unBan")
    public String unBan(){return "unBan";}

    @PostMapping("/unBan")
    public ChangeUserDtoResponse unBanUser(@Valid @RequestBody ChangeUserRoleRequest request){
        return adminService.unBanUser(request);
    }

    @PostMapping("/change")
    public ChangeUserDtoResponse changeUserRole(@Valid @RequestBody ChangeUserRoleRequest request){
        return adminService.changeUserRole(request);
    }

    @PostMapping("/delete")
    public DeleteSongDtoResponse deleteSong(@Valid @RequestBody DeleteSongDtoRequest request){
        return adminService.deleteSong(request);
    }
}
