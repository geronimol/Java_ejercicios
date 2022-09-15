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
public class Combi extends Vehiculo{
    private float VALOR_BASE=1,VALOR_PASAJERO=1;
    private float valorBase;
    private float valorPasajero;
    public Combi(String patente, int capacidadPasajeros, float velocidadPromedio) {
        super(patente, capacidadPasajeros, velocidadPromedio);
        valorBase=VALOR_BASE;
        valorPasajero=VALOR_PASAJERO;
    }
    public Combi(String patente, float velocidadPromedio) {
        super(patente, 16, velocidadPromedio);
        valorBase=VALOR_BASE;
        valorPasajero=VALOR_PASAJERO;;
    }
    
    @Override
    public String toString() {
        return super.toString()+" Valor base:"+valorBase+" Valor pasajero"+valorPasajero;
    }
    
    @Override
    public float getValor(float km) {
        return valorBase+valorPasajero*km;
    }

    public float getValorBase() {
        return valorBase;
    }

    public float getValorPasajero() {
        return valorPasajero;
    }
}
