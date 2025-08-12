package com.gab.mylibrary.controller;

import com.gab.mylibrary.dto.BookDTO;
import com.gab.mylibrary.mapper.BookMapper;
import com.gab.mylibrary.model.Book;
import com.gab.mylibrary.model.enuns.Genre;
import com.gab.mylibrary.repository.BookRepository;
import com.gab.mylibrary.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
        return bookService.getBookById(id)
                .map(bookMapper :: toDTO)
                .map(ResponseEntity ::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> books = bookService.getAllBooks().stream()
                .map(bookMapper ::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(bookMapper.toDTO(createdBook), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable  long id, @Valid @RequestBody BookDTO bookDTO){
        Book bookDetails = bookMapper.toEntity(bookDTO);
        try{
            Book updatedBook = bookService.updateBook(id, bookDetails);
            return ResponseEntity.ok(bookMapper.toDTO(updatedBook));
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        try{
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
          return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookDTO>> findByTitle(@PathVariable String title){
        List<BookDTO> books = bookService.findBookByTitle(title).stream()
                .map(bookMapper ::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping("/Genre/{Genre}")
    public ResponseEntity<List<BookDTO>> findByGenre (@PathVariable Genre genre){
        List<BookDTO> books = bookService.findByGenre(genre).stream()
                .map(bookMapper ::toDTO)
                .collect(Collectors.toList());
        return  ResponseEntity.ok(books);
    }

    @GetMapping("/author/id/{id}")
    public ResponseEntity<List<BookDTO>> findByAuthorId (@PathVariable Long id){
        List<BookDTO> books = bookService.findByAuthorId(id).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @GetMapping("/author/name/{authorName}")
    public ResponseEntity<List<BookDTO>> findByAuthorName(@PathVariable String authorName){
        List<BookDTO> books = bookService.findByAuthorName(authorName).stream()
                .map(bookMapper :: toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }


}
