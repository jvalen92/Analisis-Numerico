import java.io.IOException;
public class Main{
    public static void main(String[] args) {
        double[] x = {1.0000, 2.0000, 3.0000, 4.0000, 5.0000, 6.0000, 7.0000, 8.0000, 9.0000, 10.0000 };
        double[] y = {0.5949, 0.2622, 0.6028, 0.7112, 0.2217, 0.1174, 0.2967, 0.3188, 0.4242, 0.5079};
        try{
            String[][] trazadores1 = Trazadores.trazadoresLineales(x, y);
            String[][] trazadores2 = Trazadores.trazadoresCuadraticos(x, y);
            String[][] trazadores3 = Trazadores.trazadoresCubicos(x, y);
            String vandermonde     = Interpolacion.vandermonde(x,y);
            double[][] diferencias = Interpolacion.diferenciasDivididas(x, y);
            String lagrange        = Interpolacion.lagrange(x, y);
        } 
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}