# Practical Task: Library Book Tracker API

Starter project for Data Architecture practical task: Build a REST API for managing books in a library using Spring Boot, Spring Data JPA and an H2 database.

## Task
Complete all of the 'TODO' portions of the code. The finished application should compile and all of the controller endpoints should be functional.

## Behavioural Requirements
- Books can be created
- All books can be retrieved
- A single book can be retrieved by ID
- Books can be deleted
- Books can be filtered and searched by their fields.
- Controller delegates to Service, which delegates to Repository
- DTOs are used for requests and responses

## Stretch goals
- Request validation
- Custom exception handling
- Searching by partial title
- Add 'borrow' and 'return' endpoints along with the new 'borrowedStatus' field to the Book entity

## Teacher's comments
``` text
controller/BookController.java, service/BookService.java: all required endpoints are present and
correctlydelegate Controller -> Service -> Repository — createBook, getAllBooks, getBookById, deleteBook,
getBooksByAuthor, getAvailableBooks, getBooksAfterYear, plus a books_number total-count endpoint and a
PATCH /{id}/status endpoint for toggling availability. Verified live: all of the above work as coded.
service/BookService.getBookById and deleteBook: both correctly use
bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)) before acting — no
silent-success bug. Verified live: GET /api/books/999 and DELETE /api/books/999 both correctly return 404
with a clear message ("Book with id 999 not found"), and a genuine delete of an existing book correctly
removes it (subsequent GET on that id returns 404).
dto/CreateBookRequest.java: clean @NotBlank on title/author/genre and @Min(867) on publicationYear,
correctly wired via @Valid on the controller's @RequestBody. Verified live: POSTing blank/zero fields
correctly returns 400.
repository/BookRepository.java: findAfterYear uses a hand-written @Query with a genuine > comparison
(SELECT b FROM Book b WHERE b.publicationYear > :year), not just an exact-match lookup — this correctly
fulfils the "published after a given year" bonus. Verified live: after adding a 1965 and a 1984 book,
/api/books/after_year/1970 correctly returned only the 1984 book.
repository/BookRepository.countAll (SELECT COUNT(b) FROM Book b) + controller /api/books/books_number:
a genuine total-book-count query, not a filtered count — correctly satisfies the "total number of books"
bonus. Verified live: returned 2 after two books were created.
dto/UpdateBookStatus.java: a deliberate, well-reasoned design choice to only expose the availability field
for update, with an explanatory comment ("because by logic you can only change book's availability, I
removed other fields") — @NotNull is correctly enforced and verified live (a null isAvailable value
correctly returns 400).
Minor — leftover "// TODO" comments remain on several already-fully-implemented methods (getAllBooks,
getBookById, updateBook, deleteBook, findByAuthor, findAvailableBooks, mapToResponse in both the
controller and service) — purely cosmetic clutter left over from the starter template, with no functional
impact, but worth cleaning up for readability.
Minor — exception/GlobalExceptionHandler.java only has a handler for BookNotFoundException; there is no
explicit handler for MethodArgumentNotValidException or a generic Exception.class safety net. Validation
errors still correctly return 400 via Spring's default behaviour (verified live), so this does not cause
incorrect behaviour, but a peer-comparison standard would add a structured per-field validation error
handler and a catch-all 500 handler for defence in depth.
Not implemented from the stated stretch goals: partial-title search. The available-books filter and the
status-update endpoint are reasonable, simpler substitutes for "borrow/return", but there is no distinct
borrow/return pair of endpoints with conflict (409) handling for double-borrow/double-return scenarios.
What could have been done better: removing the stale "// TODO" comments from finished methods and adding a
dedicated MethodArgumentNotValidException handler (returning field-level messages) plus a generic
Exception.class fallback in GlobalExceptionHandler would round out an already solid, correctly working
submission. Implementing partial-title search and/or a genuine borrow/return pair with conflict handling
would have added further bonus/kudos value.
```
