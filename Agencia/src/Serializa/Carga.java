/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializa;

import Agencia.Agencia;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author Cliente
 */
public class Carga {
    static public Agencia cargaAgencia(Agencia a) {
    
        System.out.println("Deserializando Agencia...");
        try {
        FileInputStream fin = new FileInputStream("Agencia.ser");
        ObjectInputStream inStream = new ObjectInputStream(fin);
        a = (Agencia)inStream.readObject();

        //Agencia a = (Agencia)inStream.readObject();
        } catch(ClassNotFoundException e) {
        System.out.println("Exception: " + e);
        }
        catch(Exception e) {
        System.out.println("Exception: " + e);
        }
        return a;
 } 
}
