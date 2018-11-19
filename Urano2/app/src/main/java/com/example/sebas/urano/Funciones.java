package com.example.sebas.urano;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebas.urano.Data.Funcion;
import com.jjoe64.graphview.GraphView;

import java.util.List;

public class Funciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funciones);

        Toolbar graphToolbar = (Toolbar) findViewById(R.id.func_toolbar);
        graphToolbar.setTitle("Funciones");
        setSupportActionBar(graphToolbar);

        // Fill all the spaces whilst there are functions
        llenarFunciones();

        //limbiar el campo del editText en caso que hayan funciones
        EditText funcion = (EditText) findViewById(R.id.txtFuncion1);
        funcion.setText(null);

        //valor de la funcion ingresada por el usuario
        String fun = getIntent().getExtras().getString("funcion");
        //Toast.makeText(this,fun,Toast.LENGTH_LONG).show();
        funcion.setText(fun);
    }

    public void obtenerFunciones(View view) {
        String func1 = String.valueOf(((EditText) findViewById(R.id.txtFuncion1)).getText());
        String func2 = String.valueOf(((EditText) findViewById(R.id.txtFuncion2)).getText());
        String func3 = String.valueOf(((EditText) findViewById(R.id.txtFuncion3)).getText());
        String func4 = String.valueOf(((EditText) findViewById(R.id.txtFuncion4)).getText());
        String func5 = String.valueOf(((EditText) findViewById(R.id.txtFuncion5)).getText());
        func1 = eliminarEspacios(func1);
        func2 = eliminarEspacios(func2);
        func3 = eliminarEspacios(func3);
        func4 = eliminarEspacios(func4);
        func5 = eliminarEspacios(func5);
        Funcion func = Funcion.crearInstancia();
        func.set(func1, 0);
        func.set(func2, 1);
        func.set(func3, 2);
        func.set(func4, 3);
        func.set(func5, 4);
    }

    public void llenarFunciones() {
        Funcion funcion = Funcion.crearInstancia();
        List<String> funciones = funcion.getFunciones();
        ((EditText) findViewById(R.id.txtFuncion1)).setText(funciones.get(0), TextView.BufferType.EDITABLE);
        ((EditText) findViewById(R.id.txtFuncion2)).setText(funciones.get(1), TextView.BufferType.EDITABLE);
        ((EditText) findViewById(R.id.txtFuncion3)).setText(funciones.get(2), TextView.BufferType.EDITABLE);
        ((EditText) findViewById(R.id.txtFuncion4)).setText(funciones.get(3), TextView.BufferType.EDITABLE);
        ((EditText) findViewById(R.id.txtFuncion5)).setText(funciones.get(4), TextView.BufferType.EDITABLE);
    }

    private String eliminarEspacios(String string) {
        return string.replaceAll("\\s+", "");
    }

    public void actualizar(View view) {
        Intent to = new Intent(this, Grafico.class);
        obtenerFunciones(view);
        startActivity(to);
    }
}
