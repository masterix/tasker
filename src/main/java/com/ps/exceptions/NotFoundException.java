package com.ps.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
