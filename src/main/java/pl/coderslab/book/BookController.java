package pl.coderslab.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @RequestMapping("")
    public List<Book> list(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.bookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "id not found"
            );
        });
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.add(book);
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @PutMapping("")
    public void updateBook(@RequestBody Book book){
       bookService.update(book);

    }


}



