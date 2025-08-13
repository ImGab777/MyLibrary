package com.gab.mylibrary.mapper;

import com.gab.mylibrary.dto.BookDTO;
import com.gab.mylibrary.dto.BookInputDTO;
import com.gab.mylibrary.model.Author;
import com.gab.mylibrary.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;

    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }


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
                authorMapper.toDTO(book.getAuthor())
        );
    }

    public Book toEntity(BookInputDTO bookInputDTO){
        if (bookInputDTO == null){
            return null;
        }
        Book book = new Book();
        book.setTitle(bookInputDTO.title());
        book.setGenre(bookInputDTO.genre());
        book.setLongDescription(bookInputDTO.longDescription());
        book.setShortDescription(bookInputDTO.shortDescription());
        book.setPublicationYear(bookInputDTO.publicationYear());
        book.setIsbn(bookInputDTO.isbn());

        if(bookInputDTO.authorId() != null){
            Author author = new Author();
            author.setId(bookInputDTO.authorId());
            book.setAuthor(author);
        }

        return book;
    }

}
