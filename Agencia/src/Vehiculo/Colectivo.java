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
public abstract class Colectivo extends Vehiculo {
    
    public Colectivo(String patente, int capacidadPasajeros, float velocidadPromedio) {
        super(patente, capacidadPasajeros, velocidadPromedio);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
