package com.luxoft.javafx.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResistanceCalculatorUtils {
    public double parallel(double r1, double r2) {
        return (r1 * r2) / (r1 + r2);
    }
}
