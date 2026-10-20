package org.example.adapter;

public class OrderParseException extends RuntimeException {
    public OrderParseException(String message) {
        super(message);
    }

    public OrderParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
