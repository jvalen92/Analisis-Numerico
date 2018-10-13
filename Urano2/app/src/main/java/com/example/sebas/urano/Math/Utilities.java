package com.example.sebas.urano.Math;

import com.jjoe64.graphview.series.DataPoint;

import org.matheclipse.core.eval.EvalUtilities;
import org.matheclipse.core.interfaces.IExpr;



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

    public static void main(String[] args) {
        double[] linspace = GraphUtilities.linspace(-10, 10, 100);
        DataPoint[] puntos = new DataPoint[linspace.length];
        EvalUtilities util = new EvalUtilities(false, true);
        for (int i = 0; i < linspace.length; i++) {
            IExpr result = util.evaluate("x="+linspace[i]+";"+"3x + 4");
            puntos[i] = new DataPoint(linspace[i], result.evalDouble());
        }
        for(DataPoint d: puntos){
            System.out.println(d.getX() + " " + d.getY());
        }
    }
}
