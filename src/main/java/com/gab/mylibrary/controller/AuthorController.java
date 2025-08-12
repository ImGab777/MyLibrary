package com.gab.mylibrary.controller;

import com.gab.mylibrary.dto.AuthorDTO;
import com.gab.mylibrary.mapper.AuthorMapper;
import com.gab.mylibrary.model.Author;
import com.gab.mylibrary.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService, AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO>getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id)
                .map(authorMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
        List<AuthorDTO> authors = authorService.getAllAuthors().stream()
                .map(authorMapper :: toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO){
        Author author = authorMapper.toEntity(authorDTO);
        Author createdAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(authorMapper.toDTO(createdAuthor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable long id, @Valid @RequestBody AuthorDTO authorDTO) {
        Author authorDetails = authorMapper.toEntity(authorDTO);
        try {
            Author updatedAuthor = authorService.updateAuthor(id, authorDetails);
            return ResponseEntity.ok(authorMapper.toDTO(updatedAuthor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
            try{
                authorService.deleteAuthor(id);
                return ResponseEntity.noContent().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
    }
}
