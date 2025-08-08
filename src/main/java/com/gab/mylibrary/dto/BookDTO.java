package com.gab.mylibrary.dto;

import com.gab.mylibrary.model.Author;
import com.gab.mylibrary.model.enuns.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookDTO(
        Long id,

        @NotBlank(message = "O nome não pode estar em branco.")
        String title,

        @NotNull(message = "O genero deve não pode estar em branco")
        Genre genre,

        String longDescription,

        String shortDescription,

        @NotNull
        LocalDate publicationYear,

        @NotNull
        String isbn,
        @NotNull
        Author author
        ) {
}
