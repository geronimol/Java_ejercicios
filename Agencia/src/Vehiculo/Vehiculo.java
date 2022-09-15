/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehiculo;

import java.io.Serializable;

/**
 *
 * @author Cliente
 */
public abstract class Vehiculo implements Comparable,Serializable {
    private String patente;
    private int capacidadPasajeros;
    private float velocidadPromedio;
    private boolean disponible;
    //Constructor
    public Vehiculo(String patente, int capacidadPasajeros, float velocidadPromedio) {
        this.patente=patente;
        this.capacidadPasajeros=capacidadPasajeros;
        this.velocidadPromedio=velocidadPromedio;
        this.disponible=true;
    }

    public Vehiculo(String patenteVehiculo) {
        patente=patenteVehiculo;
        this.disponible=true;
    }

    public Vehiculo(String patente, float velocidadPromedio) {
        this.patente=patente;
        this.velocidadPromedio=velocidadPromedio;
        this.disponible=true;
    }

    @Override
    public int compareTo(Object t) {
        Vehiculo v=(Vehiculo)t;
        int compPatente=this.patente.compareTo(v.patente);
        return compPatente;
    }
    
    @Override
    public String toString() {
        return " Patente:"+patente+" Nombre:"+this.getClass().getName()+" Capacidad:"+capacidadPasajeros+" Velocidad promedio:"+velocidadPromedio;
 } 
    
    public String getPatente() {
        return patente;
    }
    
    public int getCapacidadPasajeros(){
        return capacidadPasajeros;
    }
    
    public float getVelocidadPromedio(){
        return velocidadPromedio;
    }
    
    public void setPatente(String patente){
        this.patente=patente;
    }
    
    public void setCapacidadPasajeros(int capacidadPasajeros){
        this.capacidadPasajeros=capacidadPasajeros;
    }
    
    public void setVelocidadPromedio(float velocidadPromedio){
        this.velocidadPromedio=velocidadPromedio;
    }
    
    public void setDisponible(boolean b){
        disponible=b;
    }
    public boolean estaDisponible() {
        return disponible;
    }

    public float getValor() {
        return 0;
    }

    public float getValor(float km) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
