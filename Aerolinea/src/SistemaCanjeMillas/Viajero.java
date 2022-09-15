/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaCanjeMillas;

import Comparador.CompDestinos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Majo
 */
public abstract class Viajero implements Comparable, Serializable {

    private ArrayList<Producto> productosCanjeados;
    private TreeSet<Viaje> viajesCanjeados;
    private ArrayList<GeneradorDeMillas> generadores;
    private String nombre;
    private long dni, millasAcumuladas, millasCanjeadas;

    public Viajero(String nom, long dni, long millasAcumuladas) {
        nombre = nom;
        this.dni = dni;
        this.millasAcumuladas = millasAcumuladas;
        millasCanjeadas = 0;
        productosCanjeados = new ArrayList<>();
        viajesCanjeados = new TreeSet<>();
        generadores = new ArrayList<>();
    }

    public Viajero(long dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "| Dni: " + dni + "| Millas acumuladas: " + millasAcumuladas + "| Millas Canjeadas: " + millasCanjeadas + "| MillasTotales: " + getMillasTotales();
    }

    public long getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public long getMillasAcumuladas() {
        return millasAcumuladas;
    }

    public long getMillasTotales() {
        return millasAcumuladas + millasCanjeadas;
    }

    public long getMillasCanjeadas() {
        return millasCanjeadas;
    }

    public void setMillasCanjeadas(long millasCanjeadas) {
        this.millasCanjeadas += millasCanjeadas;
    }

    public void setMillasAcumuladas(long millasObtenidas) {
        millasAcumuladas = millasObtenidas;
    }

    public ArrayList<GeneradorDeMillas> getGeneradores() {
        return generadores;
    }

    public void setGeneradores(ArrayList<GeneradorDeMillas> generadores) {
        this.generadores = generadores;
    }

    public void registraProductoCanjeable(Producto producto) {
        if (productosCanjeados.contains(producto)) {
            for (Producto p : productosCanjeados) {
                if (producto.equals(p)) {
                    p.incrementaStock();
                }
            }
        } else {
            Producto productoCanjeado = new Producto(producto.getNombre(), producto.getDescripcion(), producto.getCostoMillas(), 1);
            productosCanjeados.add(productoCanjeado);
        }
    }

    public void registraViajeCanjeable(Viaje viaje) {
        viajesCanjeados.add(viaje);
    }

    @Override
    public int compareTo(Object o) {
        Viajero v = (Viajero) o;
        if (dni > v.getDni()) {
            return 1;
        } else if (dni < v.getDni()) {
            return -1;
        } else {
            return 0;
        }
    }

    //Agrega
    public void agregaGenerador(GeneradorDeMillas generador) {
        generadores.add(generador);
    }

    //Busca y devuelve un generador del viajero
    public GeneradorDeMillas buscaGeneradorDeMillas(String id) {
        for (Iterator it = generadores.iterator(); it.hasNext();) {
            GeneradorDeMillas generador = (GeneradorDeMillas) it.next();
            if (generador.getId().equals(id)) {
                return generador;
            }
        }
        return null;
    }

    public void muestraTotalMillasPorGeneradores() {
        long totalMillasConsumo = 0, totalMillasCombustible = 0, totalMillasHotel = 0, totalMillasPrimera = 0, totalMillasBusiness = 0, totalMillasTurista = 0;
        long gananciaEnMillas;
        String nombre;
        for (GeneradorDeMillas g : generadores) {
            nombre = g.getClass().getSimpleName();
            gananciaEnMillas = g.getGananciaMillas();
            switch (nombre) {
                case "Consumo":
                    totalMillasConsumo += gananciaEnMillas;
                    break;
                case "Combustible":
                    totalMillasCombustible += gananciaEnMillas;
                    break;
                case "Hotel":
                    totalMillasHotel += gananciaEnMillas;
                    break;
                case "Turista":
                    totalMillasTurista += gananciaEnMillas;
                    break;
                case "Business":
                    totalMillasBusiness += gananciaEnMillas;
                    break;
                case "Primera":
                    totalMillasPrimera += gananciaEnMillas;
                    break;
            }

        }
        System.out.println("Consumo: " + totalMillasConsumo);
        System.out.println("Combustible: " + totalMillasCombustible);
        System.out.println("Hotel: " + totalMillasHotel);
        System.out.println("Turista: " + totalMillasTurista);
        System.out.println("Business: " + totalMillasBusiness);
        System.out.println("Primera: " + totalMillasPrimera);
    }

    public HashMap<String, Integer> buscaDestinosRepetidos(HashMap<String, Integer> repetidos) {
        Lugar destino;
        int cont;
        //Busca los generadores tipo viaje y los guarda en el ArrayList.
        for (GeneradorDeMillas g : generadores) {
            if (g instanceof Viaje) {
                destino = ((Viaje) g).getDestino();
                cont = repetidos.containsKey(destino.getNombre()) ? repetidos.get(destino.getNombre()) : 0;
                repetidos.put(destino.getNombre(), cont + 1);

            }
        }

        //Agrega los destinos canjeados a la lista. 
        for (Viaje v : viajesCanjeados) {
            destino = v.getDestino();
            cont = repetidos.containsKey(destino.getNombre()) ? repetidos.get(destino.getNombre()) : 0;
            repetidos.put(destino.getNombre(), cont + 1);
        }

        return repetidos;

    }
}
