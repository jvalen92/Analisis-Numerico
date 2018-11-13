package com.example.sebas.urano.Methods;

public class SingletonMensaje {
    private static final SingletonMensaje singletonMensaje = new SingletonMensaje();
    private String mensaje;
    private boolean error;
    private SingletonMensaje(){ }
    public static SingletonMensaje getInstance(){
        return singletonMensaje;
    }
    public String getMensajeActual(){
        return mensaje;
    }
    public void setMensajeActual(String mensaje){
        this.mensaje = mensaje;
    }
    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return error;
    }
}
