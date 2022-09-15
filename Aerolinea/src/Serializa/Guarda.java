/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializa;

import SistemaCanjeMillas.SistemaCanjeMillas;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Cliente
 */
//Serializa archivo.
public class Guarda {

    static public void guardaAerolinea(SistemaCanjeMillas s) {
        System.out.println("Serializando SistemaCanjeMillas....");
        try {
            FileOutputStream fout = new FileOutputStream("SistemaCM.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(fout);
            outStream.writeObject(s);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
