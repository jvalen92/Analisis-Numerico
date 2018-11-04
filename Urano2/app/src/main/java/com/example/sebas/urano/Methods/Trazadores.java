public class Trazadores{
    public static String[] trazadoresLineales(double[] x, double[] y){
        int n = x.length;
        String[] ecuaciones = new String[n];
        double[][] A = new double[2*n][2*n];
        double[] b = new double[2*n];
        for(int i=0; i<n; i++ ){
            int j=2*i;
            A[j][j]     = x[i];
            A[j][j+1]   = 1;
            b[j]        = y[i];
            A[j+1][j]   = x[i+1];
            A[j+1][j+1] = 1;
            b[j+1]      = y[i+1];
        }
        return ecuaciones;
    }
}