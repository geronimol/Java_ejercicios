/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaCanjeMillas;

import java.io.Serializable;

/**
 *
 * @author Majo
 */
public class Lugar implements Comparable, Serializable {

    private String nombre, descripcion;

    public Lugar(String nombre) {
        this.nombre = nombre;
    }

    public Lugar(String nom, String descripcion) {
        nombre = nom;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int compareTo(Object t) {
        Lugar l = (Lugar) t;
        return this.nombre.compareTo(l.nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lugar)) {
            return false;
        }
        Lugar l = (Lugar) o;
        return this.nombre.equals(l.nombre);
    }

    @Override
    public String toString() {
        return nombre + " " + descripcion;
    }

}
