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
public class CocheCama extends Colectivo {
    private int plazas,maxPlazas;
    private static int MAX_PLAZAS=26;
    private static float VALOR_PASAJERO=1;
    private static float VALOR_PLAZAS=1;
    private float valorPasajero;
    private float valorPlazas;
    //Constructor        
    public CocheCama(String patente, int capacidadPasajeros, float velocidadPromedio) {
        super(patente, capacidadPasajeros, velocidadPromedio);
        plazas=0;
        maxPlazas=MAX_PLAZAS;
        valorPasajero=VALOR_PASAJERO;
        valorPlazas=VALOR_PLAZAS;
    }
    public CocheCama(String patente, float velocidadPromedio) {
        super(patente, 32, velocidadPromedio);
        plazas=0;
        maxPlazas=MAX_PLAZAS;
        valorPasajero=VALOR_PASAJERO;
        valorPlazas=VALOR_PLAZAS;
    }
    
    @Override
    public String toString() {
        return super.toString()+" Valor pasajero:"+valorPasajero+" Valor plazas:"+valorPlazas+
                " Plazas totales:"+maxPlazas+" Plazas ocupadas:"+plazas;
    }
    
    public void setPlazas(int plazas){
        this.plazas=plazas;
    }

    public int getMaxPlazas() {
        return maxPlazas;
    }
    
    @Override
    public float getValor(float km) {
        return valorPasajero*km+plazas*valorPlazas;
    }

    public float getValorPasajero() {
        return valorPasajero;
    }

    public float getPlazas() {
        return plazas;}

    public float getValorPlazas() {
        return valorPlazas;
    }
}
