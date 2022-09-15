/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agencia;

import Viaje.Viaje;
import Viaje.Viaje.Estado;
import java.io.Serializable;

/**
 *
 * @author Cliente
 */
public class Simulador extends Thread implements Serializable{
    private Viaje v;
    private static int SEGUNDOS=1;
    private float kmsRecorridos,kmsTotales;
    
    public Simulador(Viaje viaje,float kmsTot){
        v=viaje;
        kmsRecorridos=0;
        kmsTotales=kmsTot;
    }
    @Override
    public void run(){
        while(kmsRecorridos<kmsTotales){
            esperar(SEGUNDOS);
            kmsRecorridos+=v.getVelocidadPromedio();
            //v.setKmsRecorridos(kmsRecorridos);
            System.out.println("Nombre:"+v.getNombre()+"    Kms recorridos:"+
                        kmsRecorridos+"    %"+calculaPorcent()+"    Estado:"+v.getEstado());
        }
        v.finalizar();
        System.out.println("Nombre:"+v.getNombre()+"    Kms recorridos:"+
                        kmsTotales+"    %100"+"    Estado:"+v.getEstado());
    }
    
    private void esperar(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

    private int calculaPorcent() {
        return (int) ((kmsRecorridos*100)/kmsTotales);
    }
}
