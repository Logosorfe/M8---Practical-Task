package bootcamp.hibernate_practical.dto;


import jakarta.validation.constraints.NotNull;

public record UpdateBookStatus(@NotNull Boolean isAvailable) {
}
//because by logic you can only change book's availability, I removed other fields