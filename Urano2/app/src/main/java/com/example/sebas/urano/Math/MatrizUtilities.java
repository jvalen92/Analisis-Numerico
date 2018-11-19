package com.example.sebas.urano.Math;

import com.example.sebas.urano.Matriz;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
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

    private static int[] shape(double[][] A) {
        return new int[]{A.length, A[0].length};
    }

    public static double[][] aumentar(double[][] A, double[] b) {
        double[][] nA = new double[A.length][A.length + 1];
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A.length; ++j) {
                nA[i][j] = A[i][j];
            }
        }
        for (int i = 0; i < A.length; ++i) {
            nA[i][A.length] = b[i];
        }
        return nA;
    }

    public static void intercambiarFilas(double[][] A, int i, int j) {
        assert i < A.length && i > -1 && j < A.length && j > -1;
        double[] tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void intercambiarColumnas(double[][] A, int i, int j) {
        assert i < A.length && i > -1 && j < A[0].length && j > -1;
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        double[] tmp = realMatrix.getColumn(i);
        realMatrix.setColumn(i, realMatrix.getColumn(j));
        realMatrix.setColumn(j, tmp);
        A = realMatrix.getData();
    }

    public static double[] productoMatricial(double[][] A, double[] b) {
        double[] res = new double[A.length];
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A.length; ++j) {
                res[i] += A[i][j] * b[j];
            }
        }
        return res;
    }

    public static void imprimir(Object[][] A) {
        for (Object[] x : A) {
            for (Object xx : x) {
                System.out.print(xx);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public static void adicionar(ArrayList<String> a, double[] x) {
        for (int i = 0; i < x.length; ++i) {
            a.add(String.valueOf(x[i]));
        }
    }

    public static void imprimir(double[] x, boolean nl) {
        for (double y : x) System.out.print(y + "\t");
        if (nl) System.out.println();
    }

    public static double[] restar(double[] x1, double[] x2) {
        double[] res = new double[x1.length];
        for (int i = 0; i < x1.length; ++i) res[i] = x1[i] - x2[i];
        return res;
    }

    public static void llenarDiagonal(double valor, double[][] A) {
        for (int i = 0; i < A.length; ++i) A[i][i] = valor;
    }

    public static int[] organizar(int n) {
        int[] org = new int[n];
        for (int i = 0; i < n; ++i) org[i] = i;
        return org;
    }

    public static double[] diag(double[][] A) {
        double[] res = new double[A.length];
        for (int i = 0; i < res.length; ++i) {
            res[i] = A[i][i];
        }
        return res;
    }

    public static double[][] lowd(double[][] A) {
        double[][] res = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                res[i][j] = A[i][j];
            }
        }
        return res;
    }

    public static double[][] uppd(double[][] A) {
        double[][] res = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A[0].length; j++) {
                res[i][j] = A[i][j];
            }
        }
        return res;
    }

    public static double[][] suma(double[][] A, double[][] B) {
        double[][] res = new double[A.length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                res[i][j] += A[i][j] + B[i][j];
            }
        }
        return res;
    }

    private static double max(double[] x) {
        double mx = 0.0;
        for (int i = 0; i < x.length; i++) {
            mx = Math.max(mx, x[i]);
        }
        return mx;
    }

    private static double radioEspectral(double[][] A) {
        EigenDecomposition eigenDecomposition = new EigenDecomposition(new Array2DRowRealMatrix(A));
        double[] x = eigenDecomposition.getRealEigenvalues();
        return max(x);
    }

    public static boolean contieneZero(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i][j] == 0.0) return true;
            }
        }
        return false;
    }

    public static boolean estrictamenteDiagonalDominante(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < A.length; j++) {
                sum += Math.abs(A[i][j]);
            }
            if (sum - Math.abs(A[i][i]) >= Math.abs(A[i][i])) return false;
        }
        return true;
    }

    public static boolean definidaPositiva(double[][] A) {
        double[] x = new EigenDecomposition(new Array2DRowRealMatrix(A)).getRealEigenvalues();
        for (int i = 0; i < x.length; ++i) {
            if(x[i] == 0) return false;
        }
        return true;
    }

    public static String[][] format(double[][] A){
        String[][] k = new String[A.length][A[0].length];
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                k[i][j] = String.format("%.4f", A[i][j]).replace(',', '.');
            }
        }
        return k;
    }
    public static double[][] formatDouble(double[][] A){
        double[][] k = new double[A.length][A[0].length];
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                k[i][j] = Double.parseDouble(String.format("%.4f", A[i][j]).replace(',', '.'));
            }
        }
        return k;
    }
    public ArrayList<String[]> formatArray(ArrayList<String[]> to){
        ArrayList<String[]> res = new ArrayList<>();
        for(String[] s: to){
            String[] pk = fformat(s);
            res.add(pk);
        }
        return res;
    }
    private static String[] fformat(String[] to){
        String[] res = new String[to.length];
        int cnt = 0;
        for(String s: to){
            double it = Double.parseDouble(s);
            res[cnt++] = String.format("%.4f", it).replace(',', '.');
        }
        return res;
    }


}
