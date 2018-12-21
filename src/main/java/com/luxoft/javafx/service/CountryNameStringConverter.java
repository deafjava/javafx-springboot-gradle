package com.luxoft.javafx.service;

import com.luxoft.javafx.domain.Country;
import javafx.util.StringConverter;

public class CountryNameStringConverter extends StringConverter<Country> {

    @Override
    public String toString(Country object) {
        return object.getName();
    }

    @Override
    public Country fromString(String string) {
        return null;
    }
}
