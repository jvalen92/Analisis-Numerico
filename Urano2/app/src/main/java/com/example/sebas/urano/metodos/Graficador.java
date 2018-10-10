package com.example.sebas.urano.metodos;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.io.IOException;

public class Graficador {
    public static void graficar()  {
        XYChart chart = (XYChart) QuickChart.getChart("Grafica de ln(sin(x)^2 + 1) - 1/2",
                "x",
                "f(x)",
                "f(x)",
                getData1()[0],
                getData1()[1]);
        try {
            BitmapEncoder.saveBitmap(chart, "./funcion", BitmapEncoder.BitmapFormat.PNG);
        }catch (IOException ex){
            System.out.println(ex);
        }

    }
    private static double[][] getData1(){
        double[][] xyData = new double[2][100];
        double s = 0.1;
        for(int i = 0; i < 100; ++i){
            xyData[0][i] = s;
            xyData[1][i] = Utilities.f(s)[0];
            s += 0.1;
        }
        return xyData;
    }
}
