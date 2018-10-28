public class Interpolacion{
    public String vandermonde(double[] x,double[] y){
        int n = x.length;
        double[][] A = new double[n][n];
        double[]   b = new double[n];
        for(int i=0 ; i<n ; i++){
            b[i] = y[i];
            for(int j=n-1; j>=0; j-- ){
                A[i][j] = Math.pow(x[i],j);
            }
        }
        System.out.println(A);
        System.out.print(b);
        //Falta encontrar el vector A
        String polinomio = "";
        return polinomio;
    }

    public static double[][] diferenciasDivididas(double[] x, double[] y ){
        int n = x.length;
        double[][] diferenciasDivididas = new double[n][n];
        for (int i=0; i<n; i++){
            diferenciasDivididas[i][0] = y[i];
        }
        for (int i=1; i<n; i++){
            for(int j=i; j<n; j++){
                diferenciasDivididas[j][i]= (diferenciasDivididas[j][i-1]-diferenciasDivididas[j-1][i-1])/(x[j]-x[j-i]);
            }
        }
        return diferenciasDivididas;
    }

    public static String lagrange(double[] x, double[] y){
        int n = x.length;
        String termino = "";
        for(int i=0; i<n; i++){
            double divisor= 1;
            String dividendo = "";
            for(int j=0; j<n; j++){
                if(j != i){
                    divisor = divisor*(x[i]-x[j]);
                    dividendo += "(x - " + String.valueOf(x[j]) + ")";
                }
            }
            divisor = y[i]/divisor;
            termino += i!=0 && divisor > 0 ? " + ":"";
            termino += divisor + dividendo; 
        }
        return termino;
    }
}