package com.example.sebas.urano.metodos;

public class SistemaDeEcuaciones {
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

    public static double[] despejar(double[][] A, double[] b) {
        Utilities.Par<double[][], double[]> Ab= escalonamiento(A, b);
        A = Ab.getPrimero();
        b = Ab.getSegundo();
        int n = A.length - 1;
        double[] x = new double[n + 1];
        x[n] = b[n] / A[n][n];
        //System.out.println(b[n] + " " + A[n][n] + " " + x[n]);
        for(int dx = n - 1; dx > -1; --dx){
            double suma = 0;
            for(int dy = dx + 1; dy < n + 1; ++dy){
                suma += A[dx][dy] * x[dy];
            }
            x[dx] = (b[dx] - suma) / A[dx][dx];
        }
        return x;
    }

    public static Utilities.Par<double[][], double[]> escalonamiento(double[][] A, double[] b) {
        int n = A.length;
        if (n != A[0].length) {
            System.out.println("La matriz no es adecuada");
        } else if (b.length != n) {
            System.out.println("Hay diferente numero de terminos independientes e incognitas");
        } else if (det(A, n) == 0) {
            System.out.println("La matriz es no invertible");
        } else {
            for (int k = 0; k < n - 1; ++k) {
                if (A[k][k] == 0) {
                    int j = k + 1;
                    while (j < n && A[j++][k] == 0) ;
                    if (j < n) {
                        for (int l = k; l < n; ++l) {
                            A[k][l] += A[j][l];
                        }
                        b[k] += b[j];
                    }
                }
                for (int i = k + 1; i < n; ++i) {
                    if (A[i][k] != 0) {
                        double multiplicador = A[i][k] / A[k][k];
                        for (int l = k; l < n; ++l) {
                            A[i][l] -= multiplicador * A[k][l];
                        }
                        b[i] -= multiplicador * b[k];
                    }
                }
            }
        }
        return new Utilities.Par<>(A, b);
    }
    public static double[][] transpuesta(double[][] A){
        double[][] Ap = new double[A.length][A.length];
        for(int i = 0; i < A.length; ++i){
            for(int j = 0; j < A[0].length; ++i){
                Ap[i][j] = A[j][i];
            }
        }
        return Ap;
    }
    public static double[] transpuesta(double[] A){
        double[] Ap = new double[A.length];
        for(int i = 0; i < A.length; ++i){
            Ap[i] = A[A.length - i - 1];
        }
        return Ap;
    }
}
