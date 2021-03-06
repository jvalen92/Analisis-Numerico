package com.example.sebas.urano.Methods;

import com.example.sebas.urano.Math.MatrizUtilities;
import com.example.sebas.urano.Matriz;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class SistemaDeEcuaciones {

    static final SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();

    public static Object[] eliminacionGaussianaSimple(double[][] A, double[] b) {
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        singletonMensaje.setError(false);
        if (luDecomposition.getDeterminant() == 0) {
            singletonMensaje.setMensajeActual("El sistema no es invertible.");
            singletonMensaje.setError(true);
            return new Object[]{A, b};
        }
        double[][] Ub = eliminacionSimple(A, b);
        if (singletonMensaje.getError()) {
            return new Object[]{A, b};
        }
        double[] x = sustitucionRegresiva(Ub, A.length);
        singletonMensaje.setMensajeActual("Sistema solucionado");
        return new Object[]{Ub, x};
    }

    public static Object[] eliminacionGaussianaParcial(double[][] A, double[] b) {
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        singletonMensaje.setError(false);
        if (luDecomposition.getDeterminant() == 0) {
            singletonMensaje.setMensajeActual("El sistema no es invertible.");
            singletonMensaje.setError(true);
            return new Object[]{A, b};
        }
        double[][] Ub = eliminacionGaussianaPivoteoParcial(A, b);
        if (singletonMensaje.getError()) {
            return new Object[]{A, b};
        }
        double[] x = sustitucionRegresiva(Ub, A.length);
        singletonMensaje.setMensajeActual("Sistema solucionado");
        return new Object[]{Ub, x};
    }

    public static Object[] eliminacionGaussianaTotal(double[][] A, double[] b) {
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        singletonMensaje.setError(false);
        if (luDecomposition.getDeterminant() == 0) {
            singletonMensaje.setMensajeActual("El sistema no es invertible.");
            singletonMensaje.setError(true);
            return new Object[]{A, b};
        }
        int[] marcas = MatrizUtilities.organizar(A.length);
        double[][] Ub = eliminacionGaussianaPivoteoTotal(A, marcas, b);
        if (singletonMensaje.getError()) {
            return new Object[]{A, b};
        }
        //for (int i = 0; i < marcas.length; ++i) System.out.print(marcas[i] + " ");
        double[] xp = sustitucionRegresiva(Ub, A.length);
        double[] x = new double[xp.length];
        for (int i = 0; i < A.length; ++i) {
            x[i] = xp[marcas[i]];
        }
        singletonMensaje.setMensajeActual("Sistema solucionado");
        return new Object[]{Ub, x};
    }

    public static Object[] eliminacionGaussianaSimpleLU(double[][] A, double[] b) {
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        singletonMensaje.setError(false);
        if (luDecomposition.getDeterminant() == 0) {
            singletonMensaje.setMensajeActual("El sistema no es invertible.");
            singletonMensaje.setError(true);
            return new Object[]{A, b};
        }
        Object[] obj = factorizacionLU_GaussSimple(A);
        if (singletonMensaje.getError()) {
            return new Object[]{A, b};
        }
        double[][] L = (double[][]) obj[0];
        double[][] U = (double[][]) obj[1];
        double[][] Lb = MatrizUtilities.aumentar(L, b);
        double[] z = sustitucionProgresiva(Lb, L.length);
        double[][] Uz = MatrizUtilities.aumentar(U, z);
        double[] x = sustitucionRegresiva(Uz, U.length);
        singletonMensaje.setMensajeActual("Sistema solucionado");
        return new Object[]{L, U, x};
    }

    public static Object[] eliminacionGaussianaParcialLU(double[][] A, double[] b) {
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        singletonMensaje.setError(false);
        if (luDecomposition.getDeterminant() == 0) {
            singletonMensaje.setMensajeActual("El sistema no es invertible.");
            singletonMensaje.setError(true);
            return new Object[]{A, b};
        }
        Object[] obj = factorizacionLU_GaussParcial(A);
        if (singletonMensaje.getError()) {
            return new Object[]{A, b};
        }
        double[][] L = (double[][]) obj[0];
        double[][] U = (double[][]) obj[1];
        double[][] P = (double[][]) obj[2];
        double[] bn = MatrizUtilities.productoMatricial(P, b);
        double[][] Lb = MatrizUtilities.aumentar(L, bn);
        double[] z = sustitucionProgresiva(Lb, L.length);
        double[][] Uz = MatrizUtilities.aumentar(U, z);
        double[] x = sustitucionRegresiva(Uz, U.length);
        if (singletonMensaje.getError()) {
            return new Object[]{A, b};
        }
        singletonMensaje.setMensajeActual("Sistema solucionado");
        return new Object[]{L, U, x};
    }

    public static double[][] eliminacionGaussianaPivoteoParcial(double[][] A, double[] b) {
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        int n = A.length;
        double[][] Ab = MatrizUtilities.aumentar(A, b);
        for (int k = 0; k < n - 1; ++k) {
            Ab = pivoteoParcial(Ab, n, k);
            if (Ab[k][k] == 0) {
                singletonMensaje.setMensajeActual("El sistema no tiene solución unica.");
                singletonMensaje.setError(true);
                return Ab;
            }
            for (int i = k + 1; i < n; ++i) {
                double mult = Ab[i][k] / Ab[k][k];
                for (int j = k; j < n + 1; j++) {
                    Ab[i][j] -= mult * Ab[k][j];
                }
            }
        }
        return Ab;
    }

    public static double[][] eliminacionGaussianaPivoteoTotal(double[][] A, int[] marcas, double[] b) {
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        int n = A.length;
        double[][] Ab = MatrizUtilities.aumentar(A, b);
        for (int k = 0; k < n - 1; ++k) {
            Ab = pivoteoTotal(Ab, marcas, n, k);
            if (Ab[k][k] == 0) {
                singletonMensaje.setMensajeActual("El sistema no tiene solución única.");
                singletonMensaje.setError(true);
                return Ab;
            }
            for (int i = k + 1; i < n; ++i) {
                double mult = Ab[i][k] / Ab[k][k];
                for (int j = k; j < n + 1; j++) {
                    Ab[i][j] -= mult * Ab[k][j];
                }
            }
        }
        return Ab;
    }


    private static double[][] pivoteoParcial(double[][] Ab, int n, int k) {
        double mx = Math.abs(Ab[k][k]);
        int idx = k;
        for (int s = k + 1; s < n; ++s) {
            if (Math.abs(Ab[s][k]) > mx) {
                mx = Math.abs(Ab[s][k]);
                idx = s;
            }
        }
        MatrizUtilities.intercambiarFilas(Ab, idx, k);
        return Ab;
    }

    private static double[][] pivoteoTotal(double[][] Ab, int[] marcas, int n, int k) {
        double mx = 0.0;
        int idx1, idx2;
        idx1 = k;
        idx2 = k;
        for (int r = k; r < n; ++r) {
            for (int s = k; s < n; ++s) {
                if (Math.abs(Ab[r][s]) > mx) {
                    mx = Math.abs(Ab[r][s]);
                    idx1 = r;
                    idx2 = s;
                }
            }
        }
        MatrizUtilities.intercambiarFilas(Ab, k, idx1);
        MatrizUtilities.intercambiarColumnas(Ab, k, idx2);
        int tmp = marcas[idx2];
        marcas[idx2] = marcas[k];
        marcas[k] = tmp;
        return Ab;
    }

    private static double[][] eliminacionSimple(double[][] A, double[] b) {
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        int n = A.length;
        double[][] Ab = MatrizUtilities.aumentar(A, b);
        for (int k = 0; k < n - 1; ++k) {
            if (Ab[k][k] == 0) {
                double mx = A[k][k];
                int idx = k;
                while (idx < n && A[idx][k] == 0) idx++;
                MatrizUtilities.intercambiarFilas(Ab, idx, k);
            }
            if (A[k][k] == 0) {
                singletonMensaje.setMensajeActual("El sistema no tiene solución única.");
                singletonMensaje.setError(true);
                return Ab;
            }
            for (int i = k + 1; i < n; ++i) {
                double mult = Ab[i][k] / Ab[k][k];
                for (int j = k; j < n + 1; j++) {
                    Ab[i][j] -= mult * Ab[k][j];
                }
            }
        }
        return Ab;
    }

    public static Object[] factorizacionLU_GaussSimple(double[][] A) {
        singletonMensaje.setError(false);
        int n = A.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        double[][] Ab = A;
        for (int k = 0; k < n - 1; ++k) {
            for (int i = k + 1; i < n; ++i) {
                if (Ab[k][k] == 0) {
                    double mx = A[k][k];
                    int idx = k;
                    while (idx < n && A[idx][k] == 0) idx++;
                    MatrizUtilities.intercambiarFilas(Ab, idx, k);
                }
                if (A[k][k] == 0) {
                    singletonMensaje.setMensajeActual("El sistema no tiene solución única.");
                    singletonMensaje.setError(true);
                    return Ab;
                }
                double mult = Ab[i][k] / Ab[k][k];
                L[i][k] = mult;
                for (int j = k; j < n; j++) {
                    Ab[i][j] -= mult * Ab[k][j];
                }
                U[k] = Ab[k];
            }
        }
        U[n - 1] = Ab[n - 1];
        MatrizUtilities.llenarDiagonal(1, L);
        return new Object[]{L, U};
    }

    public static Object[] factorizacionLU_GaussParcial(double[][] A) {
        singletonMensaje.setError(false);
        int n = A.length;
        double[][] Ab = A;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        double[][] P = new double[n][n];
        MatrizUtilities.llenarDiagonal(1, P); //Matriz identidad
        //MatrizUtilities.imprimir(P);
        for (int k = 0; k < n - 1; ++k) {
            int idx = k;
            double mx = Math.abs(Ab[k][k]);
            for (int dx = k + 1; dx < n; ++dx) {
                if (Math.abs(Ab[dx][k]) > mx) {
                    mx = Math.abs(Ab[dx][k]);
                    idx = dx;
                }
            }
            // Impacta todos los posibles actores
            MatrizUtilities.intercambiarFilas(P, idx, k);
            MatrizUtilities.intercambiarFilas(Ab, idx, k);
            MatrizUtilities.intercambiarFilas(L, idx, k);
            MatrizUtilities.intercambiarFilas(U, idx, k);
            if (Ab[k][k] == 0) {
                singletonMensaje.setMensajeActual("El sistema no tiene solución única.");
                singletonMensaje.setError(true);
            }
            for (int i = k + 1; i < n; ++i) {
                double mult = Ab[i][k] / Ab[k][k];
                L[i][k] = mult;
                for (int j = k; j < n; j++) {
                    Ab[i][j] -= mult * Ab[k][j];
                }
                U[k] = Ab[k];
            }
        }
        U[n - 1] = Ab[n - 1];
        MatrizUtilities.llenarDiagonal(1, L);
        return new Object[]{L, U, P};
    }

    public static double[] sustitucionRegresiva(double[][] Ab, int n) {
        int np = n - 1;
        double[] x = new double[n];
        x[np] = Ab[np][np + 1] / Ab[np][np];
        for (int i = np - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int p = i + 1; p < n; ++p) {
                sum += Ab[i][p] * x[p];
            }
            x[i] = (Ab[i][np + 1] - sum) / Ab[i][i];
        }
        return x;
    }

    public static double[] sustitucionProgresiva(double[][] Ab, int n) {
        singletonMensaje.setError(false);
        singletonMensaje.setMensajeActual("");
        double[] x = new double[n];
        x[0] = Ab[0][n] / Ab[0][0];
        for (int i = 1; i < n; i++) {
            double sum = 0.0;
            for (int p = 0; p < i; ++p) {
                sum += Ab[i][p] * x[p];
            }
            x[i] = (Ab[i][n] - sum) / Ab[i][i];
        }
        return x;
    }


    private static Complex[][] ceros(int n) {
        Complex[][] mat = new Complex[n][n];
        for (int i = 0; i < mat.length; ++i) {
            for (int j = 0; j < mat.length; ++j) {
                mat[i][j] = new Complex(0, 0);
            }
        }
        return mat;
    }

    private static Object[] cholesky(Complex[][] A) {
        int n = A.length;
        Complex[][] L = ceros(n);
        Complex[][] U = ceros(n);

        for (int k = 0; k < n; ++k) {
            Complex sum1 = new Complex(0, 0);
            for (int p = 0; p < k; ++p) {
                sum1 = sum1.add(L[k][p].multiply(U[p][k]));
            }
            L[k][k] = (A[k][k].subtract(sum1)).pow(0.5);
            U[k][k] = L[k][k];
            for (int i = k; i < n; ++i) {
                Complex sum2 = new Complex(0, 0);
                for (int p = 0; p < k; ++p) {
                    sum2 = sum2.add(L[i][p].multiply(U[p][k]));
                }
                L[i][k] = (A[i][k].subtract(sum2)).divide(U[k][k]);
            }
            for (int j = k + 1; j < n; ++j) {
                Complex sum3 = new Complex(0, 0);
                for (int p = 0; p < k; ++p) {
                    sum3 = sum3.add(L[k][p].multiply(U[p][j]));
                }
                U[k][j] = (A[k][j].subtract(sum3)).divide(L[k][k]);
            }
        }
        return new Object[]{L, U};
    }

    private static Object[] choleskyReal(double[][] A) {
        Complex[][] A_ = new Complex[A.length][A.length];
        for (int i = 0; i < A.length; ++i) {
            for (int j = 0; j < A.length; ++j) {
                A_[i][j] = new Complex(A[i][j], 0);
            }
        }
        Object[] obj = cholesky(A_);
        Complex[][] L_ = (Complex[][]) obj[0];
        Complex[][] U_ = (Complex[][]) obj[1];
        double[][] L = new double[A_.length][A_.length];
        double[][] U = new double[A_.length][A_.length];
        for (int i = 0; i < L_.length; ++i) {
            for (int j = 0; j < L_.length; ++j) {
                L[i][j] = L_[i][j].getReal();
                U[i][j] = U_[i][j].getReal();
            }
        }
        return new Object[]{L, U};
    }

    public static Object[] choleskySolver(double[][] A, double[] b) {
        if (!MatrizUtilities.definidaPositiva(A)) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz debe ser definida positiva.");
            return new Object[]{A, b};
        }
        if(MatrizUtilities.det(A) == 0){
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz debe ser invertible.");
            return new Object[]{A, b};
        }
        Object LU[] = choleskyReal(A);
        double L[][] = (double[][]) LU[0];
        double U[][] = (double[][]) LU[1];
        double[][] Lb = MatrizUtilities.aumentar(L, b);
        double z[] = sustitucionProgresiva(Lb, L.length);
        double[][] Uz = MatrizUtilities.aumentar(U, z);
        double x[] = sustitucionRegresiva(Uz, Uz.length);
        singletonMensaje.setMensajeActual("Sistema solucionado");
        return new Object[]{L, U, x};
    }

    public static Object[] crout(double[][] A) {
        int n = A.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                double suma = A[i][j];
                for (int numero = 0; numero < i; numero++) {
                    suma -= L[i][numero] * U[numero][j];
                }
                L[j][i] = suma;
            }
            for (int l = i + 1; l < n; l++) {
                double u = A[i][l];
                for (int numero = 0; numero < i; numero++) {
                    u -= L[i][numero] * U[numero][l];
                }
                U[i][l] = u / L[i][i];
            }
        }
        return new Object[]{L, U};
    }

    public static Object[] croutSolver(double[][] A, double[] b) {
        if(MatrizUtilities.contieneZero(A)){
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz contiene almenos un cero en la diagonal.");
            return new Object[]{A, b};
        }
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        if (luDecomposition.getDeterminant() == 0) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz tiene determinante 0 y no se puede factorizar");
            return new Object[]{A, b};
        }
        Object LU[] = crout(A);
        double L[][] = (double[][]) LU[0];
        double U[][] = (double[][]) LU[1];
        double[][] Lb = MatrizUtilities.aumentar(L, b);
        double z[] = sustitucionProgresiva(Lb, L.length);
        double[][] Uz = MatrizUtilities.aumentar(U, z);
        double x[] = sustitucionRegresiva(Uz, z.length);
        singletonMensaje.setMensajeActual("Sistema solucionado");
        return new Object[]{L, U, x};
    }

    public static Object[] doolitle(double[][] A) {
        int n = A.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                double suma = A[i][j];
                for (int numero = 0; numero < j; numero++) {
                    suma -= L[i][numero] * U[numero][j];
                }
                L[j][i] = suma / U[j][j];
            }
            for (int l = i + 1; l < n; l++) {
                double u = A[i][l];
                for (int numero = 0; numero < i; numero++) {
                    u -= L[i][numero] * U[numero][l];
                }
                U[i][l] = u;
            }
        }
        return new Object[]{L, U};
    }

    public static Object[] doolittleSolver(double[][] A, double[] b) {
        if(MatrizUtilities.contieneZero(A)){
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz contiene almenos un cero en la diagonal.");
            return new Object[]{A, b};
        }
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        LUDecomposition luDecomposition = new LUDecomposition(realMatrix);
        if (luDecomposition.getDeterminant() == 0) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz tiene determinante 0 y no se puede factorizar");
            return new Object[]{A, b};
        }
        Object LU[] = doolitle(A);
        double L[][] = (double[][]) LU[0];
        double U[][] = (double[][]) LU[1];
        double[][] Lb = MatrizUtilities.aumentar(L, b);
        double z[] = sustitucionProgresiva(Lb, L.length);
        double[][] Uz = MatrizUtilities.aumentar(U, z);
        double x[] = sustitucionRegresiva(Uz, z.length);
        singletonMensaje.setMensajeActual("Sistema solucionado");
        return new Object[]{L, U, x};
    }


    public static ArrayList<String[]> gaussSeidel(double[][] A, double[] b, double tol, double[] x0, int niter) {
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        if(MatrizUtilities.contieneZero(A)){
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz contiene almenos un cero en la diagonal.");
            return null;
        }
        ArrayList<String[]> solucion = new ArrayList<>();
        int cnt = 1;
        double dispersion = tol + 1;
        // cnt, x_1, x_2, ..., x_i, ..., x_n
        ArrayList<String> ss = new ArrayList<>();
        ss.add("0");
        MatrizUtilities.adicionar(ss, x0);
        solucion.add(ss.toArray(new String[]{}));
        while (dispersion > tol && cnt < niter) {
            ss.clear();
            double[] x1 = calcularNuevoSeidel(A, b, x0);
            if(singletonMensaje.getError()){
                return null;
            }
            ss.add(cnt + "");
            MatrizUtilities.adicionar(ss, x1);
            dispersion = norma_cuadrada(x1, x0);
            ss.add(dispersion + "");
            x0 = x1;
            solucion.add(ss.toArray(new String[]{}));
            cnt++;
        }
        singletonMensaje.setMensajeActual("Sistema solucionado correctamente.");
        return solucion;
    }

    public static ArrayList<String[]> jacobi(double[][] A, double[] b, double tol, double[] x0, int niter) {
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        if(MatrizUtilities.contieneZero(A)){
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz contiene almenos un cero en la diagonal.");
            return null;
        }
        ArrayList<String[]> solucion = new ArrayList<>();
        RealMatrix realMatrix = new Array2DRowRealMatrix(A);
        DiagonalMatrix D = new DiagonalMatrix(MatrizUtilities.diag(realMatrix.getData()));
        int cnt = 1;
        double dispersion = tol + 1;
        // cnt, x_1, x_2, ..., x_i, ..., x_n
        ArrayList<String> ss = new ArrayList<>();
        ss.add("0");
        MatrizUtilities.adicionar(ss, x0);
        solucion.add(ss.toArray(new String[]{}));
        while (dispersion > tol && cnt < niter) {
            ss.clear();
            //MatrizUtilities.imprimir(x0, true);
            double[] x1 = calcularNuevoJacobi(A, b, x0);
            if(singletonMensaje.getError()){
                return null;
            }
            ss.add(cnt + "");
            MatrizUtilities.adicionar(ss, x1);
            dispersion = norma_cuadrada(x1, x0);
            ss.add(dispersion + "");
            x0 = x1;
            solucion.add(ss.toArray(new String[]{}));
            cnt++;
        }
        singletonMensaje.setMensajeActual("Sistema solucionado correctamente.");
        return solucion;
    }


    private static double norma_cuadrada(double[] x1, double[] x0) {
        double sum1 = 0.0, sum2 = 0.0;
        for (int i = 0; i < x1.length; ++i) {
            sum1 += Math.pow(x1[i] - x0[i], 2);
            sum2 += Math.pow(x1[i], 2);
        }
        return Math.sqrt(sum1) / Math.sqrt(sum2);
    }

    private static double[] calcularNuevoSeidel(double[][] A, double[] b, double[] x0) {
        int n = x0.length;
        double[] x = new double[n];
        for (int i = 0; i < n; ++i) {
            x[i] = x0[i];
        }
        for (int i = 0; i < n; i++) {
            double suma = 0.0;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                suma += A[i][j] * x[j];
            }

            if(A[i][i] == 0){
                singletonMensaje.setMensajeActual("Se ha encontrado una división por cero durante el proceso.");
                singletonMensaje.setError(true);
                return null;
            }
            x[i] = (b[i] - suma) / A[i][i];
        }
        return x;
    }

    private static double[] calcularNuevoJacobi(double[][] A, double[] b, double[] x0) {
        int n = x0.length;
        double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            double suma = 0.0;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                suma += A[i][j] * x0[j];
            }
            if(A[i][i] == 0){
                singletonMensaje.setMensajeActual("Se ha encontrado una división por cero durante el proceso.");
                singletonMensaje.setError(true);
                return null;
            }
            x[i] = (b[i] - suma) / A[i][i];
        }
        return x;
    }

    public static ArrayList<String[]> SOR(double[][] A, double[] b, double tol, double[] x0, double w, int niter) {
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        if(MatrizUtilities.contieneZero(A)){
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("La matriz contiene almenos un cero en la diagonal.");
            return null;
        }
        ArrayList<String[]> solucion = new ArrayList<>();
        int cnt = 1;
        double dispersion = tol + 1;
        // cnt, x_1, x_2, ..., x_i, ..., x_n
        ArrayList<String> ss = new ArrayList<>();
        ss.add("0");
        MatrizUtilities.adicionar(ss, x0);
        solucion.add(ss.toArray(new String[]{}));
        while (dispersion > tol && cnt < niter) {
            ss.clear();
            double[] x1 = calcularNuevoSeidelSOR(A, b, x0, w);
            if(singletonMensaje.getError()){
                return null;
            }
            ss.add(cnt + "");
            MatrizUtilities.adicionar(ss, x1);
            dispersion = norma_maximo(x1, x0);
            ss.add(dispersion + "");
            x0 = x1;
            solucion.add(ss.toArray(new String[]{}));
            cnt++;
        }
        singletonMensaje.setMensajeActual("Sistema solucionado correctamente.");
        return solucion;
    }

    private static double[] calcularNuevoSeidelSOR(double[][] A, double[] b, double[] x0, double w) {
        int n = x0.length;
        double[] x = new double[n];
        for (int i = 0; i < n; ++i) {
            x[i] = x0[i];
        }
        for (int i = 0; i < n; i++) {
            double suma = 0.0;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                suma += A[i][j] * x[j];
            }
            if(A[i][i] == 0){
                singletonMensaje.setMensajeActual("Se ha encontrado una división por cero durante el proceso.");
                singletonMensaje.setError(true);
                return null;
            }
            x[i] = (1. - w) * x[i] + w * (b[i] - suma) / A[i][i];
        }
        return x;
    }

    private static double norma_maximo(double[] x1, double[] x0) {
        double mx1 = 0.0, mx2 = 0.0;
        for (int i = 0; i < x1.length; ++i) {
            mx1 = Math.max(Math.abs(x1[i] - x0[i]), mx1);
            mx2 = Math.max(Math.abs(x1[i]), mx2);
        }
        return mx1 / mx2;
    }
}
