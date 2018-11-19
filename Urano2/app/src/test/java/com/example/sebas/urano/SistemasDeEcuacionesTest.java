package com.example.sebas.urano;

import com.example.sebas.urano.Methods.SingletonMensaje;
import com.example.sebas.urano.Methods.SistemaDeEcuaciones;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SistemasDeEcuacionesTest {
    double[][] A = new double[][]{{12, 0, 4}, {0, 0, 0}, {4, 5, 10}};
    double[][] M = new double[][]{{7, 3, 4}, {4, 1, 5}, {6, -2, 8}};
    double[] b = new double[]{1, 1, 1};
    double[] x0 = new double[]{0, 0, 0};
    private final double Tol = 10e-7;
    private final int MAXN = 100;
    private final SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();

    @Test
    public void testCholesky() {
        Object[] result = SistemaDeEcuaciones.choleskySolver(A, b);
        assertEquals(singletonMensaje.getError(), true);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        A[1][1] = 1;
        result = SistemaDeEcuaciones.choleskySolver(A, b);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
    }

    @Test
    public void testCrout() {
        Object[] result = SistemaDeEcuaciones.croutSolver(A, b);
        assertEquals(singletonMensaje.getError(), true);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        A[1][1] = 1;
        result = SistemaDeEcuaciones.choleskySolver(A, b);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
    }

    @Test
    public void testPivoteoParcial() {
        Object[] result = SistemaDeEcuaciones.eliminacionGaussianaParcial(A, b);
        assertEquals(singletonMensaje.getError(), true);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        A[1][1] = 1;
        result = SistemaDeEcuaciones.choleskySolver(A, b);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
    }

    @Test
    public void testJacobi() {
        ArrayList<String[]> res = SistemaDeEcuaciones.jacobi(M, b, Tol, x0, MAXN);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        A[1][1] = 10;
        res = SistemaDeEcuaciones.jacobi(M, b, Tol, x0, MAXN);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
    }
    @Test
    public void testSeidel() {
        ArrayList<String[]> res = SistemaDeEcuaciones.gaussSeidel(M, b, Tol, x0, MAXN);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        A[1][1] = 10;
        res = SistemaDeEcuaciones.gaussSeidel(M, b, Tol, x0, MAXN);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
    }
    @Test
    public void testSOR() {
        double w = 1.2;
        ArrayList<String[]> res = SistemaDeEcuaciones.SOR(M, b, Tol, x0, w, MAXN);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
        A[1][1] = 10;
        res = SistemaDeEcuaciones.SOR(M, b, Tol, x0, w, MAXN);
        assertEquals(singletonMensaje.getError(), false);
        System.out.println(singletonMensaje.getMensajeActual());
        System.out.println(singletonMensaje.getError());
    }
}
