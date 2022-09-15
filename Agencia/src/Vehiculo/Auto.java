/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehiculo;

/**
 *
 * @author Cliente
 */
public class Auto extends Vehiculo {
    private static float VALOR_BASE=1,VALOR_POR_KM=1;
    private float valorBase;
    private float valorPorKm;
    public Auto(String patente, int capacidadPasajeros, float velocidadPromedio) {
        super(patente, capacidadPasajeros, velocidadPromedio);
        valorBase=VALOR_BASE;
        valorPorKm=VALOR_POR_KM;
    }
    public Auto(String patente, float velocidadPromedio) {
        super(patente, 4, velocidadPromedio);
        valorBase=VALOR_BASE;
        valorPorKm=VALOR_POR_KM;
    }
    
    @Override
    public String toString() {
        return super.toString()+" Valor base:"+valorBase+" Valor por km:"+valorPorKm;
 } 
    public float getValorBase() {
        return valorBase;
    }
    
    public float getValorPorKm(){
        return valorPorKm;
    }
    
    @Override
    public float getValor(float km) {
        return valorBase+valorPorKm*km;
    }
}
