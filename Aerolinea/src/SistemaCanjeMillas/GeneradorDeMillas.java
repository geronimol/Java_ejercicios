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
public abstract class GeneradorDeMillas implements Comparable, Serializable, Copiable {

    private String id, nombre, descripcion;
    private float factorDeMillas;
    private long gananciaMillas;

    public enum Estado {
        DISPONIBLE, SUSPENDIDO, CADUCO
    };
    private Estado estado;

    public String getId() {
        return id;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public long getGananciaMillas() {
        return gananciaMillas;
    }

    public void setGananciaMillas(long gananciaMillas) {
        this.gananciaMillas = gananciaMillas;
    }

    public abstract long generaMillas(float valorNumerico);

    public GeneradorDeMillas(String id, String nombre, String descripcion, float factorDeMillas, Estado e) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.factorDeMillas = factorDeMillas;
        estado = e;
    }

    public float getFactorDeMillas() {
        return factorDeMillas;
    }

    @Override
    public int compareTo(Object o) {
        GeneradorDeMillas g = (GeneradorDeMillas) o;
        return this.id.compareTo(g.id);
    }

    public String toString() {
        return "ID " + id + " Nombre " + nombre + " Descripcion " + descripcion + " Factor de millas " + factorDeMillas + " Estado " + estado;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GeneradorDeMillas)) {
            return false;
        }
        GeneradorDeMillas g = (GeneradorDeMillas) o;
        return this.id.equals(g.id);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFactorDeMillas(float factorDeMillas) {
        this.factorDeMillas = factorDeMillas;
    }

}
