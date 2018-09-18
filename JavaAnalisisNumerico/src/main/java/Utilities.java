import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.sk.PrettyTable;

import java.io.IOException;
import java.io.PrintWriter;

public class Utilities {
    private static final int MAX_DERIVATES = 2;
    private static final int MAX_VARIABLES = 1;
    private static final int STARTING_INDEX = 0;
    static class Par<T, K>{
        private T primero;
        private K segundo;
        public Par(T primero, K segundo){
            this.primero = primero;
            this.segundo = segundo;
        }
        public T getPrimero(){
            return primero;
        }
        public K getSegundo() {
            return segundo;
        }
    }
    public static Par<PrettyTable, PrintWriter> iniciarArchivo(String name, String... encabezados) {
        PrintWriter writer = null;
        PrettyTable tabla;
        try {
            writer = new PrintWriter(name + ".txt");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        tabla = new PrettyTable(encabezados);
        return new Par<>(tabla, writer);
    }
    public static String[] obtenerFila(Object... columnas) {
        String[] res = new String[columnas.length];
        int i = 0;
        for (Object o : columnas) {
            res[i++] = "" + o;
        }
        return res;
    }
    public static double [] f(double a){
        DerivativeStructure x = new DerivativeStructure(MAX_VARIABLES, MAX_DERIVATES, STARTING_INDEX, a);
        x = ((x.sin()).pow(2).add(1)).log().subtract(0.5);
        return x.getAllDerivatives();
    }
    public static double g(double a){
        return f(a)[0];
    }
    public static double[] h(double a){
        DerivativeStructure x = new DerivativeStructure(MAX_VARIABLES, MAX_DERIVATES, STARTING_INDEX, a);
        x = (x.exp()).subtract(x).subtract(1);
        return x.getAllDerivatives();
    }
}
