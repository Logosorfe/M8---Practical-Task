package bootcamp.hibernate_practical.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateBookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @NotBlank String genre,
        @Min(867) int publicationYear
) {}
