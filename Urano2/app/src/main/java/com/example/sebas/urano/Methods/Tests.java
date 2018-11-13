package com.example.sebas.urano.Methods;

import java.util.ArrayList;

public class Tests {
    public static void main(String[] args) {
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        ArrayList<String[]> res1 = UnaVariable.biseccion("x^2-3", -1, 5, 1e-8, 100);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        ArrayList<String[]> res2 = UnaVariable.newton("x^2-3", "2x", 1e-8, 0.5, 100);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        ArrayList<String[]> res3 = UnaVariable.busquedaIncremental("x^2-3", -5, 0.5,  100);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        ArrayList<String[]> res4 = UnaVariable.raicesMultiples("x^2-3", "2x", "2", -0.5, 1e-8,  100);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());

    }
}
