/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparador;

import SistemaCanjeMillas.Viajero;
import java.util.Comparator;

/**
 *
 * @author Majo
 */
//Comparador de millas totales para Reportes.
public class CompMillasTotales implements Comparator<Viajero> {

    @Override
    public int compare(Viajero v, Viajero v1) {
        if(v.getMillasTotales() > v1.getMillasTotales())
            return -1;
        else
            return 1;
    };
}
