import org.sk.PrettyTable;

import java.io.PrintWriter;

public class OptimizacionUnaVariable {
    public static void muller(double x0, double x1, double x2, double tolerancia, int niter){
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("muller", "i", "x0", "fx", "error_abs", "error_rel");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        double h1 = x1 - x0;
        double h2 = x2 - x1;
        double y1 = (Utilities.f(x1)[0] - Utilities.f(x0)[0])/h1;
        double y2 = (Utilities.f(x2)[0] - Utilities.f(x1)[0])/h2;
        double d = (y2 - y1) / (h2 + h1);
        int contador = 1;
        double error = tolerancia + 1;
        double p = Double.MIN_VALUE;
        tabla.addRow(Utilities.obtenerFila(contador, p, "", ""));
        while(contador < niter && error > tolerancia){
            double b = y2 + (h2 * d);
            double D = Math.sqrt(Math.pow(b, 2)- 4 * Utilities.f(x2)[0]);
            double E = Double.MIN_VALUE;
            if(Math.abs(b - D) < Math.abs(b + d)){
                E = b + d;
            }else{
                E = b - D;
            }
            double h = -2*(Utilities.f(x2)[0]/E);
            p = x2 + h;
            x0 = x1;
            x1 = x2;
            x2 = p;
            h1 = x1 - x0;
            h2 = x2 - x1;
            y1 = (Utilities.f(x1)[0] - Utilities.f(x0)[0])/h1;
            y2 = (Utilities.f(x2)[0] - Utilities.f(x1)[0])/h2;
            d = ((y2 - y1) / (h2 + h2));
            error = Math.abs(h);
            contador ++;
            tabla.addRow(Utilities.obtenerFila(contador, p, Utilities.f(p)[0], error));
        }
        if(error <= tolerancia){
            System.out.printf("Hay raiz en %.15f\n", p);
        }else{
            System.out.printf("Fracaso en %d iteracioens\n", niter);
        }
        writer.println(tabla.toString());
        writer.close();
    }
    public static void steffensen(double x0, int niter, double tolerancia) {
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("steffensen", "i", "x0", "x1", "x2","p", "fp", "error_abs");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        int contador = 0;
        double error = tolerancia + 1;
        double x1 = Utilities.g(x0);
        double x2 = Utilities.g(x1);
        double p = x0 - (Math.pow(x1 - x0, 2) / (x2 - 2 * x1 + x0));
        tabla.addRow(Utilities.obtenerFila(contador, x0, x1, x2, p, Utilities.f(p)[0], "No existe"));
        x0 = p;
        while (contador < niter && error > tolerancia) {
            x1 = Utilities.g(x0);
            x2 = Utilities.g(x1);
            p = x0 - (Math.pow(x1 - x0, 2) / (x2 - 2 * x1 + x0));
            error = Math.abs(p - x0);
            contador++;
            tabla.addRow(Utilities.obtenerFila(contador, x0, x1, x2, p, Utilities.f(p)[0], error));
            x0 = p;
        }
        if (error <= tolerancia) {
            System.out.printf("Hay una raiz en %.15f\n", p);
        } else {
            System.out.printf("El metodo falla despues de %d iteraciones", niter);
        }
        writer.println(tabla.toString());
        writer.close();
    }
}
