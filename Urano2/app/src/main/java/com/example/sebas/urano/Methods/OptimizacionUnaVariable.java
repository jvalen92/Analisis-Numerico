package com.example.sebas.urano.Methods;

import com.example.sebas.urano.Math.NumericalUtilities;

import java.util.ArrayList;

public class OptimizacionUnaVariable {
    public static ArrayList<String[]> muller(String funcion, double x0, double x1, double x2, double tolerancia, int niter) {
        ArrayList<String[]> solucion = new ArrayList<>();
        double h1 = x1 - x0;
        double h2 = x2 - x1;
        double y1 = (NumericalUtilities.evaluarFuncion(funcion, x1) - NumericalUtilities.evaluarFuncion(funcion, x0)) / h1;
        double y2 = (NumericalUtilities.evaluarFuncion(funcion, x2) - NumericalUtilities.evaluarFuncion(funcion, x1)) / h2;
        double d = (y2 - y1) / (h2 + h1);
        int contador = 1;
        double error = tolerancia + 1;
        double p = Double.MIN_VALUE;
        solucion.add(new String[]{contador + "", p + "", "No existe", "No existe"});
        while (contador < niter && error > tolerancia) {
            double b = y2 + (h2 * d);
            double D = Math.sqrt(Math.pow(b, 2) - 4 * NumericalUtilities.evaluarFuncion(funcion, x2) * d);
            double E = Double.MIN_VALUE;
            if (Math.abs(b - D) < Math.abs(b + d)) {
                E = b + d;
            } else {
                E = b - D;
            }
            double h = -2 * (NumericalUtilities.evaluarFuncion(funcion, x2) / E);
            p = x2 + h;
            x0 = x1;
            x1 = x2;
            x2 = p;
            h1 = x1 - x0;
            h2 = x2 - x1;
            y1 = (NumericalUtilities.evaluarFuncion(funcion, x1) - NumericalUtilities.evaluarFuncion(funcion, x0)) / h1;
            y2 = (NumericalUtilities.evaluarFuncion(funcion, x2) - NumericalUtilities.evaluarFuncion(funcion, x1)) / h2;
            d = ((y2 - y1) / (h2 + h2));
            error = Math.abs(h);
            contador++;
            solucion.add(new String[]{contador + "", p + "", NumericalUtilities.evaluarFuncion(funcion, p) + "", error + ""});
        }
        return solucion.size() > 0 ? solucion : null;
    }

    public static ArrayList<String[]> steffensen(String f, double x0, int niter, double tolerancia) {
        ArrayList<String[]> solucion = new ArrayList<>();
        int contador = 0;
        double error = tolerancia + 1;
        double x1 = NumericalUtilities.evaluarFuncion(f, x0);
        double x2 = NumericalUtilities.evaluarFuncion(f, x1);
        double p = x0 - (Math.pow(x1 - x0, 2) / (x2 - 2 * x1 + x0));
        solucion.add(new String[]{contador + "", x0 + "", x1 + "", x2 + "", p + "", NumericalUtilities.evaluarFuncion(f, p) + "", "No existe", "No existe"});
        x0 = p;
        while (contador < niter && error > tolerancia) {
            x1 = NumericalUtilities.evaluarFuncion(f, x0);
            x2 = NumericalUtilities.evaluarFuncion(f, x1);
            p = x0 - (Math.pow(x1 - x0, 2) / (x2 - 2 * x1 + x0));
            error = Math.abs(p - x0);
            contador++;
            solucion.add(new String[]{contador + "", x0 + "", x1 + "", x2 + "", p + "", NumericalUtilities.evaluarFuncion(f, p) + "", error + "", ""});
            x0 = p;
        }

        return solucion.size() > 0 ? solucion : null;
    }
}
