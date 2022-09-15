/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializa;

import SistemaCanjeMillas.SistemaCanjeMillas;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author Cliente
 */
//Carga archivo serializable.
public class Carga {

    static public SistemaCanjeMillas cargaAerolinea(SistemaCanjeMillas s) {

        System.out.println("Deserializando SistemaCanjeMillas...");
        try {
            FileInputStream fin = new FileInputStream("SistemaCM.ser");
            ObjectInputStream inStream = new ObjectInputStream(fin);
            s = (SistemaCanjeMillas) inStream.readObject();

        } catch (ClassNotFoundException e) {
            System.out.println("Exception: " + e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return s;
    }
}
