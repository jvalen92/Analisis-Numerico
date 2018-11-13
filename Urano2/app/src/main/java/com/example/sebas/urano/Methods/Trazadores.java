package com.example.sebas.urano.Methods;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Trazadores {
    public static String[][] trazadoresLineales(double[] x, double[] y) throws IOException {
        int n = x.length - 1;
        double[][] A = new double[n * 2][n * 2];
        double[] b = new double[n * 2];
        String[][] polinomios = new String[n][n];
        String polinomio, intervalo;
        int j, l;
        for (int i = 0; i < n; i++) {
            j = 2 * i;
            A[j][j] = x[i];
            A[j][j + 1] = 1;
            b[j] = y[i];
            A[j + 1][j] = x[i + 1];
            A[j + 1][j + 1] = 1;
            b[j + 1] = y[i + 1];
        }
        Object retVal[] = SistemaDeEcuaciones.eliminacionGaussianaTotal(A, b);
        double[] ab = (double[]) retVal[1];
        l = 0;
        for (int i = 0; i < ab.length; i += 2) {
            polinomio = String.valueOf(ab[i]) + "x";
            polinomio = ab[i + 1] > 0 ? polinomio + "+" : polinomio;
            polinomio += String.valueOf(ab[i + 1]);
            polinomios[l][0] = polinomio;
            intervalo = String.valueOf(x[l]) + "<= x >= " + String.valueOf(x[l + 1]);
            polinomios[l][1] = intervalo;
            l += 1;
        }
        return polinomios;
    }

    public static String[][] trazadoresCuadraticos(double[] x, double[] y) throws IOException {
        int n = x.length - 1;
        double[][] A = new double[n * 3][n * 3];
        double[] b = new double[n * 3];
        String[][] polinomios = new String[n][n];
        String polinomio, intervalo;
        int j, l;
        for (int i = 0; i < n; i++) {
            j = 2 * i;
            l = 3 * i;
            A[j][l] = Math.pow(x[i], 2);
            A[j][l + 1] = x[i];
            A[j][l + 2] = 1;
            b[j] = y[i];
            A[j + 1][l] = Math.pow(x[i + 1], 2);
            A[j + 1][l + 1] = x[i + 1];
            A[j + 1][l + 2] = 1;
            b[j + 1] = y[i + 1];
        }
        j = 0;
        l = 1;
        for (int i = n * 2; i < (n * 3) - 1; i++) {
            A[i][j] = 2 * x[l];
            A[i][j + 1] = 1;
            A[i][j + 3] = -2 * x[l];
            A[i][j + 4] = -1;
            j += 3;
            l += 1;
        }
        A[(3 * n) - 1][0] = 1;
        Object retVal[] = SistemaDeEcuaciones.eliminacionGaussianaTotal(A, b);
        double[] abc = (double[]) retVal[1];
        l = 0;
        for (int i = 0; i < abc.length; i += 3) {
            polinomio = String.valueOf(abc[i]) + "x^2";
            polinomio = abc[i + 1] > 0 ? polinomio + "+" : polinomio;
            polinomio += String.valueOf(abc[i + 1]) + "x";
            polinomio = abc[i + 2] > 0 ? polinomio + "+" : polinomio;
            polinomio += String.valueOf(abc[i + 2]);
            polinomios[l][0] = polinomio;
            intervalo = String.valueOf(x[l]) + "<= x >= " + String.valueOf(x[l + 1]);
            polinomios[l][1] = intervalo;
            l += 1;
        }
        return polinomios;
    }

    public static String[][] trazadoresCubicos(double[] x, double[] y) throws IOException {
        int n = x.length - 1;
        double[][] A = new double[n * 4][n * 4];
        double[] b = new double[n * 4];
        String[][] polinomios = new String[n][n];
        String polinomio, intervalo;
        int j, l;
        for (int i = 0; i < n; i++) {
            j = 2 * i;
            l = 4 * i;
            A[j][l] = Math.pow(x[i], 3);
            A[j][l + 1] = Math.pow(x[i], 2);
            A[j][l + 2] = x[i];
            A[j][l + 3] = 1;
            b[j] = y[i];
            A[j + 1][l] = Math.pow(x[i + 1], 3);
            A[j + 1][l + 1] = Math.pow(x[i + 1], 2);
            A[j + 1][l + 2] = x[i + 1];
            A[j + 1][l + 3] = 1;
            b[j + 1] = y[i + 1];
        }
        j = 0;
        l = 1;
        for (int i = n * 2; i < (n * 3) - 1; i++) {
            A[i][j] = Math.pow(3 * x[l], 2);
            A[i][j + 1] = 2 * x[l];
            A[i][j + 2] = 1;
            A[i][j + 4] = -3 * Math.pow(x[l], 2);
            A[i][j + 5] = -2 * x[l];
            A[i][j + 6] = -1;
            j += 4;
            l += 1;
        }
        j = 0;
        l = 1;
        for (int i = (n * 3) - 1; i < (n * 4) - 2; i++) {
            A[i][j] = 6 * x[l];
            A[i][j + 1] = 2;
            A[i][j + 4] = -6 * x[l];
            A[i][j + 5] = -2;
            j += 4;
            l += 1;
        }
        A[(4 * n) - 2][0] = 6 * x[0];
        A[(4 * n) - 2][1] = 2;
        A[(4 * n) - 1][4 * (n - 1)] = 6 * x[n];
        A[(4 * n) - 1][4 * (n - 1) + 1] = 2;
        Object retVal[] = SistemaDeEcuaciones.eliminacionGaussianaTotal(A, b);
        double[] abc = (double[]) retVal[1];
        l = 0;
        for (int i = 0; i < abc.length; i += 4) {
            polinomio = String.valueOf(abc[i]) + "x^3";
            polinomio = abc[i + 1] > 0 ? polinomio + "+" : polinomio;
            polinomio += String.valueOf(abc[i + 1]) + "x^2";
            polinomio = abc[i + 2] > 0 ? polinomio + "+" : polinomio;
            polinomio += String.valueOf(abc[i + 2]) + "x";
            polinomio = abc[i + 3] > 0 ? polinomio + "+" : polinomio;
            polinomio += String.valueOf(abc[i + 3]);
            polinomios[l][0] = polinomio;
            intervalo = String.valueOf(x[l]) + "<= x >= " + String.valueOf(x[l + 1]);
            polinomios[l][1] = intervalo;
            l += 1;
        }
        return polinomios;
    }
}