package pl.pomoku.backend.dto.request;

public record RegisterRequest(String firstName, String lastName, String email, String password) {
}
