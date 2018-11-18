package com.example.sebas.urano;

import com.example.sebas.urano.Math.NumericalUtilities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class NumericalUtilitiesTest {
    private double delta = 1e-10;
    @Test
    public void fe_isCorrect() {
        assertEquals(NumericalUtilities.fe(1.23713213E-7), 1.24E-7, delta);
        assertEquals(NumericalUtilities.fe(1.23513213E-7), 1.24E-7, delta);
        assertEquals(NumericalUtilities.fe(1.23413213E-7), 1.23E-7, delta);
        assertEquals(NumericalUtilities.fe(3.11111E-8), 3.11E-8, delta);
        assertEquals(NumericalUtilities.fe(1.12191299129E-8), 1.12E-8, delta);
    }
    @Test
    public void fd_isCorrect() {
        assertEquals(NumericalUtilities.fd(1.23123E-16, 3), 0, delta);
        assertEquals(NumericalUtilities.fd(1.23123E-13, 2), 1.23E-13, delta);
        assertEquals(NumericalUtilities.fd(0.23123E-14, 3), 0, delta);
    }

    @Test
    public void evaluateFunction_isCorrect(){
        assertEquals(NumericalUtilities.evaluarFuncion("2x", 0.5), 1.0, delta);
        assertEquals(NumericalUtilities.evaluarFuncion("x^2", 0.5), 0.25, delta);
        assertEquals(NumericalUtilities.evaluarFuncion("Ln(x)", 1), 0, delta);
        assertEquals(NumericalUtilities.evaluarFuncion("sin(x)", 6), -0.27941549819, delta);
        assertEquals(NumericalUtilities.evaluarFuncion("3*x", 3), 9.0, delta);
    }
}
