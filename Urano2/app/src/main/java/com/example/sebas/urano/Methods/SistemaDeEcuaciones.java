package com.example.sebas.urano.Methods;

import com.example.sebas.urano.Math.MatrizUtilities;

import org.apache.commons.math3.complex.Complex;

public class SistemaDeEcuaciones {
    public static double[] eliminacionGaussianaSimple(double[][] A, double[] b) {
        double[][] Ub = eliminacionSimple(A, b);
        double[] x = sustitucionRegresiva(Ub, A.length);
        return x;
    }

    public static double[] eliminacionGaussianaParcial(double[][] A, double[] b) {
        double[][] Ub = eliminacionGaussianaPivoteoParcial(A, b);
        double[] x = sustitucionRegresiva(Ub, A.length);
        return x;
    }

    public static double[] eliminacionGaussianaTotal(double[][] A, double[] b) {
        int[] marcas = MatrizUtilities.organizar(A.length);
        double[][] Ub = eliminacionGaussianaPivoteoTotal(A, marcas, b);
        for (int i = 0; i < marcas.length; ++i) System.out.print(marcas[i] + " ");
        double[] xp = sustitucionRegresiva(Ub, A.length);
        double[] x = new double[xp.length];
        for (int i = 0; i < A.length; ++i) {
            x[i] = xp[marcas[i]];
        }
        return xp;
    }

    public static double[] eliminacionGaussianaSimpleLU(double[][] A, double[] b) {
        Object[] obj = factorizacionLU_GaussSimple(A);
        double[][] L = (double[][]) obj[0];
        double[][] U = (double[][]) obj[1];
        double[][] Lb = MatrizUtilities.aumentar(L, b);
        double[] z = sustitucionProgresiva(Lb, L.length);
        double[][] Uz = MatrizUtilities.aumentar(U, z);
        double[] x = sustitucionRegresiva(Uz, U.length);
        return x;
    }

    public static double[] eliminacionGaussianaParcialLU(double[][] A, double[] b) {
        Object[] obj = factorizacionLU_GaussSimple(A);
        double[][] L = (double[][]) obj[0];
        double[][] U = (double[][]) obj[1];
        double[][] P = (double[][]) obj[2];
        double[] bn = MatrizUtilities.productoMatricial(P, b);
        double[][] Lb = MatrizUtilities.aumentar(L, bn);
        double[] z = sustitucionProgresiva(Lb, L.length);
        double[][] Uz = MatrizUtilities.aumentar(U, z);
        double[] x = sustitucionRegresiva(Uz, U.length);
        return x;
    }

    public static double[][] eliminacionGaussianaPivoteoParcial(double[][] A, double[] b) {
        int n = A.length;
        double[][] Ab = MatrizUtilities.aumentar(A, b);
        for (int k = 0; k < n - 1; ++k) {
            Ab = pivoteoParcial(Ab, n, k);
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
        int n = A.length;
        double[][] Ab = MatrizUtilities.aumentar(A, b);
        for (int k = 0; k < n - 1; ++k) {
            Ab = pivoteoTotal(Ab, marcas, n, k);
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
        int n = A.length;
        double[][] Ab = MatrizUtilities.aumentar(A, b);
        for (int k = 0; k < n - 1; ++k) {
            if (Ab[k][k] == 0) {
                double mx = A[k][k];
                int idx = k;
                while (A[idx][k] == 0) idx++;
                MatrizUtilities.intercambiarFilas(Ab, idx, k);
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
        int n = A.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        double[][] Ab = A;
        for (int k = 0; k < n - 1; ++k) {
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
        return new Object[]{L, U};
    }

    public static Object[] factorizacionLU_GaussParcial(double[][] A) {
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

    static class LU_Directo {
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

        public static Object[] choleskyReal(double[][] A) {
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
    }

    public static void main(String[] args) {
        double[][] A = {{1, -1, -1, -1, -1}, {-1, 2, 0, 0, 0}, {-1, 0, 3, 1, 1}, {-1, 0, 1, 4, 2}, {-1, 0, 1, 2, 5}};
        Object[] obj = LU_Directo.choleskyReal(A);
        MatrizUtilities.imprimir((double[][]) obj[0]);
        MatrizUtilities.imprimir((double[][]) obj[1]);
    }
}