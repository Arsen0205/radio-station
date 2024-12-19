package com.example.radio_station.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Data
public class RegisterUserDtoRequest {
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно быть длиной от 2 до 50 символов")
    private String name;
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Фамилия должна быть длиной от 2 до 50 символов")
    private String surname;
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min = 2, max = 50, message = "Логин должен быть длиной от 2 до 50 символов")
    private String userLogin;
    @NotNull(message = "Данное поле не может быть пустым")
    @Size(min = 8, max = 50, message = "Пароль должен быть длиной от 8 до 50 символов")
    private String password;
}