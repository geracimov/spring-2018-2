package ru.geracimov.otus.spring.hw22springsecurityacl.services;


import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Book;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Genre;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    Optional<Book> getBookById(UUID uuid);

    boolean delete(UUID id);

    boolean delete(Book book);

    void save(Book book);

    Book addBook(String name,
                 int year,
                 int pageCount,
                 String isbn);

    List<Book> getAllBooks();

    List<Book> getBooksByAuthor(Author author);

    List<Book> getBooksByAuthor_Id(UUID authorId);

    void addGenreToBook(UUID genreId,
                        UUID bookId);

    void addGenreToBook(Genre genre,
                        Book book);

    void addAuthorToBook(UUID authorId,
                         UUID bookId);

    void addAuthorToBook(Author author,
                         Book book);

    void addReviewToBook(String reviewerName,
                         String text,
                         UUID bookId);

    void addReviewToBook(Review review,
                         Book book);

    void delReviewFromBook(UUID reviewUuid,
                           UUID bookUuid);

    List<Book> getAllContainsAndNotContainig(String contains,
                                             String notContaining);
}
