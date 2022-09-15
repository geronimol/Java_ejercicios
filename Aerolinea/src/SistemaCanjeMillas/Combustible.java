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
public class Combustible extends GeneradorDeMillas{
    private float litrosCargados;

    public Combustible(String id, String nombre, String descripcion, float factorDeMillas, Estado e) {
        super(id,nombre,descripcion,factorDeMillas, e);
        litrosCargados=0;
    }
    public float getLitrosCargados(){
        return litrosCargados;
    }

    @Override
    public long generaMillas(float valorNumerico) {
        litrosCargados=valorNumerico;
        setGananciaMillas((long)(litrosCargados/getFactorDeMillas()));
        return getGananciaMillas();
    }

    @Override
    public String toString() {
        return super.toString() + " Combustible: " + getGananciaMillas(); 
    }

    @Override
    public GeneradorDeMillas copiaGen(GeneradorDeMillas g) {
        return new Combustible(g.getId(),g.getNombre(),g.getDescripcion(),g.getFactorDeMillas(),g.getEstado());
    }
    
    
}
                               