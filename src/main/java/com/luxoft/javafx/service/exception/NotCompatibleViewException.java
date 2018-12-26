package com.luxoft.javafx.service.exception;

public class NotCompatibleViewException extends RuntimeException {
    public NotCompatibleViewException() {
        super("View must be Label or TextField!");
    }
}
