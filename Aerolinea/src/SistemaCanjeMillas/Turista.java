/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaCanjeMillas;

/**
 *
 * @author Majo
 */
public class Turista extends Viaje {

    public Turista(String id, String nombre, String descripcion, float factorDeMillas, Lugar origen, Lugar destino, Estado e) {
        super(id, nombre, descripcion, factorDeMillas, origen, destino, e);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public long generaMillas(float valorNumerico) {
        setGananciaMillas((long) (getFactorDeMillas() * (long) getGananciaEnMillas()));
        return getGananciaMillas();
    }

    @Override
    public GeneradorDeMillas copiaGen(GeneradorDeMillas g) {
        return new Turista(g.getId(),g.getNombre(),g.getDescripcion(),g.getFactorDeMillas(),((Viaje)g).getOrigen(),((Viaje)g).getDestino(),g.getEstado());
    }

}
