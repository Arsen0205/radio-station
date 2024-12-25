package com.example.radio_station.controller;

import com.example.radio_station.dto.request.ChangeUserRoleRequest;
import com.example.radio_station.dto.request.DeleteSongDtoRequest;
import com.example.radio_station.dto.response.ChangeUserDtoResponse;
import com.example.radio_station.dto.response.DeleteSongDtoResponse;
import com.example.radio_station.models.User;
import com.example.radio_station.models.enums.Role;
import com.example.radio_station.service.AdminService;
import com.example.radio_station.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private AdminService adminService;
    private UserService userService;

    @GetMapping("/admin")
    public String admin(Model model){
        List<User> users = userService.list();
        users.forEach(user -> {
            user.setRolesString(user.getRoles().stream()
                    .map(Role::getAuthority)
                    .collect(Collectors.joining(", ")));
        });
        model.addAttribute("users", users);
        return "admin";
    }

    @PostMapping("/admin/ban/{userLogin}")
    public String banUser(@PathVariable("userLogin") String userLogin){
        adminService.banUser(userLogin);
        return "redirect:/admin";
    }


    @PostMapping("/admin/unBan/{userLogin}")
    public String unBanUser(@PathVariable String userLogin){
        adminService.unBanUser(userLogin);
        return "redirect:/admin";
    }

    @GetMapping("/admin/change/{user}")
    public String userChange(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "change";
    }

    @PostMapping("/admin/change")
    public String changeUserRole(@ModelAttribute ChangeUserRoleRequest request){
        adminService.changeUserRole(request);

        return "redirect:/admin";
    }
}
