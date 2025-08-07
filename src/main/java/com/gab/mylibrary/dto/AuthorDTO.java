package com.gab.mylibrary.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AuthorDTO(
        Long id,

        @NotBlank(message = "O nome não pode estar em branco.")
        String name,

        @NotNull(message = "A data de não pode ser nula.")
        @Past
        LocalDate birthDate,

        @PastOrPresent(message = "A data de morte deve ser ou no passado ou no presente")
        LocalDate deathDate
        ) {
}
