package com.example.radio_station.controller;

import com.example.radio_station.dto.request.ChangeUserRoleRequest;
import com.example.radio_station.dto.request.DeleteSongDtoRequest;
import com.example.radio_station.dto.response.ChangeUserDtoResponse;
import com.example.radio_station.dto.response.DeleteSongDtoResponse;
import com.example.radio_station.models.enums.Role;
import com.example.radio_station.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private AdminService adminService;

    @GetMapping("/ban")
    public String ban(){return "ban";}

    @PostMapping("/ban")
    public ChangeUserDtoResponse banUser(@Valid @RequestBody ChangeUserRoleRequest request){
        return adminService.banUser(request);
    }

    @GetMapping("/admin")
    public String adminPanel(){
        return "admin";
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
}
