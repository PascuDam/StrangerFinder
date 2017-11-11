package com.example.strangerfinder.strangerfinder.Models;

/**
 * Created by Jimmy on 06/11/2017.
 */

public class Usuario {

    /**
     * Nombre especifica el nombre del usuario
     */
    String nombre;

    /**
     * El sexo se representa como un entero, cuando:
     *
     * 0 = mujer
     * 1 = hombre
     */
    int sexo = -1;

    /**
     * La preferencia es el sexo que el usuario busca conversar, cuando:
     *
     * 0 = mujer
     * 1 = hombre
     * 2 = sin preferencia
     */
    int preferencia = -1;

    public Usuario(String nombre, int sexo, int preferencia) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.preferencia = preferencia;
    }

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public int getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(int preferencia) {
        this.preferencia = preferencia;
    }
}
