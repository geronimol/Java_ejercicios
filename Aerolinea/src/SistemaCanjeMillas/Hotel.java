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
public class Hotel extends GeneradorDeMillas {

    private Lugar lugar;
    private String categoria;
    private float precioNoche;
    private int cantNoches;

    public Hotel(Lugar lugar, String categoria, float precioNoche, int cantNoches, String id, String nombre, String descripcion, float factorDeMillas, Estado e) {
        super(id, nombre, descripcion, factorDeMillas, e);
        this.lugar = lugar;
        this.categoria = categoria;
        this.precioNoche = precioNoche;
        this.cantNoches = cantNoches;
    }

    @Override
    public String toString() {
        return super.toString() + " Hotel " + getGananciaMillas(); 
    }

    @Override
    public long generaMillas(float valorNumerico) {
        setGananciaMillas((long) (cantNoches * precioNoche * getFactorDeMillas()));
        return getGananciaMillas();
    }

    @Override
    public GeneradorDeMillas copiaGen(GeneradorDeMillas g) {
        return new Hotel(((Hotel)g).getLugar(),((Hotel)g).getCategoria(),((Hotel)g).getPrecioNoche(),((Hotel)g).getCantNoches(),g.getId(),g.getNombre(),g.getDescripcion(),g.getFactorDeMillas(),g.getEstado());
    }

    public int getCantNoches() {
        return cantNoches;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public String getCategoria() {
        return categoria;
    }

    public float getPrecioNoche() {
        return precioNoche;
    }
    
    
}
