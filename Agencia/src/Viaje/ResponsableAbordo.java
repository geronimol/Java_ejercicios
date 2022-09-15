/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viaje;

import java.io.Serializable;

/**
 *
 * @author Cliente
 */
public class ResponsableAbordo implements Serializable {
    private String nombre;
    private int dni;
    private float sueldo;
    private boolean disponible;
    private float kmRecorridos;
    //Constructor
    public ResponsableAbordo(String nombre, int dni, float sueldo) {
        this.nombre=nombre;
        this.dni=dni;
        this.sueldo=sueldo;
        this.disponible=true;
        kmRecorridos=0;
    }

   /* @Override
    public int compareTo(Object t) {
        ResponsableAbordo r=(ResponsableAbordo)t;
        if(this.dni>r.dni)
            return 1;
        else
            if(this.dni<r.dni)
                return -1;
            else
                return 0;
    }*/
    
    @Override
    public String toString() {
        return " DNI:"+dni+" Nombre:"+nombre+" Sueldo:"+sueldo;
 } 
    
    public String getNombre(){
        return nombre;
    }
    public int getDni() {
        return dni;
    }
    public float getSueldo(){
        return sueldo;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setDni(int dni){
        this.dni=dni;
    }
    public void setSueldo(float sueldo){
        this.sueldo=sueldo;
    }
    
    public void setDisponible(boolean b){
        disponible=b;
    }
    public boolean estaDisponible() {
        return disponible;
    }
    public void sumaKmRecorridos(float k){
        kmRecorridos+=k;
    }
    public float getKmRecorridos(){
        return kmRecorridos;
    }
}
