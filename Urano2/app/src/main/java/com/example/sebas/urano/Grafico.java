package com.example.sebas.urano;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.sebas.urano.Data.Funcion;
import com.example.sebas.urano.Math.GraphUtilities;
import com.example.sebas.urano.Math.NumericalUtilities;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import java.util.List;

public class Grafico extends AppCompatActivity {
    private GraphView graphView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        Toolbar graphToolbar = (Toolbar) findViewById(R.id.graph_toolbar);
        graphToolbar.setTitle("Funciones");
        setSupportActionBar(graphToolbar);
        graphView = (GraphView) findViewById(R.id.graph);
        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(-150);
        graphView.getViewport().setMaxY(150);

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(4);
        graphView.getViewport().setMaxX(80);

        graphView.getViewport().setScrollable(true); // enables horizontal scrolling
        graphView.getViewport().setScrollableY(true); // enables vertical scrolling
        graphView.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graphView.getViewport().setScalableY(true);
        boolean can = paintFunctions();
        if(!can){
            Toast.makeText(this, "No hay funciones para dibujar. " +
                    "Puedes crear funciones tocando en el menú a la derecha superior y despés tocando funciones.",
                    Toast.LENGTH_LONG).show();
        }
    }
    public boolean paintFunctions(){
        Funcion functions = Funcion.crearInstancia();
        graphView.removeAllSeries();
        List<String> funcs = functions.obtenerFuncionesUtiles();
        if(funcs.size() > 0){
            double[] linspace = GraphUtilities.linspace(-10, 10, 100);
            LineGraphSeries<DataPoint> series;
            for(String s: funcs){
                int[] colr = NumericalUtilities.ramdomColor();
                int red = colr[0];
                int blue = colr[1];
                int green = colr[2];
                DataPoint[] points = NumericalUtilities.obtenerTodosLosPuntos(linspace, s);
                series = new LineGraphSeries<>(points);
                series.setColor(Color.rgb(red, green, blue));
                graphView.addSeries(series);
            }
            return true;
        }
        return false;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.opciones_grafico, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        Intent to;
        if(id == R.id.mn_home){
            //Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            to = new Intent(this, MainActivity.class);
            startActivity(to);
        }else{
            //Toast.makeText(this, "Funciones", Toast.LENGTH_SHORT).show();
            to = new Intent(this, Funciones.class);
            startActivity(to);
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
