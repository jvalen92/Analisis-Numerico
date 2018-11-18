package com.example.sebas.urano.Math;

import com.example.sebas.urano.MainActivity;
import com.example.sebas.urano.Methods.SingletonMensaje;
import com.jjoe64.graphview.series.DataPoint;

import org.matheclipse.core.builtin.function.Do;
import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.interfaces.IExpr;

import java.util.Random;


public class NumericalUtilities {

    public static DataPoint[] obtenerTodosLosPuntos(double[] linspace, String expresion) {
        EvalUtilities util = new EvalUtilities(false, true);
        DataPoint[] puntos = new DataPoint[linspace.length];
        for (int i = 0; i < linspace.length; i++) {
            IExpr result = util.evaluate("x=" + linspace[i] + ";" + expresion);
            puntos[i] = new DataPoint(linspace[i], result.evalComplex().getReal());
        }
        return puntos;
    }

    public static double evaluarFuncion(String funcion, double valor) {
        EvalUtilities util = new EvalUtilities(false, true);
        IExpr der = util.evaluate("x=" + valor + ";" + funcion);
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setError(false);
        if (Double.isNaN(valor) || valor == Double.POSITIVE_INFINITY || valor == Double.NEGATIVE_INFINITY) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                    " el resultado no está en el conjunto de los reales.");
            return 0;
        }
        try {
            if (der.evalComplex().getImaginary() > 0) {
                singletonMensaje.setError(true);
                singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                        " el resultado no está en el conjunto de los reales.");
                return der.evalComplex().getReal();
            }
        } catch (Exception ex) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                    " el resultado no está en el conjunto de los reales.");
            return 0;
        }
        return der.evalDouble();
    }

    public static double evaluarDerivada(String funcion, double valor) {
        EvalUtilities util = new EvalUtilities(false, true);
        IExpr der = util.evaluate("y = D(" + funcion + ", x);x=" + valor + ";y");
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setError(false);
        if (Double.isNaN(valor) || valor == Double.POSITIVE_INFINITY || valor == Double.NEGATIVE_INFINITY) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                    " el resultado no está en el conjunto de los reales.");
            return 0;
        }
        try {
            if (der.evalComplex().getImaginary() > 0) {
                singletonMensaje.setError(true);
                singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                        " el resultado no está en el conjunto de los reales.");
                return der.evalComplex().getReal();
            }
        } catch (Exception ex) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                    " el resultado no está en el conjunto de los reales.");
            return 0;
        }
        return der.evalComplex().getReal();
    }

    public static double evaluarSegundaDerivada(String funcion, double valor) {
        EvalUtilities util = new EvalUtilities(false, true);
        IExpr der = util.evaluate("y=" + funcion + ";z=D(y, x); w = D(z, x); x = " + valor + ";w");
        SingletonMensaje singletonMensaje = SingletonMensaje.getInstance();
        singletonMensaje.setError(false);
        if (Double.isNaN(valor) || valor == Double.POSITIVE_INFINITY || valor == Double.NEGATIVE_INFINITY) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                    " el resultado no está en el conjunto de los reales.");
            return 0;
        }
        try {
            if (der.evalComplex().getImaginary() > 0) {
                singletonMensaje.setError(true);
                singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                        " el resultado no está en el conjunto de los reales.");
                return der.evalComplex().getReal();
            }
        } catch (Exception ex) {
            singletonMensaje.setError(true);
            singletonMensaje.setMensajeActual("Durante la ejecución del método, al evaluar la función en " + valor +
                    " el resultado no está en el conjunto de los reales.");
            return 0;
        }
        return der.evalDouble();
    }

    /**
     * Generate an array  of 3 values with different values between 0 and 255
     *
     * @return
     */
    public static int[] ramdomColor() {
        final int MOD = 256;
        Random rand = new Random();
        return new int[]{rand.nextInt(MOD), rand.nextInt(MOD), rand.nextInt(MOD)};
    }

    /**
     * Devuelve el número d con k decimales
     *
     * @param d
     * @return
     */
    public static double fd(double d, int k) {
        if (d < 1e-14) return 0.0;
        return Double.parseDouble(String.format("%." + k + "f", d).replace(',', '.'));
    }

    /**
     * Devuelve el error con k decimales
     *
     * @param e
     * @return
     */
    public static double fe(double e) {
        if (e < 1e-14) {
            return 0.0;
        }
        String value = String.valueOf(e);
        String new_val = "";
        int idx = value.indexOf('e');
        if (idx == -1) {
            idx = value.indexOf('E');
            if (idx == -1) {
                new_val = String.format("%.4f", e);
            } else {
                new_val = String.format("%.2f", Double.parseDouble(value.substring(0, idx)));
                new_val += "E";
                new_val += value.substring(idx + 1, value.length());
            }
        } else {
            new_val = String.format("%.2f", Double.parseDouble(value.substring(0, idx)));
            new_val += "E";
            new_val += value.substring(idx + 1, value.length());
        }
        new_val = new_val.replace(',', '.');
        return Double.parseDouble(new_val);
    }

    public static String format(String s, Object... params) {
        return String.format(s, params).replace(',', '.');
    }

}
