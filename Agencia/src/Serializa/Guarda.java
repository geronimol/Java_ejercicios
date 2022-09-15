/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializa;

import Agencia.Agencia;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Cliente
 */
public class Guarda {
    static public void guardaAgencia(Agencia a) {
        System.out.println("Serializando Agencia....");
        try { 
        FileOutputStream fout = new FileOutputStream("Agencia.ser");
        ObjectOutputStream outStream = new ObjectOutputStream(fout);
        outStream.writeObject(a);
        outStream.flush();
        outStream.close();
        } catch(Exception e){
        System.out.println("Exception: " + e);
        }
 } 
}
