package com.example.sebas.urano.metodos;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    private static final int N = 100;
    private static final double TOL = 1e-7;
    UnaVariable p = new UnaVariable();


    public static void main(String[] args) {

        UnaVariable uv = new UnaVariable();
        //uv.biseccion(0, 1, TOL, N);
        uv.puntoFijo(TOL,-0.5,100);
        /*
        UnaVariable.busquedaIncremental(-3, 0.5, N);
        UnaVariable.reglaFalsa(0, 1, TOL, N);
        UnaVariable.newton(TOL, 0.5, N);
        UnaVariable.puntoFijo(TOL, -0.5, N);
        UnaVariable.secante(TOL, 0.5, 1, N);
        UnaVariable.raicesMultiples(1, TOL, N);*/
        //OptimizacionUnaVariable.steffensen(-0.5, N, TOL);
        //OptimizacionUnaVariable.muller(0.5, 1, 1.5, TOL, N);
        double[][] A = new double[][]{{2, -1, 0, 3}, {1, 0.5, 3, 8}, {0, 13, -2, 11}, {14, 5, -2, 3}};
        double[] b = new double[]{1,1,1,1};
        double[] x = SistemaDeEcuaciones.despejar(A, b);
        PrintWriter writer = null;
        try{
            writer = new PrintWriter("ecuaciones_lineales.txt");
        }catch (IOException ex){
            System.out.println(ex);
        }
        writer.println("Det = " + SistemaDeEcuaciones.det(A));
        writer.println("Solucion: ");
        writer.print("x = [");
        for(double r: x) writer.print(" " + r);
        writer.println(" ]");
        writer.println("Matriz solucion: ");
        for(double[] d: A){
            for(double r: d){
                writer.printf("%.15f ", r);
            }
            writer.println();
        }
        writer.flush();
        writer.close();
        Graficador.graficar();
    }
}