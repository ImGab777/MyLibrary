package com.gab.mylibrary.mapper;

import com.gab.mylibrary.dto.BookDTO;
import com.gab.mylibrary.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getGenre(),
                book.getLongDescription(),
                book.getShortDescription(),
                book.getPublicationYear(),
                book.getIsbn(),
                book.getAuthor()
        );
    }

    public Book toEntity(BookDTO bookDTO){
        if (bookDTO == null){
            return null;
        }
        Book book = new Book();
        book.setId(bookDTO.id());
        book.setTitle(bookDTO.title());
        book.setGenre(bookDTO.genre());
        book.setLongDescription(bookDTO.longDescription());
        book.setShortDescription(bookDTO.shortDescription());
        book.setPublicationYear(bookDTO.publicationYear());
        book.setIsbn(bookDTO.isbn());
        book.setAuthor(bookDTO.author());
        return book;
    }

}
