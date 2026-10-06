package ec.com.leodev.leoplay.domain.dto;

import ec.com.leodev.leoplay.domain.enums.Gender;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record MovieDto(
        Long id,

        @NotBlank(message = "El título es obligatorio")
        @Size(max = 150, message = "El título no puede superar 150 caracteres")
        String title,

        @NotNull(message = "La duración es obligatoria")
        @Positive(message = "La duración debe ser mayor que 0")
        Integer duration,

        @NotNull(message = "El género es obligatorio")
        Gender gender,

        @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual")
        LocalDate releaseDate,

        @DecimalMin(value = "0.0", message = "El rating no puede ser menor que 0")
        @DecimalMax(value = "5.0", message = "El rating no puede ser mayor que 5")
        BigDecimal rating
) { }
