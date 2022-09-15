/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaCanjeMillas;

import Comparador.CompMillasTotales;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author Majo
 */
public class Reportes implements Serializable {

    private TreeSet<Viajero> viajeros;

    public Reportes(TreeSet<Viajero> viajeros) {
        this.viajeros = viajeros;
    }

    //REPORTES
    public void rankingViajerosPorMillasTotales() {
        TreeSet<Viajero> rankingViajeros = new TreeSet<Viajero>(new CompMillasTotales());
        System.out.println("RANKING DE LOS VIAJEROS POR MILLAS TOTALES:");
        for (Viajero v : viajeros) {
            rankingViajeros.add(v);
        }

        for (Viajero v : rankingViajeros) {
            System.out.println(v.toString());
            v.muestraTotalMillasPorGeneradores();
        }

    }

    public void listadoDeDestinos() {
        HashMap<String, Integer> repetidos = new HashMap<String, Integer>();

        for (Viajero v : viajeros) {
            repetidos = v.buscaDestinosRepetidos(repetidos);
        }

        System.out.println(" DESTINOS CONSUMIDOS AL MENOS 5 VECES: ");
        //Muestra el HashMap con los destinos consumidos por lo menos 5 veces.
        for (Map.Entry<String, Integer> entry : repetidos.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value >= 5) {
                System.out.println("Destino: " + key.toString() + " VECES CONSUMIDO: " + value);
            }
        }
    }

    public void graficoGeneradores() {
        ArrayList<GeneradorDeMillas> generadores = new ArrayList<>();
        for (Viajero v : viajeros) {
            generadores.addAll(v.getGeneradores());
        }

        long gananciaMillas, millasTotales = 0, millasConsumo = 0, millasCombustible = 0, millasHotel = 0, millasViajes = 0;
        int cantConsumo = 0, cantCombustible = 0, cantHotel = 0, cantViajes = 0, cantTotal;
        String nombre;
        for (GeneradorDeMillas g : generadores) {
            nombre = g.getClass().getSimpleName();
            gananciaMillas = g.getGananciaMillas();
            switch (nombre) {
                case "Consumo":
                    millasConsumo += gananciaMillas;
                    cantConsumo++;
                    break;
                case "Combustible":
                    millasCombustible += gananciaMillas;
                    cantCombustible++;
                    break;
                case "Hotel":
                    millasHotel += gananciaMillas;
                    cantHotel++;
                    break;
                case "Turista":
                    millasViajes += gananciaMillas;
                    cantViajes++;
                    break;
                case "Business":
                    millasViajes += gananciaMillas;
                    cantViajes++;
                    break;
                case "Primera":
                    millasViajes += gananciaMillas;
                    cantViajes++;
                    break;
            }

        }

        millasTotales = millasConsumo + millasCombustible + millasHotel + millasViajes;
        cantTotal = cantConsumo + cantCombustible + cantHotel + cantViajes;

        System.out.println("Consumo: " + millasConsumo + " Cantidad: " + cantConsumo + " Millas Promedio: " + calculaPromedio(millasConsumo, cantConsumo));
        grafico(calculaPorcentaje(millasConsumo, millasTotales));
        System.out.println("Combustible: " + millasCombustible + " Cantidad: " + cantCombustible + " Millas Promedio: " + calculaPromedio(millasCombustible, cantCombustible));
        grafico(calculaPorcentaje(millasCombustible, millasTotales));
        System.out.println("Hotel: " + millasHotel + " Cantidad: " + cantHotel + " Millas Promedio: " + calculaPromedio(millasHotel, cantHotel));
        grafico(calculaPorcentaje(millasHotel, millasTotales));
        System.out.println("Viajes: " + millasViajes + " Cantidad: " + cantViajes + " Millas Promedio: " + calculaPromedio(millasViajes, cantViajes));
        grafico(calculaPorcentaje(millasViajes, millasTotales));
    }

    private void grafico(float porcentaje) {
        int tamanio;
        tamanio = (int) porcentaje;
        System.out.println();
        for (int i = 0; i < tamanio; i++) {
            System.out.print((char) 27 + "[32m|" + (char) 27 + "[0m");
        }
        System.out.print(" %" + porcentaje);
        System.out.println(" ");
        System.out.println(" ");

    }

    private float calculaPromedio(long millas, long cantidad) {
        try {
            return (float) (millas / cantidad);
        } catch (ArithmeticException ex) {
            return 0;
        }
    }

    private float calculaPorcentaje(long millas, long total) {
        try {
            return (float) ((millas * 100) / total);
        } catch (ArithmeticException ex) {
            return 0;
        }
    }
}
