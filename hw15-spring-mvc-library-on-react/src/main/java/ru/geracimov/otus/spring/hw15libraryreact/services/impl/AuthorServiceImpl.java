package ru.geracimov.otus.spring.hw15libraryreact.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw15libraryreact.domain.Author;
import ru.geracimov.otus.spring.hw15libraryreact.repository.AuthorRepository;
import ru.geracimov.otus.spring.hw15libraryreact.services.AuthorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getAuthorById(UUID uuid) {
        return Optional.ofNullable(this.authorRepository.getOne(uuid));
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        return authors.isEmpty() ? new ArrayList<>() : authors;
    }

    @Override
    public List<Author> getAllAuthorsByBook(UUID bookId) {
        return authorRepository.findAuthorsByBook(bookId);
    }

    public Author addAuthor(String name,
                            LocalDate birth) {
        try {
            Author author = new Author(name, birth);
            this.authorRepository.save(author);
            return author;
        } catch (Exception var4) {
            log.error("Error adding author", var4);
            return null;
        }
    }

    public boolean delete(UUID id) {
        try {
            this.authorRepository.deleteById(id);
            return true;
        } catch (Exception var3) {
            log.error("Error deleting author - " + id, var3);
            return false;
        }
    }

    public boolean delete(Author author) {
        return this.delete(author.getId());
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

}
