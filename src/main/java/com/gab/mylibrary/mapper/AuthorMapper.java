package com.gab.mylibrary.mapper;

import com.gab.mylibrary.dto.AuthorDTO;
import com.gab.mylibrary.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    public AuthorDTO toDTO (Author author){
        if (author == null){
            return null;
        }
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getBirthDate(),
                author.getDeathDate()
        );
    }
    public Author toEntity(AuthorDTO authorDTO){
        if (authorDTO == null){
            return null;
        }
        Author author = new Author();
        author.setId(authorDTO.id());
        author.setName(authorDTO.name());
        author.setBirthDate(authorDTO.birthDate());
        author.setDeathDate(authorDTO.deathDate());
        return author;
    }

    public List<AuthorDTO> toDTOList(List<Author> authors){
        return authors.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
