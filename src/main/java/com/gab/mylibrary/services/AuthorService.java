package com.gab.mylibrary.services;

import com.gab.mylibrary.model.Author;
import com.gab.mylibrary.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    //buscar todos
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    //busca o author por id
    @Transactional(readOnly = true)
    public Optional<Author> getAuthorById(Long id){
        return authorRepository.findById(id);
    }

    //criar um novo autor
    @Transactional
    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    @Transactional
    public void deleteAuthor(Long id){
        if(!authorRepository.existsById(id)){
            throw new EntityNotFoundException("Autor não encontrado com o id: " + id);
        }
        authorRepository.deleteById(id);
    }

    @Transactional
    public Author updateAuthor(Long id, Author authorDetails){
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author não encontrado com o id: " + id));

        existingAuthor.setName(authorDetails.getName());
        existingAuthor.setBirthDate(authorDetails.getBirthDate());
        existingAuthor.setDeathDate(authorDetails.getDeathDate());

        return authorRepository.save(existingAuthor);
    }

    @Transactional(readOnly = true)
    public List<Author> findByNameOrPseudonym(String name, String pseudonym){
        return findByNameOrPseudonym(name,pseudonym);
    }
}
