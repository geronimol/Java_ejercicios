/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparador;

import SistemaCanjeMillas.Lugar;
import java.util.Comparator;

/**
 *
 * @author Cliente
 */
public class CompDestinos implements Comparator<Lugar>{

    @Override
    public int compare(Lugar t, Lugar t1) {
        return t.getNombre().compareTo(t1.getNombre());
    }
    
}
