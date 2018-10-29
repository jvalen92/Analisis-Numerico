package com.example.sebas.urano.Methods;

import java.util.ArrayList;

public class Tests {
    private static final double Tol = 1e-7;
    private static final int N = 100;
    public static void main(String[] args) {
        /*String f = "ln((sin(x))^2 + 1) - 1/2";
        String f = "ln((sin(x))^2 + 1) - 1/2";
        String fp = "(2*Cos(x)*Sin(x))/(1+Sin(x)^2)";
        String g = "ln((sin(x))^2+1)   -1/2";
        String h = "exp(x) - x -1";
        String hp = "exp(x) - 1";
        String hpp = "exp(x)";
        ArrayList<String[]> res = UnaVariable.biseccion(f, 0, 1, Tol, N);
        //ArrayList<String[]> res = UnaVariable.biseccion(f, 0, 1, Tol, N);
        //ArrayList<String[]> res = UnaVariable.reglaFalsa(f, 0, 1, Tol, N);
        //ArrayList<String[]> res = UnaVariable.newton(f,"", false, Tol, 0.5, N);
        //ArrayList<String[]> res = UnaVariable.newton(f,fp, true, Tol, 0.5, N);
        //ArrayList<String[]> res = UnaVariable.puntoFijo(f, g, Tol, -0.5, N);
        //ArrayList<String[]> res = UnaVariable.secante(f, Tol, 0.5, 1, N);
        //ArrayList<String[]> res = UnaVariable.raicesMultiples(h, "", "", false, 1, Tol, N);
        //ArrayList<String[]> res = UnaVariable.raicesMultiples(h, hp, hpp, true, 1, Tol, N);
        //ArrayList<String[]> res = OptimizacionUnaVariable.steffensen(f, -0.5, N, Tol);
        //ArrayList<String[]> res = OptimizacionUnaVariable.muller(f, 0.5, 1, 1.5, Tol, N);


        */
        double mat [][]={{14.0,-2.0,1.0,4.0},{0.0,3.0,5.0,2.0},{4.0,-1.0,8.0,0.5},{0.0,0.0,-3.0,6.0}};
        double b [] = {1.0,-4.0,0.0,1.0};
        double tol = 10e-4;
        double x0 []= {0.0,0.0,0.0,0.0};
        int niter =80;
        double w=1.22;
        //ArrayList<String[]> res =SistemaDeEcuaciones.MetodoIterativo.jacobi(mat,b,tol,x0,niter);
        //ArrayList<String[]> res =SistemaDeEcuaciones.MetodoIterativo.gaussSeidel(mat,b,tol,x0,niter);

        ArrayList<String[]> res =SistemaDeEcuaciones.SOR(mat,b,tol,x0,w,niter);
        /*
        for(String[] s: res){
            for(String r: s){
                System.out.print(r + " | ");
            }
            System.out.println();
        }



        */
    }
}
