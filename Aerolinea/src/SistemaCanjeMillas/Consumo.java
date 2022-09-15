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
public class Consumo extends GeneradorDeMillas {
    private float costo;
    
    public Consumo(String id, String nombre, String descripcion, float factorDeMillas, Estado e) {
        super(id, nombre, descripcion, factorDeMillas, e);
        this.costo = 0;
    }
    public float getCosto(){
        return costo;
    }

    @Override
    public String toString() {
        return super.toString() + " Consumo: " + getGananciaMillas(); 
    }
    
    @Override
    public long generaMillas(float valorNumerico) {
        costo = valorNumerico;
        setGananciaMillas((long)(costo*getFactorDeMillas())); 
        return getGananciaMillas();
    }

    @Override
    public GeneradorDeMillas copiaGen(GeneradorDeMillas g) {
         return new Consumo(g.getId(),g.getNombre(),g.getDescripcion(),g.getFactorDeMillas(),g.getEstado());
    }
}
