package ru.geracimov.otus.spring.hw20springsecurityauth.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Author;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Book;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Genre;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Review;
import ru.geracimov.otus.spring.hw20springsecurityauth.exception.NotFoundException;
import ru.geracimov.otus.spring.hw20springsecurityauth.repository.BookRepository;
import ru.geracimov.otus.spring.hw20springsecurityauth.services.AuthorService;
import ru.geracimov.otus.spring.hw20springsecurityauth.services.BookService;
import ru.geracimov.otus.spring.hw20springsecurityauth.services.GenreService;
import ru.geracimov.otus.spring.hw20springsecurityauth.services.ReviewService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final ReviewService reviewService;
    private final AuthorService authorService;

    public BookServiceImpl(final BookRepository bookRepository,
                           final GenreService genreService,
                           final ReviewService reviewService,
                           final AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
        this.reviewService = reviewService;
        this.authorService = authorService;
    }

    public Optional<Book> getBookById(UUID uuid) {
        return this.bookRepository.findById(uuid);
    }

    public List<Book> getAllBooks() {
        return this.bookRepository.findAllBy();
    }

    public List<Book> getBooksByAuthor(Author author) {
        return this.bookRepository.findBooksByAuthors(author);
    }

    @Override
    public List<Book> getBooksByAuthor_Id(UUID authorId) {
        return bookRepository.findBooksAndGenresByAuthors(authorService.getAuthorById(authorId).orElseThrow(NotFoundException::new));
    }

    public Book addBook(String name,
                        int year,
                        int pageCount,
                        String isbn) {
        try {
            Book book = new Book(name, year, pageCount, isbn);
            return this.bookRepository.save(book);
        } catch (Exception var6) {
            log.error("Error adding book", var6);
            return null;
        }
    }

    public boolean delete(UUID id) {
        try {
            this.bookRepository.deleteById(id);
            return true;
        } catch (Exception var3) {
            log.error("Error adding book", var3);
            return false;
        }
    }

    public boolean delete(Book book) {
        return this.delete(book.getId());
    }

    public void save(Book book) {
        this.bookRepository.save(book);
    }

    public void addGenreToBook(UUID genreId,
                               UUID bookId) {
        Genre genre = this.genreService.getGenreById(genreId).orElseThrow(NotFoundException::new);
        Book book = this.bookRepository.findById(bookId).orElseThrow(NotFoundException::new);
        this.addGenreToBook(genre, book);
    }

    public void addGenreToBook(Genre genre,
                               Book book) {
        book.addGenre(genre);
        this.bookRepository.save(book);
    }

    public void addAuthorToBook(UUID authorId,
                                UUID bookId) {
        Author author = this.authorService.getAuthorById(authorId).orElseThrow(NotFoundException::new);
        Book book = this.bookRepository.findById(bookId).orElseThrow(NotFoundException::new);
        this.addAuthorToBook(author, book);
    }

    public void addAuthorToBook(Author author,
                                Book book) {
        book.addAuthor(author);
        this.bookRepository.save(book);
    }

    public void addReviewToBook(String reviewerName,
                                String text,
                                UUID bookId) {
        Review review = new Review(reviewerName, LocalDateTime.now(), text);
        this.reviewService.save(review);
        Book book = this.getBookById(bookId).orElseThrow(NotFoundException::new);
        this.addReviewToBook(review, book);
    }

    public void addReviewToBook(Review review,
                                Book book) {
        book.addReview(review);
        this.bookRepository.save(book);
    }

    public void delReviewFromBook(UUID reviewUuid,
                                  UUID bookUuid) {
        Review review = this.reviewService.getReviewById(reviewUuid);
        Book book = this.bookRepository.findById(bookUuid).orElseThrow(NotFoundException::new);
        book.delReview(review);
        this.bookRepository.save(book);
    }

    public List<Book> getAllContainsAndNotContainig(String nameContains,
                                                    String nameNotContaining) {
        return this.bookRepository.findAllByNameContainsAndNameIsNotContaining(nameContains, nameNotContaining);
    }
}
