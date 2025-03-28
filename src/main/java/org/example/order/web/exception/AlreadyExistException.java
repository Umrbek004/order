package org.example.order.web.exception;

public class AlreadyExistException extends RuntimeException {

    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public AlreadyExistException(String message) {
        this.message = message;
    }
}
