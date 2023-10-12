package by.pizzzadog.exception;

public class AuthException extends RuntimeException {
    private String message;

    public AuthException(String message) {
        super(message);
    }
}
