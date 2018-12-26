package com.luxoft.javafx.model.domain;

public class ElectronicComponentFactory {
    public static ElectronicComponent resistor(Double value) {
        ElectronicComponent e = new ElectronicComponent();
        e.setValue(value);
        e.setType(ElectronicComponentType.RESISTOR);
        return e;
    }
}
