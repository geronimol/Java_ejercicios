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
public class FilaDeDestinos implements Comparable, Serializable {

    private Lugar origen, destino;
    private long costoEnMillas, gananciaMillas;

    public FilaDeDestinos(Lugar origen, Lugar destino, long costoMillaLugares, long gananciaMillas) {
        this.origen = origen;
        this.destino = destino;
        this.costoEnMillas = costoMillaLugares;
        this.gananciaMillas = gananciaMillas;
    }

    public FilaDeDestinos(Lugar origen, Lugar destino) {
        this.origen = origen;
        this.destino = destino;
        this.costoEnMillas = 0;
        this.gananciaMillas = 0;
    }

    public void setCostoEnMillas(long costoEnMillas) {
        this.costoEnMillas = costoEnMillas;
    }

    public void setGananciaMillas(long gananciaMillas) {
        this.gananciaMillas = gananciaMillas;
    }

    public long getCostoEnMillas() {
        return costoEnMillas;
    }
    
    @Override
    public int compareTo(Object o) {
        FilaDeDestinos f = (FilaDeDestinos) o;
        int compFila = this.origen.compareTo(f.origen);
        return (compFila != 0 ? compFila
                : this.destino.compareTo(f.destino));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof FilaDeDestinos)) {
            return false;
        }
        FilaDeDestinos f = (FilaDeDestinos) o;
        return this.origen.getNombre().equals(f.origen.getNombre()) && this.destino.getNombre().equals(f.destino.getNombre());
    }

    @Override
    public String toString() {
        return origen.getNombre() + " - " + destino.getNombre() + " Costo en millas: " + costoEnMillas + " Ganancia en Millas " + gananciaMillas;
    }

    public long getGananciaMillas() {
        return gananciaMillas;
    }

    public String getNombreOrigen() {
        return origen.getNombre();
    }

    public String getNombreDestino() {
        return destino.getNombre();
    }
    
}
