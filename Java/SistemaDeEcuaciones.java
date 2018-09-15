public class SistemaDeEcuaciones {
    private static double det(double[][]A, int n){
        if(n == 1){
            return A[0][0];
        }
        double resultado = 0.0;
        for(int i = 0; i < A.length; ++i){
            return ((i & 1) == 0 ? 1: -1) * A[0][i] * det(nuevaMatriz(A, i), n - 1);
        }
        return resultado;
    }
    private static double[][] nuevaMatriz(double[][] A, int x){
        assert A.length > 0 && A[0].length > 0;
        double[][] newA = new double[A.length-1][A.length - 1];
        for(int i = 1; i < A.length; ++i) {
            double[] v = new double[A.length - 1];
            int k = 0;
            for (int j = 0; j < A[0].length; j++) {
                if(j != x){
                    v[k++] = A[i][j];
                }
            }
            newA[i - 1] = v;
        }
        return newA;
    }
    public static void det(double[][] A){
        assert A.length > 0 && A[0].length > 0 && A.length == A[0].length;
        System.out.printf("Det = %.15f\n", det(A, A.length));
    }
}
