package ec.com.leodev.leoplay.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovieDto (
        Long id,
        String title,
        Integer duration,
        String gender,
        LocalDate releaseDate,
        BigDecimal rating
) { }
