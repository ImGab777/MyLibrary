package com.gab.mylibrary.model;

import com.gab.mylibrary.model.enuns.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    private LocalDate publicationYear;

    @Column(unique = true, nullable = false)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book(
            String title,
            Genre genre,
            String longDescription,
            String shortDescription,
            LocalDate publicationYear,
            String isbn,
            Author author) {
        this.title = title;
        this.genre = genre;
        this.longDescription = longDescription;
        this.shortDescription = shortDescription;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.author = author;
    }
}
