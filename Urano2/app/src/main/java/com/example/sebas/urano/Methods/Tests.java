package com.example.sebas.urano.Methods;

import java.util.ArrayList;

public class Tests {
    public static void main(String[] args) {
        String f1 = "ln(x) - 1";
        String f2 = "(ln(x) - 1)^2";
        String g = "x(2 - ln(x))";
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        /*ArrayList<String[]> res1 = UnaVariable.biseccion(f1, 1, 3, 1e-17, 100);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        */

        ArrayList<String[]> res2 = UnaVariable.newton(f1, "1/x", 1e-8, 0.5, 100);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        /*ArrayList<String[]> res3 = UnaVariable.busquedaIncremental("x^2-3", -5, 0.5, 100);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        ArrayList<String[]> res4 = UnaVariable.raicesMultiples("x^2-3", "2x", "2", -0.5, 1e-8, 100);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        */
    }
}
