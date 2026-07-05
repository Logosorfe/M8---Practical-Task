package bootcamp.hibernate_practical.repository;

import bootcamp.hibernate_practical.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);

    List<Book> findByAvailableTrue();

    @Query("SELECT b FROM Book b WHERE b.publicationYear > :year")
    List<Book> findAfterYear(@Param("year") int year);

    @Query("SELECT COUNT(b) FROM Book b")
    long countAll();
}
