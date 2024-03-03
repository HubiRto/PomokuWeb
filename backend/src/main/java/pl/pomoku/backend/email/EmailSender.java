package pl.pomoku.backend.email;

public interface EmailSender {
    void send(String to, String email);
}
