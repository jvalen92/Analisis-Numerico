import org.sk.PrettyTable;
import java.io.PrintWriter;

public class UnaVariable {

    public static void newton(double tolerancia, double x0, int niter) {
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("newton", "i", "x0", "fx", "error_abs", "error_rel");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        int contador = 0;
        double error = tolerancia + 1.0;
        double error_rel = (tolerancia + 1.0) / tolerancia;
        double fx = Utilities.f(x0)[0];
        double dfx = Utilities.f(x0)[1];
        tabla.addRow(Utilities.obtenerFila(contador, x0, fx, "No existe", "No existe"));
        while (fx != 0.0 && dfx != 0.0 && error > tolerancia && contador < niter) {
            double x1 = x0 - (fx / dfx);
            fx = Utilities.f(x1)[0];
            dfx = Utilities.f(x1)[1];
            error = Math.abs(x1 - x0);
            error_rel = Math.abs(error / x1);
            x0 = x1;
            contador++;
            tabla.addRow(Utilities.obtenerFila(contador, x0, fx, error, error_rel));
        }
        if (fx == 0) {
            System.out.printf("%.15f es raiz\n", x0);
        } else if (error < tolerancia) {
            System.out.printf("%.15f es una aproximacion con tolerancia=%.15f\n", x0, tolerancia);
        } else {
            System.out.printf("El metodo fracaso en %d iteraciones", niter);
        }
        writer.println(tabla.toString());
        writer.close();
    }
    public static void puntoFijo(double tolerancia, double xa, int niter) {
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("punto_fijo", "i", "xn", "fx", "error_abs", "error_rel");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        int contador = 0;
        double error = tolerancia + 1.0;
        double error_rel = (tolerancia + 1.0) / tolerancia;
        double fx = Utilities.f(xa)[0];
        tabla.addRow(Utilities.obtenerFila(contador, xa, fx, "No existe", "No existe"));
        while (fx != 0 && error > tolerancia && contador < niter) {
            double xn = Utilities.g(xa);
            fx = Utilities.f(xn)[0];
            error = Math.abs(xn - xa);
            error_rel = Math.abs(error / xn);
            xa = xn;
            contador++;
            tabla.addRow(Utilities.obtenerFila(contador, xa, fx, error, error_rel));
        }
        if (fx == 0) {
            System.out.printf("%.15f es raiz\n", xa);
        } else if (error < tolerancia) {
            System.out.printf("%.15f es una aproximacion con tolerancia=%.15f\n", xa, tolerancia);
        } else {
            System.out.printf("El metodo fracaso en % iteraciones\n", niter);
        }
        writer.println(tabla.toString());
        writer.close();
    }

    public static void biseccion(double xi, double xs, double tolerancia, int niter) {
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("biseccion", "i", "xi", "xs", "xm", "fxm", "error_abs", "error_rel");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        double fxi = Utilities.f(xi)[0];
        double fxs = Utilities.f(xs)[0];
        if (fxi == 0 || fxs == 0) {
            System.out.printf("Hay raiz en %.15f\n", (fxi == 0 ? fxi : fxs));
        } else if (fxi * fxs < 0) {
            double xm = (xi + xs) / 2;
            double fxm = Utilities.f(xm)[0];
            int contador = 1;
            tabla.addRow(Utilities.obtenerFila(contador, xi, xs, xm, fxm, "No existe", "No existe"));
            double error = tolerancia + 1;
            while (error > tolerancia && fxm != 0 && contador < niter) {
                if (fxi * fxm < 0) {
                    xs = xm;
                    fxs = fxm;
                } else {
                    xi = xm;
                    fxi = fxm;
                }
                double xaux = xm;
                xm = (xi + xs) / 2;
                fxm = Utilities.f(xm)[0];
                error = Math.abs(xm - xaux);
                double error_rel = Math.abs(error / xm);
                contador++;
                tabla.addRow(Utilities.obtenerFila(contador, xi, xs, xm, fxm, error, error_rel));
            }
            if (fxm == 0) {
                System.out.printf("Raiz en %.15f\n", xm);
            } else if (error < tolerancia) {
                System.out.printf(" %.15f es una aproximacion a una raiz con una tolerancia = %.15f\n", xm, tolerancia);
            } else {
                System.out.printf("Fracaso en %d iteraciones", niter);
            }
        } else {
            System.out.println("El intervalo es inadecuado");
        }
        writer.println(tabla.toString());
        writer.close();
    }

    public static void busquedaIncremental(double x0, double delta, int niter) {
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("busqueda_incremental", "i", "xi", "fx");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        double fx0 = Utilities.f(x0)[0];
        if (fx0 == 0) {
            System.out.printf("Hay raiz en %.15f\n", x0);
        } else {
            tabla.addRow(Utilities.obtenerFila(0, x0, fx0));
            double x1 = x0 + delta;
            int contador = 1;
            double fx1 = Utilities.f(x1)[0];
            tabla.addRow(Utilities.obtenerFila(contador, x1, fx1));
            while (fx0 * fx1 > 0 && contador < niter) {
                x0 = x1;
                fx0 = fx1;
                x1 = x0 + delta;
                fx1 = Utilities.f(x1)[0];
                contador++;
                tabla.addRow(Utilities.obtenerFila(contador, x1, fx1));
            }
            if (fx1 == 0) {
                System.out.printf("Hay raiz en %.15f\n", x0);
            } else if (fx0 * fx1 < 0) {
                System.out.printf("Hay raiz en (%.15f,%.15f)\n", x0, x1);
            } else {
                System.out.printf("Fracaso en %d iteraciones", niter);
            }
        }
        writer.println(tabla.toString());
        writer.close();
    }

