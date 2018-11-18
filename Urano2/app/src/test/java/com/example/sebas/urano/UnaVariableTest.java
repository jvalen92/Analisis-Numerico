package com.example.sebas.urano;

import com.example.sebas.urano.Methods.SingletonMensaje;
import com.example.sebas.urano.Methods.UnaVariable;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class UnaVariableTest {
    private final String f1 = "ln(x) - 1";
    private final String f2 = "(ln(x) -1) ^ 2";
    private final String g = "x(2 - ln(x) -1)";
    private final double Tol = 1e-7;
    private final int NMAX = 100;
    private SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();

    @Test
    public void testBiseccion() {
        System.out.println("\t\t Bisección \t\t");
        double a = 1, b = 2;
        ArrayList<String[]> res = UnaVariable.biseccion(f1, a, b, Tol, NMAX);
        assertEquals(singletonMensaje.getError(), true);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
        b = 3;
        res = UnaVariable.biseccion(f1, a, b, Tol, NMAX);
        assertEquals(singletonMensaje.getError(), false);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
    }

    @Test
    public void testNewton() {
        System.out.println("\t\t Newton \t\t");
        double x0 = -1;
        ArrayList<String[]> res = UnaVariable.newton(f1, "1/x", x0, Tol, NMAX);
        assertEquals(singletonMensaje.getError(), true);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
        x0 = 2;
        res = UnaVariable.newton(f1, "1/x", x0, Tol, NMAX);
        assertEquals(singletonMensaje.getError(), true);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
        res = UnaVariable.newton("x^3+4*(x^2)-10", "3*x^2+8*x", Tol, 1.5, 80);
        System.out.println(singletonMensaje.getError());
        System.out.println(singletonMensaje.getMensajeActual());
    }

    @Test
    public void testNewtonModificado() {
        System.out.println("\t\t Raices Multiples \t\t");
        double x0 = 1;
        ArrayList<String[]> res = UnaVariable.raicesMultiples(f2, "2(ln(x) - 1)/x", "2(2 - ln(x))/x^2", x0, Tol, NMAX);
        assertEquals(singletonMensaje.getError(), true);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
        x0 = 2;
        res = UnaVariable.raicesMultiples(f2, "2(ln(x) - 1)/x", "2(2 - ln(x))/x^2", x0, Tol, NMAX);
        assertEquals(singletonMensaje.getError(), false);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
    }

    @Test
    public void testSecante() {

        System.out.println("\t\t Secante \t\t");
        double x0 = 1.1, x1 = 1.5;
        ArrayList<String[]> res = UnaVariable.secante(f1, Tol, x0, x1, NMAX);
        assertEquals(singletonMensaje.getError(), false);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
        res = UnaVariable.secante("exp(x) - 5x + 2", 10e-5, 0.5, 1, 80);
        for(String[] r: res){
            for(String s: r){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
    @Test
    public void testPuntoFijo() {
        System.out.println("\t\t Punto Fijo \t\t");
        double x0 = 0;
        ArrayList<String[]> res = UnaVariable.puntoFijo(f1, g, Tol, x0, NMAX);
        assertEquals(singletonMensaje.getError(), true);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
        x0 = 3;
        res =  UnaVariable.puntoFijo(f1, g, Tol, x0, NMAX);
        assertEquals(singletonMensaje.getError(), true);
        System.out.print(singletonMensaje.getError() ? singletonMensaje.getMensajeActual() + "\n": "No hay error\n");
    }
}
