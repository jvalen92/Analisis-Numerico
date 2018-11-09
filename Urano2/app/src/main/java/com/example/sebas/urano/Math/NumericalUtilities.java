package com.example.sebas.urano.Math;

import com.example.sebas.urano.MainActivity;
import com.jjoe64.graphview.series.DataPoint;

import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.interfaces.IExpr;

import java.util.Random;


public class NumericalUtilities {

    public static DataPoint[] obtenerTodosLosPuntos(double[] linspace, String expresion) {
        EvalUtilities util = new EvalUtilities(false, true);
        DataPoint[] puntos = new DataPoint[linspace.length];
        for (int i = 0; i < linspace.length; i++) {
            IExpr result = util.evaluate("x=" + linspace[i] + ";" + expresion);
            puntos[i] = new DataPoint(linspace[i], result.evalDouble());
        }
        return puntos;
    }

    public static double evaluarFuncion(String funcion, double valor) {
        EvalUtilities util = new EvalUtilities(false, true);
        return util.evaluate("x=" + valor + ";" + funcion).evalDouble();
    }

    public static double evaluarDerivada(String funcion, double valor) {
        EvalUtilities util = new EvalUtilities(false, true);
        IExpr der = util.evaluate("y = D(" + funcion + ", x);x="+valor+";y");
        return der.evalDouble();
    }
    public static double evaluarSegundaDerivada(String funcion, double valor){
        EvalUtilities util = new EvalUtilities(false, true);
        IExpr exp = util.evaluate("y="+funcion+";z=D(y, x); w = D(z, x); x = " + valor + ";w");
        return exp.evalDouble();
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

    /*public static void main(String[] args) {
        EvalUtilities util = new EvalUtilities(false, true);
        String f = "ln((sin(x))^2 + 1) - 1/2";

        IExpr der = util.evaluate("y = D(" + f + ", x);y");
        System.out.println(der.toString());

    }
    */

}
