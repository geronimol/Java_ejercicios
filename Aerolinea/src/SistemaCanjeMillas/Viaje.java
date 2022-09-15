/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaCanjeMillas;

import java.io.Serializable;
import java.util.TreeSet;

/**
 *
 * @author Majo
 */
public abstract class Viaje extends GeneradorDeMillas implements Canjeable, Serializable {

    private TreeSet<Viajero> viajeros;
    private Lugar origen, destino;
    private TablaDeDestinos tablaDestinos;

    public Viaje(String id, String nombre, String descripcion, float factorDeMillas, Lugar origen, Lugar destino, Estado e) {
        super(id, nombre, descripcion, factorDeMillas, e);
        this.tablaDestinos = TablaDeDestinos.getInstanceTablaDeDestinos();
        this.origen = origen;
        this.destino = destino;
    }

    public String getNombreOrigen() {
        return origen.getNombre();
    }

    public String getNombreDestino() {
        return destino.getNombre();
    }

    public long getGananciaEnMillas() {
        return (tablaDestinos.getGananciaEnMillas(origen, destino));
    }

    public long getCostoEnMillas() {
        return (tablaDestinos.getCostoEnMillas(origen, destino));
    }

    public Lugar getDestino() {
        return destino;
    }
    
    public Lugar getOrigen(){
        return origen;
    }

    @Override
    public abstract long generaMillas(float valorNumerico);

    @Override
    public String toString() {
        return super.toString() + " Viaje Origen: " + origen.getNombre() + " Destino: " + destino.getNombre() + " MillasObt: " + getGananciaMillas(); 
    }

    @Override
    public int compareTo(Object o) {
        return super.compareTo(o); 
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); 
    }

}
