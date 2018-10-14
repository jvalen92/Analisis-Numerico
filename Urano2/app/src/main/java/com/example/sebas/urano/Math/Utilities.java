package com.example.sebas.urano.Math;

import com.jjoe64.graphview.series.DataPoint;

import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.interfaces.IExpr;

import java.util.Random;


public class Utilities {
    //protected static ScriptEngine fScriptEngine;
    public static DataPoint[] obtenerTodosLosPuntos(double[] linspace, String expresion) {
        DataPoint[] puntos = new DataPoint[linspace.length];
        EvalUtilities util = new EvalUtilities(false, true);
        for (int i = 0; i < linspace.length; i++) {
            IExpr result = util.evaluate("x="+linspace[i]+";"+expresion);
            puntos[i] = new DataPoint(linspace[i], result.evalDouble());
        }
        return puntos;
    }

    /**
     * Generate an array  of 3 values with different values between 0 and 255
     * @return
     */
    public static int[] ramdomColor(){
        final int MOD = 256;
        Random rand = new Random();
        return new int[]{rand.nextInt(MOD), rand.nextInt(MOD), rand.nextInt(MOD)};
    }
}
