package com.example.sebas.urano.Math;

public class GraphUtilities {
    public static double[] linspace(double x1, double x2, int numberOfPoints){
        double[] spaceSet = new double[numberOfPoints + 1];
        double step = (x2 - x1) / (numberOfPoints - 1);
        spaceSet[0] = x1;
        for(int i = 0; i < numberOfPoints - 1; ++i){
            spaceSet[i + 1] = x1 + step * i;
        }
        spaceSet[numberOfPoints] = x2;
        return spaceSet;
    }
}
