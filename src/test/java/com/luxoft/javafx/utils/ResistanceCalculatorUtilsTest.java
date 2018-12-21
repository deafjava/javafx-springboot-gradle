package com.luxoft.javafx.utils;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class ResistanceCalculatorUtilsTest {

    private double r1, r2, result;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1, 0.5},
                {3, 7, 2.1},
                {10, 40, 8},
                {8, 2, 1.6}
        });
    }

    @Test
    public void expectedResistanceResultsTest() {
        assertTrue(ResistanceCalculatorUtils.parallel(r1, r2) == result);
    }
}