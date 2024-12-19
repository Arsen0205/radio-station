package com.example.radio_station.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteSongDtoRequest {
    @NotNull(message = "Данное поле должно быть заполнено!")
    private Long id;
}
