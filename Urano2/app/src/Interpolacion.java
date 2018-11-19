package com.example.sebas.urano.Methods;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Interpolacion{
    public static String vandermonde(double[] x,double[] y) throws IOException{
        int n = x.length;
        double[][] A = new double[n][n];
        double[]   b = new double[n];
        for(int i=0 ; i<n ; i++){
            b[i] = y[i];
            for(int j=0; j<n; j++ ){
                A[i][j] = Math.pow(x[i],(n-(1+j)));
            }
        }
        double[] solucionAb = SistemaDeEcuaciones.eliminacionGaussianaTotal(A,b);
        String polinomio = String.valueOf(solucionAb[0]) + "x^" + String.valueOf(n-1);
        for(int i =1; i< solucionAb.length; i++){
            polinomio += solucionAb[i]>=0 ? "+" + String.valueOf(solucionAb[i]) : String.valueOf(solucionAb[i]);
            polinomio += "x^"+String.valueOf(n-(1+i));
        }
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
        return diferenciasDivididas;
    }

    public static String lagrange(double[] x, double[] y) throws IOException{
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