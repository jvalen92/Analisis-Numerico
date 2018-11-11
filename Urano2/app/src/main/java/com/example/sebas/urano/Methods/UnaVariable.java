package com.example.sebas.urano.Methods;
import com.example.sebas.urano.Math.NumericalUtilities;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UnaVariable {

    public static ArrayList<String[]> newton(String f, String df, boolean derivada_de_usuario, double tolerancia, double x0, int niter) {
        ArrayList<String[]> solucion = new ArrayList<>();
        int contador = 0;
        double error = tolerancia + 1.0;
        double error_rel = Double.MIN_VALUE;
        double fx = NumericalUtilities.evaluarFuncion(f, x0);
        double dfx;
        if (derivada_de_usuario) {
            dfx = NumericalUtilities.evaluarFuncion(df, x0);
        } else {
            dfx = NumericalUtilities.evaluarDerivada(f, x0);
        }
        solucion.add(new String[]{contador + "", x0 + "", dfx + "", "No Existe", "No Existe"});
        while (fx != 0.0 && dfx != 0.0 && error > tolerancia && contador < niter) {
            double x1 = x0 - (fx / dfx);
            fx = NumericalUtilities.evaluarFuncion(f, x1);
            if (derivada_de_usuario) {
                dfx = NumericalUtilities.evaluarFuncion(df, x1);
            } else {
                dfx = NumericalUtilities.evaluarDerivada(f, x1);
            }
            DecimalFormat format = new DecimalFormat("0.##E0");

            error = Math.abs(x1 - x0);
            String dec_abs=format.format(error);
            error_rel = Math.abs(error / x1);
            String dec_rel=format.format(error_rel);
            x0 = x1;
            contador++;
            solucion.add(new String[]{contador + "", x0 + "", dfx + "", dec_abs +"",dec_rel+""});
        }
        return solucion.size() > 0 ? solucion : null;
    }

    public static ArrayList<String[]> puntoFijo(String f, String g, double tolerancia, double xa, int niter) {
        ArrayList<String[]> solucion = new ArrayList<>();
        int contador = 0;
        double error = tolerancia + 1.0;
        double error_rel = (tolerancia + 1.0) / tolerancia;
        double fx = NumericalUtilities.evaluarFuncion(f, xa);
        solucion.add(new String[]{contador + "", xa + "", fx + "", "No existe", "No existe"});
        while (fx != 0 && error > tolerancia && contador < niter) {
            double xn = NumericalUtilities.evaluarFuncion(g, xa);
            fx = NumericalUtilities.evaluarFuncion(f, xn);
            DecimalFormat format = new DecimalFormat("0.##E0");
            error = Math.abs(xn - xa);
            String dec_abs=format.format(error);
            error_rel = Math.abs(error / xn);
            String dec_rel=format.format(error_rel);
            xa = xn;
            contador++;
            solucion.add(new String[]{contador + "", xa + "", fx + "", dec_abs + "", dec_rel+""});
        }
        return solucion.size() > 0 ? solucion : null;
    }

    public static ArrayList<String[]> biseccion(String f, double xi, double xs, double tolerancia, int niter) {
        ArrayList<String[]> solucion = new ArrayList<>();
        double fxi = NumericalUtilities.evaluarFuncion(f, xi);
        double fxs = NumericalUtilities.evaluarFuncion(f, xs);
        if (fxi == 0) {
            solucion.add(new String[]{"0", xi + "", xs + "", xi + "", fxi + "", "No existe", "No existe"});
            //solucion.add(new String[]{"0", xi + "",fxi +"", "N/A"});
        } else if (fxs == 0) {
            solucion.add(new String[]{"0", xi + "", xs + "", xs + "", fxs + "", "No existe", "No existe"});
            //solucion.add(new String[]{"0", xs + "",fxs+"", "N/A"});
        } else if (fxi * fxs < 0) {
            double xm = (xi + xs) / 2;
            double fxm = NumericalUtilities.evaluarFuncion(f, xm);
            int contador = 1;
            solucion.add(new String[]{contador + "", xi + "", xs + "", xm + "", fxm + "", "No existe", "No existe"});
            //solucion.add(new String[]{contador + "", xm + "",fxm +"", "N/A"});
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
                DecimalFormat format = new DecimalFormat("0.##E0");
                String dec_abs=format.format(Math.abs(xm - xaux));
                double error_rel = Math.abs(error / xm);
                String dec_rel = format.format(error_rel);
                contador++;
                solucion.add(new String[]{contador + "", xi + "", xs + "", xm + "", fxm + "", dec_abs + "", dec_rel + ""});
                //solucion.add(new String[]{contador + "", xm + "", fxm + "", error + ""});
            }
        } else {

            return null;
        }
        return solucion;
    }

    public static ArrayList<String[]> busquedaIncremental(String f, double x0, double delta, int niter) {
        ArrayList<String[]> solucion = new ArrayList<>();
        double fx0 = NumericalUtilities.evaluarFuncion(f, x0);
        if (fx0 == 0) {
            solucion.add(new String[]{"0", x0 + "", fx0 + ""});
            System.out.printf("Hay raiz en %.15f\n", x0);
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
                solucion.add(new String[]{contador + "", x1 + "", fx1 + ""});
            }
        }
        return solucion;
    }

    public static ArrayList<String[]> raicesMultiples(String f, String df, String ddf, boolean derivadas_de_usuario, double x0, double tolerancia, int niter) {
        ArrayList<String[]> solucion = new ArrayList<>();
        double fx = NumericalUtilities.evaluarFuncion(f, x0);
        double dfx;
        double ddfx;
        if(derivadas_de_usuario){
            dfx = NumericalUtilities.evaluarFuncion(df, x0);
            ddfx = NumericalUtilities.evaluarFuncion(ddf, x0);
        }else{
            dfx = NumericalUtilities.evaluarDerivada(f, x0);
            ddfx = NumericalUtilities.evaluarSegundaDerivada(f, x0);
        }
        double xn = x0;
        int contador = 0;
        double error = tolerancia + 1.0;
        solucion.add(new String[]{contador + "", xn + "", fx + "", dfx + "", ddfx + "", "No Existe", "No Existe"});
        while (contador < niter && error > tolerancia) {
            double xnn = xn - ((fx * dfx) / (Math.pow(dfx, 2) - fx * ddfx));
            error = Math.abs(xn - xnn);
            xn = xnn;
            fx = NumericalUtilities.evaluarFuncion(f, xn);
            if(derivadas_de_usuario){
                dfx = NumericalUtilities.evaluarFuncion(df, xn);
                ddfx = NumericalUtilities.evaluarFuncion(ddf, xn);
            }else{
                dfx = NumericalUtilities.evaluarDerivada(f, xn);
                ddfx = NumericalUtilities.evaluarSegundaDerivada(f, xn);
            }
            double error_rel = Math.abs(error / xn);
            DecimalFormat format = new DecimalFormat("0.##E0");
            String dec_abs=format.format(error);
            String dec_rel=format.format(error_rel);
            contador++;
            solucion.add(new String[]{contador + "", xn + "", fx + "", dfx + "", ddfx + "", dec_abs + "", dec_rel + ""});
        }
        return solucion.size() > 0 ? solucion: null;
    }

    public static ArrayList<String[]> reglaFalsa(String f, double xi, double xs, double tolerancia, int niter) {
        ArrayList<String[]> solucion = new ArrayList<>();
        double fxi = NumericalUtilities.evaluarFuncion(f, xi);
        double fxs = NumericalUtilities.evaluarFuncion(f, xs);
        if (fxi == 0) {
            solucion.add(new String[]{"0",xi + "", fxi + "", "No Existe", "No Existe"});
        } else if (fxs == 0) {
            solucion.add(new String[]{"0", xs +"", fxs + "", "No Existe", "No existe"});
        } else if (fxi * fxs < 0) {
            double xm = xi - ((fxi * (xs - xi))) / (fxs - fxi);
            double fxm = NumericalUtilities.evaluarFuncion(f, xm);
            int contador = 1;
            solucion.add(new String[]{contador + "", xm + "", fxm + "", "No Existe", "No Existe"});
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
                DecimalFormat format = new DecimalFormat("0.##E0");
                String dec_abs =format.format(error);
                String dec_rel =format.format(error_rel);

                contador++;
                solucion.add(new String[]{contador + "", xm + "", fxm + "", dec_abs+"" + "", dec_rel+""});
            }
        } else {
            return null;
        }
        return solucion.size() > 0 ? solucion : null;
    }

    public static ArrayList<String[]> secante(String f, double tolerancia, double x0, double x1, int niter) {
        ArrayList<String[]> solucion = new ArrayList<>();
        double fx0 = NumericalUtilities.evaluarFuncion(f, x0);
        if (fx0 == 0) {
            solucion.add(new String[]{"0", x0 + "", fx0 + "", "No Existe", "No Existe"});
        } else {
            double fx1 = NumericalUtilities.evaluarFuncion(f, x1);
            int contador = 0;
            double error = tolerancia + 1;
            double error_rel = tolerancia + 1;
            double den = fx1 - fx0;
            solucion.add(new String[]{"i", x0 + "", fx0 + "", "No Existe", "No Existe"});
            solucion.add(new String[]{"u", x1 + "", fx1 + "", "No Existe", "No Existe"});
            while (error > tolerancia && fx1 != 0 && den != 0 && contador < niter) {
                double x2 = x1 - fx1 * (x1 - x0) / den;
                error = Math.abs(x2 - x1);
                error_rel = Math.abs(error / x2);
                x0 = x1;
                fx0 = fx1;
                x1 = x2;
                fx1 = NumericalUtilities.evaluarFuncion(f, x1);
                den = fx1 - fx0;

                DecimalFormat format = new DecimalFormat("0.##E0");
                String dec_abs = format.format(error);
                String dec_rel = format.format(error_rel);

                contador++;
                solucion.add(new String[]{contador + "", x1 + "", fx1 + "",  dec_abs+ "",dec_rel+""});
            }
        }
        return solucion.size() > 0 ? solucion : null;
    }
}
