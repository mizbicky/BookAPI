package pl.coderslab.book;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class MockBookService implements BookService {

    private static Long nextId = 4L;

    private List<Book> books;


    public MockBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public Optional<Book> get(Long id) {
        return books.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public void add(Book book) {
        book.setId(nextId);
        books.add(book);
        nextId++;
    }

    @Override
    public void delete(Long id) {
        books.remove(get(id).get());
    }

    @Override
    public void update(Book book) {
        Book bookId = books.stream().filter(e -> e.getId().equals(book.getId())).findFirst().orElseThrow(() ->
        new RuntimeException("nie znaleziono ksiazki"));
        Long id = bookId.getId();
        books.remove(get(id).get());
        book.setId(id);
        books.add(book);
//        books.stream().sorted(Comparator.comparing(Book::getId));

    }
    }

