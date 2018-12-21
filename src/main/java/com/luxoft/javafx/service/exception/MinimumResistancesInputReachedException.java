package com.luxoft.javafx.service.exception;

public class MinimumResistancesInputReachedException extends Exception {

    public MinimumResistancesInputReachedException() {
        super("Let at least 2 resistors to make parallel calculation!");
    }
}
