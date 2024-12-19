package com.example.radio_station.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDtoRequest {
    @NotNull(message = "Данное поле не может быть пустым")
    private String userLogin;
    @NotNull(message = "Данное поле не может быть пустым")
    private String password;
}
