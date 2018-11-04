import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Interpolacion{
    public static String vandermonde(double[] x,double[] y) throws IOException{
        File file = new File("vandermonde.txt");
        FileWriter writer = new FileWriter(file);
        int n = x.length;
        double[][] A = new double[n][n];
        double[]   b = new double[n];
        for(int i=0 ; i<n ; i++){
            b[i] = y[i];
            for(int j=0; j<n; j++ ){
                A[i][j] = Math.pow(x[i],(n-(1+j)));
            }
        }
        writer.write("Matriz vandermonde \n");
        for(double[] a: A){
         for(double ai: a){
            writer.write(ai + " ");
         }   
            writer.write("\n");
        }
        writer.write("vector b\n");
        for (double var : b) {
            writer.write(String.valueOf(var) + " ");
        }
        writer.write("\n");
        double[] solucionAb = SistemasDeEcuaciones.eliminacionGaussiana(A,b);
        String polinomio = "";
        writer.close();
        return polinomio;
    }

    public static double[][] diferenciasDivididas(double[] x, double[] y ) throws IOException{
        int n = x.length;
        double[][] diferenciasDivididas = new double[n][n];
        File file = new File("diferenciasDivididas.txt");
        FileWriter writer = new FileWriter(file);
        for (int i=0; i<n; i++){
            diferenciasDivididas[i][0] = y[i];
        }
        for (int i=1; i<n; i++){
            for(int j=i; j<n; j++){
                diferenciasDivididas[j][i]= (diferenciasDivididas[j][i-1]-diferenciasDivididas[j-1][i-1])/(x[j]-x[j-i]);
            }
        }
        writer.write("Matriz de diferencias divididas \n");
        for(double[] a: diferenciasDivididas){
            for(double ai: a){
                writer.write(ai + " ");
            }   
            writer.write("\n");
           }
           writer.close();
        return diferenciasDivididas;
    }

    public static String lagrange(double[] x, double[] y) throws IOException{
        int n = x.length;
        String termino = "";
        File file = new File("lagrange.txt");
        FileWriter writer = new FileWriter(file);
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
        writer.write("Polinomio de lagrange \n");
        writer.write(termino);
        writer.close();
        return termino;
    }
}