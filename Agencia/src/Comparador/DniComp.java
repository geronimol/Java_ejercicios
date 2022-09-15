/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparador;

import Viaje.ResponsableAbordo;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Cliente
 */
public class DniComp implements Serializable,Comparator<ResponsableAbordo>{

    @Override
    public int compare(ResponsableAbordo t, ResponsableAbordo t1) {
        if(t.getDni() > t1.getDni())
            return 1;
        else
            if(t.getDni() < t1.getDni())
                return -1;
            else 
                return 0;
    }
    
}
