/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validaciones;

/**
 *
 * @author Majo
 */
public class Validaciones {

    public static int entero(String s) {
        int i = -1;

        try {
            i = Integer.parseInt(s);

        } catch (NumberFormatException nfe) {
            System.out.println("Error, debe ingresar un valor numérico.");

        }
        return i;
    }

    public static float real(String s) {
        float f = 0;

        try {
            f = Float.parseFloat(s);

        } catch (NumberFormatException nfe) {
            System.out.println("Error, debe ingresar un valor numérico.");
        }
        return f;
    }

    public static long longg(String s) {
        long i = 0;

        try {
            i = Long.parseLong(s);

        } catch (NumberFormatException nfe) {
            System.out.println("Error, debe ingresar un valor numérico.");
        }
        return i;
    }

    public static boolean sonEspacios(String cad) {
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean sonCaracteresCorrectos(String cad) {
        return (cad.matches("[a-z,A-Z].*"));
    }

    public static boolean esMayorACero(long l) {
        return l > 0;
    }
   
    public static boolean esMayorACero(float f) {
        return f > 0;
    }
    public static boolean esMayorIgualACero(long l) {
        return l >= 0;
    }
}
