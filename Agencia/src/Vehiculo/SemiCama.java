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
public class SemiCama extends Colectivo {
    private static float VALOR_PASAJERO=1;
    private float valorPasajero;
    public SemiCama(String patente, int capacidadPasajeros, float velocidadPromedio) {
        super(patente, capacidadPasajeros, velocidadPromedio);
        valorPasajero=VALOR_PASAJERO;
    }
    public SemiCama(String patente, float velocidadPromedio) {
        super(patente, 40, velocidadPromedio);
        valorPasajero=VALOR_PASAJERO;
    }
    
    @Override
    public String toString() {
        return super.toString()+" Valor pasajero:"+valorPasajero;
    }
    
    @Override
    public float getValor(float km) {
        return valorPasajero*km;
    }

    public float getValorPasajero() {
        return valorPasajero;
    }
}
