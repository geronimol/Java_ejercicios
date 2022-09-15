/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viaje;

import Agencia.Simulador;
import Vehiculo.Vehiculo;
import java.io.Serializable;
import java.util.TreeSet;

/**
 *
 * @author Cliente
 */
public abstract class Viaje implements Comparable,Serializable {

    public enum Estado{
        PENDIENTE,EN_CURSO,FINALIZADO
    };
    private String nombre;
    private Destino destino;
    private Vehiculo vehiculo;
    private int cantPasajeros;
    private float kmRecorridos;
    private float valorTotal;
    private Estado estado;
    private Simulador s;
    //Constructor
    public Viaje(String nombre,Destino destino,Vehiculo vehiculo,int cantPasajeros) {
        this.nombre=nombre;
        this.destino=destino;
        this.vehiculo=vehiculo;
        this.cantPasajeros=cantPasajeros;
        vehiculo.setDisponible(false);
        this.estado=Estado.PENDIENTE;
    }
    
    @Override
    public int compareTo(Object t) {
        Viaje v=(Viaje)t;
        int compNombre=this.nombre.compareTo(v.nombre);
        return compNombre;
    }
    
    @Override
    public String toString() {
        return " Nombre: "+nombre+"\nDestino:"+destino.toString()+"\nVehiculo:"+
                vehiculo.toString()+" Cant Pasajeros:"+cantPasajeros+" Valor total:"+valorTotal;
    } 
    
    public void iniciar(){
        estado=Estado.EN_CURSO;
        vehiculo.setDisponible(false);
        s=new Simulador(this,destino.getKm());
        s.start();
        
    }
    
    public void detenerViajes() {
        s.interrupt();
        finalizar();
    }
    
    public void finalizar(){
        estado=Estado.FINALIZADO;
        vehiculo.setDisponible(true);
    }
    
    public float getVelocidadPromedio(){
        return vehiculo.getVelocidadPromedio();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setValorTotal(float total){
        valorTotal=total;
    }

    public void setEstado(Estado estado){
        this.estado=estado;
    }
    
    public Estado getEstado(){
        return estado;
    }
    
    public float getKmsRecorridos() {
        return kmRecorridos;
    }
    
    public float getKmsTotales() {
        return destino.getKm();
    }
    
    public int getCantPasajeros() {
        return cantPasajeros;
    }
    
    public String getTransporte() {
        return vehiculo.getClass().getName();
    }
    
    public float getValor() {
        return 0;
    }
    public float getValorTotal(){
        return valorTotal;
    }
    
    
    
}
