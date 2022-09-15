/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaCanjeMillas;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Majo
 */
public class Primera extends Viaje {

    private float aumentoMillas;
    private static final int TOTAL_ADICIONALES = 3;
    private int cantidadAdicionales;
    private TreeMap<String, Long> adicionales;

    public Primera(String id, String nombre, String descripcion, float factorDeMillas, Lugar origen, Lugar destino, Estado e) {
        super(id, nombre, descripcion, factorDeMillas, origen, destino, e);
        aumentoMillas = 2;
        cantidadAdicionales = 0;
        this.adicionales = new TreeMap<>();
    }

    public void aumentaCantidadAdicionales() {
        if (cantidadAdicionales < TOTAL_ADICIONALES) {
            cantidadAdicionales++;
        }
    }

    public void setCantidadAdicionales(int cantidadAdicionales) {
        this.cantidadAdicionales = cantidadAdicionales;
    }

    public boolean agregaAdicional(String nombre, long costoMillas) {
        if (cantidadAdicionales < TOTAL_ADICIONALES) {
            cantidadAdicionales++;
            adicionales.put(nombre, costoMillas);
            return true;
        } else {
            System.out.println("No puede tener mas de" + TOTAL_ADICIONALES + "adicionale");
        }
        return false;
    }

    public boolean verificaCantAdicionales() {
        return cantidadAdicionales == TOTAL_ADICIONALES;
    }

    public long restaMillasDeAdicionales() {
        long totalMillas = 0;
        if (cantidadAdicionales > 0) {
            for (Map.Entry<String, Long> entry : adicionales.entrySet()) {
                String key = entry.getKey();
                Long value = entry.getValue();
                totalMillas += value;
            }
            return totalMillas;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " Adicionales: " + adicionales.toString(); 
    }

    @Override
    public long generaMillas(float valorNumerico) {
        setGananciaMillas((long) ((getFactorDeMillas() * getGananciaEnMillas()) * aumentoMillas - restaMillasDeAdicionales()));
        return getGananciaMillas();
    }

    @Override
    public GeneradorDeMillas copiaGen(GeneradorDeMillas g) {
        Primera p = new Primera (g.getId(),g.getNombre(),g.getDescripcion(),g.getFactorDeMillas(),((Viaje)g).getOrigen(),((Viaje)g).getDestino(),g.getEstado());
        if (cantidadAdicionales > 0) {
            for (Map.Entry<String, Long> entry : adicionales.entrySet()) {
                String key = entry.getKey();
                Long value = entry.getValue();
                p.agregaAdicional(key, value);
            }
        }
        return p;
    }

}
