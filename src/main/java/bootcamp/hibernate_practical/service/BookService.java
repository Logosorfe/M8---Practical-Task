package bootcamp.hibernate_practical.service;

import bootcamp.hibernate_practical.dto.BookResponse;
import bootcamp.hibernate_practical.dto.CreateBookRequest;
import bootcamp.hibernate_practical.dto.UpdateBookStatus;
import bootcamp.hibernate_practical.entity.Book;
import bootcamp.hibernate_practical.exception.BookNotFoundException;
import bootcamp.hibernate_practical.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse createBook(CreateBookRequest request) {
        Book book = new Book(
                request.title(),
                request.author(),
                request.genre(),
                request.publicationYear(),
                true
        );
        return mapToResponse(bookRepository.save(book));
    }

    public List<BookResponse> getAllBooks() {
        // TODO:
        // Fetch all books from the repository
        // Convert each Book entity into BookResponse DTO
        // Return the list
        return bookRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public long countAllBooks() {
        return bookRepository.countAll();
    }

    public BookResponse getBookById(Long id) {
        // TODO
        return bookRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new BookNotFoundException(id));
        // Find the book by its ID
        // Throw RuntimeException if not found
        // Convert the entity to BookResponse
    }

    public BookResponse updateBookStatus(Long id, UpdateBookStatus request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setAvailable(request.isAvailable());
        return mapToResponse(bookRepository.save(book));
    }


    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(book);
    }

    public List<BookResponse> findByAuthor(String author) {
        // TODO
        return bookRepository.findByAuthor(author).stream().
                map(this::mapToResponse).
                toList();
    }

    public List<BookResponse> findAvailableBooks() {
        // TODO
        return bookRepository.findByAvailableTrue().stream().
                map(this::mapToResponse).
                toList();
    }

    public List<BookResponse> findPublishedBooksAfterYear(int year) {
        return bookRepository.findAfterYear(year).stream().
                map(this::mapToResponse).
                toList();
    }

    private BookResponse mapToResponse(Book book) {
        // TODO: map Book to BookResponse
        return new BookResponse(book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublicationYear(),
                book.isAvailable());
    }
}
