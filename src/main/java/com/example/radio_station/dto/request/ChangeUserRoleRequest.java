package com.example.radio_station.dto.request;

import com.example.radio_station.models.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChangeUserRoleRequest {
    @NotNull(message = "Логин пользователя обязателен!")
    private String userLogin;
    @NotNull(message = "Роль пользователя обязательна")
    private Role newRole;
}
