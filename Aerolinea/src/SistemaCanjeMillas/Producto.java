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
public class Producto implements Canjeable, Comparable, Serializable {

    private String nombre, descripcion;
    private long costoMillas;
    private int stock;

    public Producto(String nombre, String descripcion, long costoMillas, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoMillas = costoMillas;
        this.stock = stock;
    }

    public Producto(String nombreProducto) {
        this.nombre = nombreProducto;
    }

    public long getCostoMillas() {
        return costoMillas;
    }

    public void decrementaStock() {
        stock--;
    }

    public void incrementaStock() {
        stock++;
    }

    public int getStock() {
        return stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " " + descripcion + " " + costoMillas + " " + stock + "\n"; 
    }

    @Override
    public int compareTo(Object t) {
        Producto p = (Producto) t;
        return this.nombre.compareTo(p.nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Producto)) {
            return false;
        }
        Producto p = (Producto) o;
        return this.nombre.equals(p.nombre);
    }

}
