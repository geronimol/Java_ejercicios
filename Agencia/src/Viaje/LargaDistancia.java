/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viaje;

import Vehiculo.Vehiculo;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author Cliente
 */
public class LargaDistancia extends Viaje {
    private ArrayList<ResponsableAbordo> responsablesAbordo;
    private static float COSTO_RESP_ABORDO=1;
    private float costoRespAbordo;

    public LargaDistancia(String nombre, Destino destino, Vehiculo vehiculo,ArrayList<ResponsableAbordo> listaResponsablesAbordo,int cantPasajeros) {
        super(nombre, destino, vehiculo,cantPasajeros); 
        
        costoRespAbordo=COSTO_RESP_ABORDO;
        this.responsablesAbordo=listaResponsablesAbordo;
        for(ResponsableAbordo r: responsablesAbordo)
            r.setDisponible(false);
    }
    
    @Override
    public String toString() {
        return super.toString()+"\nResponsables a bordo: "+responsablesAbordo.toString();
    }
    
   public void iniciar(){
        super.iniciar();
        for(ResponsableAbordo r: responsablesAbordo)
            r.setDisponible(false);     
    }
   public void detenerViajes() {
        super.detenerViajes();
        finalizar();
    }
    public void finalizar(){
        super.finalizar();
        for(ResponsableAbordo r: responsablesAbordo){
            r.setDisponible(true);
        }
            
    }

    public float getValor() {
        return responsablesAbordo.size()*costoRespAbordo;
    }

    public ArrayList<ResponsableAbordo> getResponsablesAbordo() {
        return responsablesAbordo;
    }

}
