package ru.geracimov.otus.spring.hw25springintegration.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AUTHOR")
@ToString(exclude = "books")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotBlank
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "BIRTH")
    private LocalDate birth;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books;

    public Author(String name,
                  LocalDate birth) {
        this.name = name;
        this.birth = birth;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }
}
