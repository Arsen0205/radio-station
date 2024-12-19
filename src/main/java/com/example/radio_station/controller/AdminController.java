package com.example.radio_station.controller;

import com.example.radio_station.dto.request.ChangeUserRoleRequest;
import com.example.radio_station.dto.response.ChangeUserDtoResponse;
import com.example.radio_station.models.enums.Role;
import com.example.radio_station.service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;

    @GetMapping("/ban")
    public String ban(){return "ban";}

    @PostMapping("/change")
    public ChangeUserDtoResponse userBan(@Valid @RequestBody ChangeUserRoleRequest request){
        return adminService.changeUserRole(request);
    }

}
