package bootcamp.hibernate_practical.controller;

import bootcamp.hibernate_practical.dto.BookResponse;
import bootcamp.hibernate_practical.dto.CreateBookRequest;
import bootcamp.hibernate_practical.dto.UpdateBookStatus;
import bootcamp.hibernate_practical.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponse createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        return bookService.createBook(createBookRequest);
    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        // TODO
        return bookService.getAllBooks();
    }

    @GetMapping("/books_number")
    public long getAllBooksNumber() {
        return bookService.countAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        // TODO
        return bookService.getBookById(id);
    }

    @PatchMapping("/{id}/status")
    public BookResponse updateBook(@PathVariable Long id, @Valid @RequestBody UpdateBookStatus updateBookStatus) {
        // TODO
        return bookService.updateBookStatus(id, updateBookStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        // TODO
        bookService.deleteBook(id);
    }

    @GetMapping("/author/{author}")
    public List<BookResponse> getBooksByAuthor(@PathVariable String author) {
        // TODO
        return bookService.findByAuthor(author);
    }

    @GetMapping("/available")
    public List<BookResponse> getAvailableBooks() {
        // TODO
        return bookService.findAvailableBooks();
    }

    @GetMapping("/after_year/{year}")
    public List<BookResponse> getBooksAfterYear(@PathVariable Integer year) {
        return bookService.findPublishedBooksAfterYear(year);
    }
}
