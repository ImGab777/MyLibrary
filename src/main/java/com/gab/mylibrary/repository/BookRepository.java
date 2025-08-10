package com.gab.mylibrary.repository;

import com.gab.mylibrary.model.Book;
import com.gab.mylibrary.model.enuns.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByGenre(Genre genre);
    List<Book> findByAuthorId(Long authorId );
    List<Book> findByAuthorNameContainingIgnoreCase(String authorName);

}
