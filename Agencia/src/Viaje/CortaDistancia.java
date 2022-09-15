/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viaje;

import Vehiculo.Vehiculo;

/**
 *
 * @author Cliente
 */
public class CortaDistancia extends Viaje {
    private static float VALOR_BASE=1;
    private float valorBase;
    public CortaDistancia(String nombre, Destino destino, Vehiculo vehiculo,int cantPasajeros) {
        super(nombre, destino, vehiculo,cantPasajeros);
        valorBase=VALOR_BASE;
    }
    
    @Override
    public String toString() {
        return super.toString()+" Valor base:"+valorBase;
    }

    public float getValor() {
        return valorBase;
    }
    
    public void iniciar(){
        super.iniciar();
    }
    
    public void detenerViajes() {
        super.detenerViajes();
        finalizar();
    }
    
    public void finalizar(){
        super.finalizar();
    }

    
}
