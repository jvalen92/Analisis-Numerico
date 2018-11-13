package com.example.sebas.urano.Methods;

import com.example.sebas.urano.Math.NumericalUtilities;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UnaVariable {

    /**
     * Devuelve una tabla con la ejecucon del metodo de newton: los campos son iter, xi, f(x), df(x), error_abs, error_rel
     *
     * @param f
     * @param df
     * @param tolerancia
     * @param x0
     * @param niter
     * @return
     */
    public static ArrayList<String[]> newton(String f, String df, double tolerancia, double x0, int niter) {
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        ArrayList<String[]> solucion = new ArrayList<>();
        int contador = 0;
        double error = tolerancia + 1.0;
        double error_rel = Double.MIN_VALUE;
        double fx = NumericalUtilities.evaluarFuncion(f, x0);
        double dfx = NumericalUtilities.evaluarFuncion(f, x0);
        double x1 = x0;
        solucion.add(new String[]{contador + "", x0 + "", fx + "", dfx + "", "No existe", "No existe"});
        while (fx != 0.0 && dfx != 0.0 && error > tolerancia && contador < niter) {
            x1 = x0 - (fx / dfx);
            fx = NumericalUtilities.evaluarFuncion(f, x1);
            error = Math.abs(x1 - x0);
            error_rel = Math.abs(error / x1);
            x0 = x1;
            contador++;
            double px0 = NumericalUtilities.fd(x0, 4);
            double pfx = NumericalUtilities.fd(fx, 4);
            double pdfx = NumericalUtilities.fd(dfx, 4);
            double ea = NumericalUtilities.fe(error);
            double er = NumericalUtilities.fe(error_rel);
            solucion.add(new String[]{contador + "", px0 + "", pfx + "", pdfx + "", ea + "", er + ""});
        }
        String mensaje = "";
        if (fx == 0) {
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", x0, contador);
        } else if (error < tolerancia) {
            mensaje = NumericalUtilities.format("%.4f es aproximación a una raíz con una tolerancia de %.4f.", x1, tolerancia);
        } else if (dfx == 0) {
            mensaje = NumericalUtilities.format("%.4f es una posible raíz múltiple.");
        } else {
            mensaje = NumericalUtilities.format("El método fracasó después de %d iteraciones.", contador);
        }
        singletonMensaje.setMensajeActual(mensaje);
        return solucion;
    }

    public static ArrayList<String[]> puntoFijo(String f, String g, double tolerancia, double xa, int niter) {
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        ArrayList<String[]> solucion = new ArrayList<>();
        int contador = 0;
        double error = tolerancia + 1.0;
        double error_rel = (tolerancia + 1.0) / tolerancia;
        double fx = NumericalUtilities.evaluarFuncion(f, xa);
        double xn = xa;
        solucion.add(new String[]{contador + "", xa + "", fx + "", "No existe", "No existe"});
        while (fx != 0 && error > tolerancia && contador < niter) {
            xn = NumericalUtilities.evaluarFuncion(g, xa);
            fx = NumericalUtilities.evaluarFuncion(f, xn);
            error = Math.abs(xn - xa);
            error_rel = Math.abs(error / xn);
            xa = xn;
            contador++;
            double pxa = NumericalUtilities.fd(xa, 4);
            double pfx = NumericalUtilities.fd(fx, 4);
            double ea = NumericalUtilities.fe(error);
            double er = NumericalUtilities.fe(error_rel);
            solucion.add(new String[]{contador + "", pxa + "", pfx + "", ea + "", er + ""});
        }
        String mensaje = "";
        if (fx == 0) {
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", xa, contador);
        } else if (error < tolerancia) {
            mensaje = NumericalUtilities.format("%.4f es aproximación a una raíz con una tolerancia de %.4f.", xa, tolerancia);
        } else {
            mensaje = NumericalUtilities.format("El método fracasó después de %d iteraciones.", contador);
        }
        singletonMensaje.setMensajeActual(mensaje);
        return solucion;
    }

    public static ArrayList<String[]> biseccion(String f, double xi, double xs, double tolerancia, int niter) {
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        ArrayList<String[]> solucion = new ArrayList<>();
        double fxi = NumericalUtilities.evaluarFuncion(f, xi);
        double fxs = NumericalUtilities.evaluarFuncion(f, xs);
        String mensaje = "";
        if (fxi == 0) {
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", xi, 0);
            solucion.add(new String[]{"0", xi + "", xs + "", xi + "", fxi + "", "No existe", "No existe"});
        } else if (fxs == 0) {
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", xs, 0);
            solucion.add(new String[]{"0", xi + "", xs + "", xs + "", fxs + "", "No existe", "No existe"});
        } else if (fxi * fxs < 0) {
            double xm = (xi + xs) / 2;
            double fxm = NumericalUtilities.evaluarFuncion(f, xm);
            int contador = 1;
            solucion.add(new String[]{contador + "", xi + "", xs + "", xm + "", fxm + "", "No existe", "No existe"});
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
                fxm = NumericalUtilities.evaluarFuncion(f, xm);
                error = Math.abs(xm - xaux);
                double error_rel = Math.abs(error / xm);
                contador++;
                double pxi = NumericalUtilities.fd(xi, 4);
                double pxs = NumericalUtilities.fd(xs, 4);
                double pxm = NumericalUtilities.fd(xm, 4);
                double pfxm = NumericalUtilities.fd(fxm, 4);
                double ea = NumericalUtilities.fe(error);
                double er = NumericalUtilities.fe(error_rel);
                solucion.add(new String[]{contador + "", pxi + "", pxs + "", pxm + "", pfxm + "", ea + "", er + ""});
            }
            if (fxm == 0) {
                mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", xi, contador);
            } else if (error < tolerancia) {
                mensaje = NumericalUtilities.format("%.4f es aproximación a una raíz con una tolerancia de %.4f.", xm, tolerancia);
            } else {
                mensaje = NumericalUtilities.format("El método fracasó después de %d iteraciones.", contador);
            }
        } else {
            mensaje = NumericalUtilities.format("El intervalo [%.2f, %.2f] es inadecuado.", xi, xs);
            singletonMensaje.setError(true);
        }
        singletonMensaje.setMensajeActual(mensaje);
        return solucion;
    }

    public static ArrayList<String[]> busquedaIncremental(String f, double x0, double delta, int niter) {
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setError(false);
        singletonMensaje.setMensajeActual("");
        ArrayList<String[]> solucion = new ArrayList<>();
        double fx0 = NumericalUtilities.evaluarFuncion(f, x0);
        String mensaje = "";
        if (fx0 == 0) {
            solucion.add(new String[]{"0", x0 + "", fx0 + ""});
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", x0, 0);
        } else {
            solucion.add(new String[]{"0", x0 + "", fx0 + ""});
            double x1 = x0 + delta;
            int contador = 1;
            double fx1 = NumericalUtilities.evaluarFuncion(f, x1);
            solucion.add(new String[]{contador + "", x1 + "", fx1 + ""});
            while (fx0 * fx1 > 0 && contador < niter) {
                x0 = x1;
                fx0 = fx1;
                x1 = x0 + delta;
                fx1 = NumericalUtilities.evaluarFuncion(f, x1);
                contador++;
                double px1 = NumericalUtilities.fd(x1, 4);
                double pfx1 = NumericalUtilities.fd(fx1, 4);
                solucion.add(new String[]{contador + "", px1 + "", pfx1 + ""});
            }
            if (fx1 == 0) {
                mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", x0, contador);
            } else if (fx0 * fx1 < 0) {
                mensaje = NumericalUtilities.format("Hay una raíz el el intervalo [%.2f, %.2f].", x0, x1);
            } else {
                mensaje = NumericalUtilities.format("El método fracasó después de %d iteraciones.", contador);
            }
            singletonMensaje.setMensajeActual(mensaje);
        }
        return solucion;
    }

    public static ArrayList<String[]> raicesMultiples(String f, String df, String ddf, double x0, double tolerancia, int niter) {
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        ArrayList<String[]> solucion = new ArrayList<>();
        double fx = NumericalUtilities.evaluarFuncion(f, x0);
        double dfx;
        double ddfx;
        dfx = NumericalUtilities.evaluarFuncion(df, x0);
        ddfx = NumericalUtilities.evaluarFuncion(ddf, x0);
        double xn = x0;
        int contador = 0;
        double error = tolerancia + 1.0;
        solucion.add(new String[]{contador + "", xn + "", fx + "", dfx + "", ddfx + "", "No existe", "No existe"});
        while (contador < niter && error > tolerancia && fx != 0.0) {
            double xnn = xn - ((fx * dfx) / (Math.pow(dfx, 2) - fx * ddfx));
            error = Math.abs(xn - xnn);
            xn = xnn;
            fx = NumericalUtilities.evaluarFuncion(f, xn);
            dfx = NumericalUtilities.evaluarFuncion(df, xn);
            ddfx = NumericalUtilities.evaluarFuncion(ddf, xn);
            double error_rel = Math.abs(error / xn);
            contador++;
            double pxn = NumericalUtilities.fd(xn, 4);
            double pfx = NumericalUtilities.fd(fx, 4);
            double pdfx = NumericalUtilities.fd(dfx, 4);
            double pddfx = NumericalUtilities.fd(ddfx, 4);
            double ea = NumericalUtilities.fe(error);
            double er = NumericalUtilities.fe(error_rel);
            solucion.add(new String[]{contador + "", pxn + "", pfx + "", pdfx + "", pddfx + "", ea + "", er + ""});
        }
        String mensaje = "";
        if (fx == 0) {
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", xn, contador);
        } else if (error < tolerancia) {
            mensaje = NumericalUtilities.format("%.4f es aproximación a una raíz con una tolerancia de %.4f.", xn, tolerancia);
        } else {
            mensaje = NumericalUtilities.format("El método fracasó después de %d iteraciones.", contador);
        }
        singletonMensaje.setMensajeActual(mensaje);
        return solucion;
    }

    public static ArrayList<String[]> reglaFalsa(String f, double xi, double xs, double tolerancia, int niter) {
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        ArrayList<String[]> solucion = new ArrayList<>();
        double fxi = NumericalUtilities.evaluarFuncion(f, xi);
        double fxs = NumericalUtilities.evaluarFuncion(f, xs);
        String mensaje = "";
        if (fxi == 0) {
            solucion.add(new String[]{"0", xi + "", xs + "", xi + "", fxi + "", "No existe", "No existe"});
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", xi, 0);
        } else if (fxs == 0) {
            solucion.add(new String[]{"0", xi + "", xs + "", xs + "", fxs + "", "No existe", "No existe"});
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", xs, 0);
        } else if (fxi * fxs < 0) {
            double xm = xi - ((fxi * (xs - xi))) / (fxs - fxi);
            double fxm = NumericalUtilities.evaluarFuncion(f, xm);
            int contador = 1;
            solucion.add(new String[]{contador + "", xi + "", xs + "", xm + "", fxm + "", "No existe", "No existe"});
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
                fxm = NumericalUtilities.evaluarFuncion(f, xm);
                error = Math.abs(xm - xaux);
                double error_rel = Math.abs(error / xm);
                contador++;
                double pxi = NumericalUtilities.fd(xi, 4);
                double pxs = NumericalUtilities.fd(xs, 4);
                double pxm = NumericalUtilities.fd(xm, 4);
                double pfxm = NumericalUtilities.fd(fxm, 4);
                double ea = NumericalUtilities.fe(error);
                double er = NumericalUtilities.fe(error_rel);
                solucion.add(new String[]{contador + "", pxi + "", pxs + "", pxm + "", pfxm + "", ea + "", er + ""});
            }
            if (fxm == 0) {
                mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", xi, contador);
            } else if (error < tolerancia) {
                mensaje = NumericalUtilities.format("%.4f es aproximación a una raíz con una tolerancia de %.4f.", xm, tolerancia);
            } else {
                mensaje = NumericalUtilities.format("El método fracasó después de %d iteraciones.", contador);
            }
        } else {
            mensaje = NumericalUtilities.format("El intervalo [%.2f, %.2f] es inadecuado.", xi, xs);
            singletonMensaje.setError(true);
        }
        singletonMensaje.setMensajeActual(mensaje);
        return solucion;
    }

    public static ArrayList<String[]> secante(String f, double tolerancia, double x0, double x1, int niter) {
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setMensajeActual("");
        singletonMensaje.setError(false);
        ArrayList<String[]> solucion = new ArrayList<>();
        double fx0 = NumericalUtilities.evaluarFuncion(f, x0);
        String mensaje = "";
        if (fx0 == 0) {
            mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", x0, 0);
            solucion.add(new String[]{"0", NumericalUtilities.fd(x0, 4) + "", NumericalUtilities.fd(fx0, 4) + "", "No existe", "No existe"});
        } else {
            double fx1 = NumericalUtilities.evaluarFuncion(f, x1);
            int contador = 0;
            double error = tolerancia + 1;
            double error_rel = tolerancia + 1;
            double den = fx1 - fx0;
            solucion.add(new String[]{"i", x0 + "", fx0 + "", "No existe", "No existe"});
            solucion.add(new String[]{"u", x1 + "", fx1 + "", "No existe", "No existe"});
            while (error > tolerancia && fx1 != 0 && den != 0 && contador < niter) {
                double x2 = x1 - fx1 * (x1 - x0) / den;
                error = Math.abs(x2 - x1);
                error_rel = Math.abs(error / x2);
                x0 = x1;
                fx0 = fx1;
                x1 = x2;
                fx1 = NumericalUtilities.evaluarFuncion(f, x1);
                den = fx1 - fx0;
                contador++;
                solucion.add(new String[]{contador + "", x1 + "", fx1 + "", error + "", error_rel + ""});
            }
            if (fx1 == 0) {
                mensaje = NumericalUtilities.format("%.4f es una raíz encontrada %d iteraciones.", x1, contador);
            } else if (den == 0) {
                mensaje = NumericalUtilities.format("Hay una posible raíz múltiple.");
            } else {
                mensaje = NumericalUtilities.format("El método fracasó después de %d iteraciones.", contador);
            }
        }
        singletonMensaje.setMensajeActual(mensaje);
        return solucion;
    }
}
