package ru.geracimov.otus.spring.hw14librarymvc.services;

import ru.geracimov.otus.spring.hw14librarymvc.domain.Book;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Genre;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GenreService {
    Genre getGenreById(UUID uuid);

    Set<Genre> getGenresByBook(Book book);

    List<Genre> getAllGenres();

    Genre addGenre(String name);

    boolean delete(UUID id);

    boolean delete(Genre genre);

    void save(Genre genre);
}