    public static void raicesMultiples(double x0, double tolerancia, int niter) {
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("raices_multiples", "i", "xn", "fx", "dfx","ddfx" ,"error_abs", "error_rel");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        double fx = Utilities.h(x0)[0];
        double dfx = Utilities.h(x0)[1];
        double ddfx = Utilities.h(x0)[2];
        double xn = x0;
        int contador = 0;
        double error = tolerancia + 1.0;
        tabla.addRow(Utilities.obtenerFila(contador, xn, fx, dfx, ddfx, "No existe", "No existe"));
        while (contador < niter && error > tolerancia) {
            double xnn = (fx * dfx) / (Math.pow(dfx, 2) - fx * ddfx);
            error = Math.abs(xn - xnn);
            xn = xnn;
            fx = Utilities.h(xn)[0];
            dfx = Utilities.h(xn)[1];
            ddfx = Utilities.h(xn)[2];
            double error_rel = Math.abs(error / xn);
            contador++;
            tabla.addRow(Utilities.obtenerFila(contador, xn, fx, dfx, ddfx, error, error_rel));
        }
        writer.println(tabla.toString());
        writer.close();
    }

    public static void reglaFalsa(double xi, double xs, double tolerancia, int niter) {
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("regla_false", "i", "xi", "xs", "xm", "fxm", "error_abs", "error_rel");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        double fxi = Utilities.f(xi)[0];
        double fxs = Utilities.f(xs)[0];
        if (fxi == 0 || fxs == 0) {
            System.out.printf("Hay raiz en %.15f\n", (fxi == 0 ? fxi : fxs));
        } else if (fxi * fxs < 0) {
            double xm = (xi + xs) / 2;
            double fxm = Utilities.f(xm)[0];
            int contador = 1;
            tabla.addRow(Utilities.obtenerFila(contador, xi, xs, xm, fxm, "No existe", "No existe"));
            double error = tolerancia + 1;
            while (error > tolerancia && fxm != 0 && contador < niter) {
                if (fxi * fxm < 0) {
                    xs = xm;
                    fxs = fxm;
                } else {
                    xi = xm;
                    fxi = fxm;
                }
                double xaux = xm;
                xm = xi - ((fxi * (xs - xi))) / (fxs - fxi);
                fxm = Utilities.f(xm)[0];
                error = Math.abs(xm - xaux);
                double error_rel = Math.abs(error / xm);
                contador++;
                tabla.addRow(Utilities.obtenerFila(contador, xi, xs, xm, fxm, error, error_rel));
            }
            if (fxm == 0) {
                System.out.printf("Raiz en %.15f\n", xm);
            } else if (error < tolerancia) {
                System.out.printf(" %.15f es una aproximacion a una raiz con una tolerancia = %.15f\n", xm, tolerancia);
            } else {
                System.out.printf("Fracaso en %d iteraciones", niter);
            }
        } else {
            System.out.println("El intervalo es inadecuado");
        }
        writer.println(tabla.toString());
        writer.close();
    }

    public static void secante(double tolerancia, double x0, double x1, int niter) {
        Utilities.Par<PrettyTable, PrintWriter> par = Utilities.iniciarArchivo("secante", "i", "x0", "fx0", "error_abs", "error_rel");
        PrintWriter writer = par.getSegundo();
        PrettyTable tabla = par.getPrimero();
        double fx0 = Utilities.f(x0)[0];
        if (fx0 == 0) {
            System.out.printf("%.15f es raiz\n", x0);
        } else {
            double fx1 = Utilities.f(x1)[0];
            int contador = 0;
            double error = tolerancia + 1;
            double error_rel = tolerancia + 1;
            double den = fx1 - fx0;
            tabla.addRow(Utilities.obtenerFila(contador, x0, fx0, "No existe", "No existe"));
            tabla.addRow(Utilities.obtenerFila(contador, x1, fx1, "No existe", "No existe"));
            while (error > tolerancia && fx1 != 0 && den != 0 && contador < niter) {
                double x2 = x1 - fx1 * (x1 - x0) / den;
                error = Math.abs(x2 - x1);
                error_rel = Math.abs(error / x2);
                x0 = x1;
                fx0 = fx1;
                x1 = x2;
                fx1 = Utilities.f(x1)[0];
                den = fx1 - fx0;
                contador++;
                tabla.addRow(Utilities.obtenerFila(contador, x1, fx1, error, error_rel));
            }
            if (fx1 == 0) {
                System.out.printf("%.15f es raiz\n", x1);
            } else if (error < tolerancia) {
                System.out.printf("%.15f es una aproximacion  con una tolerancia = %.15f\n", x1, tolerancia);
            } else if (den == 0) {
                System.out.println("Hay una posible raiz multiple");
            } else {
                System.out.printf("El metodo fracaso en %d iteraciones\n", niter);
            }
        }
        writer.println(tabla.toString());
        writer.close();
    }
}
