package com.gab.mylibrary.services;

import com.gab.mylibrary.model.Author;
import com.gab.mylibrary.model.Book;
import com.gab.mylibrary.model.enuns.Genre;
import com.gab.mylibrary.repository.AuthorRepository;
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

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public Book createBook(Book book) {
        if (book.getAuthor() == null || book.getAuthor().getId() == null){
            throw new IllegalArgumentException("O autor e seu id não podem ser nulos ");
        }

        Long authorId = book.getAuthor().getId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author não encontrado com o id: " + authorId));
        book.setAuthor(author);
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
        // Atualiza todos os campos do livro existente
        existingBook.setTitle(bookDetails.getTitle());
        existingBook.setGenre(bookDetails.getGenre());
        existingBook.setLongDescription(bookDetails.getLongDescription());
        existingBook.setShortDescription(bookDetails.getShortDescription());
        existingBook.setPublicationYear(bookDetails.getPublicationYear());
        existingBook.setIsbn(bookDetails.getIsbn());

        // Se o autor foi alterado, busca o novo autor e o atualiza
        if (bookDetails.getAuthor() != null && bookDetails.getAuthor().getId() != null) {
            Author author = authorRepository.findById(bookDetails.getAuthor().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado com o id: " + bookDetails.getAuthor().getId()));
            existingBook.setAuthor(author);
        }

        return bookRepository.save(existingBook);
    }

    @Transactional
    public void deleteBook(long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Livro não encontrado com o id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
