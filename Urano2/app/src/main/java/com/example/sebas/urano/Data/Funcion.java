package com.example.sebas.urano.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Funcion {
    private static List<String> funciones;
    private static Funcion funcion;
    public static final Funcion crearInstancia(){
        if(funciones == null){
            funciones = new ArrayList<>(10);
            for(int i = 0; i < 10; ++i){
                funciones.add(new String(""));
            }
            funcion = new Funcion();
        }
        return funcion;
    }
    public boolean contains(String s){ return funciones.contains(s);}
    public void set(String res, int index){
        funciones.add(index, res);
    }
    public int size(){return funciones.size();}
    public List<String> getFunciones(){ return funciones;}
    public void agregarFuncion(String funcion){
        funciones.add(funcion);
    }
    public void eliminarFuncion(String funcion){
        funciones.remove(funcion);
    }
    public void eliminarFuncion(int index){
        funciones.remove(index);
    }
    public void modificarFuncion(int index, String funcion){
        funciones.add(index, funcion);
    }
    public List<String> obtenerFuncionesUtiles(){
        List<String> nuevaLista = new ArrayList<>();
        for(String s: funciones){
            if(s.equals("")) continue;
            nuevaLista.add(s);
        }
        return nuevaLista;
    }
}
