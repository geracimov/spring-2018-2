package ru.geracimov.otus.spring.hw20springsecurityauth.services;

import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Book;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Genre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenreService {
    Optional<Genre> getGenreById(UUID uuid);

    List<Genre> getGenresByBook(Book book);

    List<Genre> getAllGenres();

    Genre addGenre(String name);

    boolean delete(UUID id);

    boolean delete(Genre genre);

    void save(Genre genre);
}