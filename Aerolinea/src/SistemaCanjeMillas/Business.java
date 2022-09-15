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
public class Business extends Viaje {

    private float aumentoMillas;
    private static final int TOTAL_ADICIONALES = 2;
    private int cantidadAdicionales;
    private TreeMap<String, Long> adicionales;

    public Business(String id, String nombre, String descripcion, float factorDeMillas, Lugar origen, Lugar destino, Estado e) {
        super(id, nombre, descripcion, factorDeMillas, origen, destino, e);
        aumentoMillas = 1.5f;
        cantidadAdicionales = 0;
        adicionales = new TreeMap();
    }

    public void aumentaCantidadAdicionales() {
        if (cantidadAdicionales < TOTAL_ADICIONALES) {
            cantidadAdicionales++;
        }
    }

    public void setCantidadAdicionales(int cantidadAdicionales) {
        this.cantidadAdicionales = cantidadAdicionales;
    }

    public void agregaAdicional(String nombre, long costoMillas) {
        if (cantidadAdicionales < TOTAL_ADICIONALES) {
            cantidadAdicionales++;
            adicionales.put(nombre, costoMillas);
        } else {
            System.out.println("No puede tener mas de" + TOTAL_ADICIONALES + "adicionale");
        }
    }

    public boolean verificaCantAdicionales() {
        return cantidadAdicionales == TOTAL_ADICIONALES;
    }

    @Override
    public String toString() {
        return super.toString() + " Adicionales: " + adicionales.toString();
    }

    @Override
    public long generaMillas(float valorNumerico) {
        setGananciaMillas((long) ((getFactorDeMillas() * getGananciaEnMillas() * aumentoMillas - restaMillasDeAdicionales())));
        return getGananciaMillas();
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
    public GeneradorDeMillas copiaGen(GeneradorDeMillas g) {
        Business b = new Business(g.getId(),g.getNombre(),g.getDescripcion(),g.getFactorDeMillas(),((Viaje)g).getOrigen(),((Viaje)g).getDestino(), g.getEstado());
        if (cantidadAdicionales > 0) {
            for (Map.Entry<String, Long> entry : adicionales.entrySet()) {
                String key = entry.getKey();
                Long value = entry.getValue();
                b.agregaAdicional(key, value);
            }
        }
        return b;
    }

}
