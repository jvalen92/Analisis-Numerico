package com.example.sebas.urano.Math;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MatrizUtilities {
    private static double det(double[][] A, int n) {
        if (n == 1) {
            return A[0][0];
        }
        double resultado = 0.0;
        for (int i = 0; i < A.length; ++i) {
            resultado += ((i & 1) == 0 ? 1 : -1) * A[0][i] * det(nuevaMatriz(A, i), n - 1);
        }
        return resultado;
    }

    private static double[][] nuevaMatriz(double[][] A, int x) {
        assert A.length > 0 && A[0].length > 0;
        double[][] newA = new double[A.length - 1][A.length - 1];
        for (int i = 1; i < A.length; ++i) {
            double[] v = new double[A.length - 1];
            int k = 0;
            for (int j = 0; j < A[0].length; j++) {
                if (j != x) {
                    v[k++] = A[i][j];
                }
            }
            newA[i - 1] = v;
        }
        return newA;
    }

    public static double det(double[][] A) {
        assert A.length > 0 && A[0].length > 0 && A.length == A[0].length;
        //System.out.printf("Det = %.15f\n", det(A, A.length));
        return det(A, A.length);
    }

    public static double[][] transpuesta(double[][] A) {
        RealMatrix matrix = new Array2DRowRealMatrix(A);
        return matrix.transpose().getData();
    }

    private static int [] shape(double[][] A){
        return new int[]{A.length, A[0].length};
    }

    public static double[][] aumentar(double[][] A, double[] b) {
        double[][] nA = new double[A.length][A.length + 1];
        System.out.println(shape(nA)[0] + " "+ shape(nA)[1]);
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A.length; ++j) {
                nA[i][j] = A[i][j];
            }
        }
        for(int i = 0; i < A.length; ++i){
            nA[i][A.length] = b[i];
        }
        return nA;
    }
    public static void intercambiarFilas(double[][] A, int i, int j){
        assert i < A.length && i > -1 && j < A.length && j > -1;
        double[] tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    public static void intercambiarColumnas(double[][] A, int i, int j){
        assert i < A.length && i > -1 && j < A[0].length && j > -1;
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        double[] tmp = realMatrix.getColumn(i);
        realMatrix.setColumn(i, realMatrix.getColumn(j));
        realMatrix.setColumn(j, tmp);
        A = realMatrix.getData();
    }
    public static double[] productoMatricial(double[][] A, double[] b){
        double[] res = new double[A.length];
        for(int i = 0; i < A.length; ++i){
            for(int j = 0; j < A.length; ++j){
                res[i] += A[i][j] * b[j];
            }
        }
        return res;
    }
    public static void imprimir(double[][] A){
        for(double[] x: A){
            for(double xx: x){
                System.out.print(__);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    public static void adicionar(ArrayList<String> a, double[] x){
        for(int i = 0; i < x.length; ++i){
            a.add(String.valueOf(x[i]));
        }
    }
    public static void imprimir(double[] x, boolean nl){
        for(double y: x) System.out.print(y + "\t");
        if(nl) System.out.println();
    }
    public static double[] restar(double[] x1, double[] x2){
        double[] res = new double[x1.length];
        for(int i = 0; i < x1.length; ++i) res[i] = x1[i] - x2[i];
        return res;
    }
    public static void llenarDiagonal(double valor, double[][] A){
        for(int i = 0; i < A.length; ++i) A[i][i] = valor;
    }
    public static int[] organizar(int n){
        int[] org = new int[n];
        for(int i = 0; i < n; ++i) org[i] = i;
        return org;
    }

}
