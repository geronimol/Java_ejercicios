/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viaje;

import java.io.Serializable;

/**
 *
 * @author Cliente
 */
public class Destino implements Comparable,Serializable{
    private String nombre;
    private float km;

    public Destino(String nombre, float km) {
        this.nombre=nombre;
        this.km=km;
    }

    public Destino(String nombre) {
        this.nombre=nombre;
    }

    @Override
    public int compareTo(Object t) {
        Destino d=(Destino)t;
        int cmpNombre=this.nombre.compareTo(d.nombre);
        return cmpNombre;
    }
    
    @Override
    public String toString() {
        return " "+nombre+" Distancia:"+km;
 } 

    public String getNombre() {
        return nombre;
    }

    public float getKm() {
        return km;
    }
    
}
