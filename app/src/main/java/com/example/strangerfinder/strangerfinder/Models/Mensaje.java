package com.example.strangerfinder.strangerfinder.Models;

/**
 * Created by Jimmy on 13/11/2017.
 */

public class Mensaje {

    public boolean izquierda;
    public String mensaje;

    public Mensaje(boolean izquierda, String mensaje) {
        this.izquierda = izquierda;
        this.mensaje = mensaje;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
