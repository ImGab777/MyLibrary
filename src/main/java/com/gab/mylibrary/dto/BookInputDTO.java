package com.gab.mylibrary.dto;

import com.gab.mylibrary.model.enuns.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookInputDTO(
        @NotBlank(message = "O nome não pode estar em branco.")
        String title,

        @NotNull(message = "O genero não pode estar em branco")
        Genre genre,

        String longDescription,

        String shortDescription,

        @NotNull
        LocalDate publicationYear,

        @NotNull
        String isbn,

        @NotNull(message = "O ID do autor não pode ser nulo")
        Long authorId
) {
}
