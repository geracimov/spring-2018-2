package ru.geracimov.otus.spring.hw22springsecurityacl.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message,
                             Throwable cause) {
        super(message, cause);
    }
}
