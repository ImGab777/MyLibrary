package com.gab.mylibrary.services;

import com.gab.mylibrary.model.Author;
import com.gab.mylibrary.model.Book;
import com.gab.mylibrary.model.enuns.Genre;
import com.gab.mylibrary.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Transactional(readOnly = true)
    public List<Book> findByGenre(Genre genre) {
        return bookRepository.findByGenre(genre);
    }

    @Transactional(readOnly = true)
    public List<Book> findByAuthorId(Long id) {
        return bookRepository.findByAuthorId(id);
    }

    @Transactional(readOnly = true)
    public List<Book> findByAuthorName(String authorName) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
    }

    @Transactional
    public Book updateBook(long id, Book bookDetails) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado com o id: " + id));

        existingBook.setTitle(bookDetails.getTitle());
        return bookRepository.save(bookDetails);
    }

    @Transactional
    public void deleteBook(long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Livro não encontrado com o id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
