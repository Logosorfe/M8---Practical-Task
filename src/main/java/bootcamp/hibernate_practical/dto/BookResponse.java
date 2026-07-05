package bootcamp.hibernate_practical.dto;


public record BookResponse(
        Long id,
        String title,
        String author,
        String genre,
        int publicationYear,
        boolean available
) {
}