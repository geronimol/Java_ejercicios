/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaCanjeMillas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author Majo
 */
public class TablaDeDestinos implements Serializable {

    private static TablaDeDestinos tablaDeDestinos;
    private TreeSet<Lugar> lugares;
    private TreeSet<FilaDeDestinos> filas;

    public TablaDeDestinos() {
        filas = new TreeSet<>();
        lugares = new TreeSet<>();
    }

    public static TablaDeDestinos getInstanceTablaDeDestinos() {
        if (tablaDeDestinos == null) {
            tablaDeDestinos = new TablaDeDestinos();
        }
        return tablaDeDestinos;
    }

    public void agregaLugar(String nom, String descripcion) {
        lugares.add(new Lugar(nom, descripcion));
    }

    void muestraFilasDestino() {
        for (FilaDeDestinos fila : filas) {
            System.out.println(fila.toString());
        }
    }

    //ALTA FILA DESTINO
    public void agregaFilaDeDestino(String nomOrigen, String nomDestino, long millasCanje, long millasObtenidas) {
        Lugar origen = buscaLugar(nomOrigen);
        Lugar destino = buscaLugar(nomDestino);
        if (origen != null && destino != null) {
            FilaDeDestinos fila = new FilaDeDestinos(origen, destino, millasCanje, millasObtenidas);
            filas.add(fila);
        } else {
            System.out.println("No se encontro origen/destino");
        }
    }

    //BAJA  FILA DESTINO
    public boolean eliminaFilaDeDestino(String nomOrigen, String nomDestino) {
        Lugar origen = buscaLugar(nomOrigen);
        Lugar destino = buscaLugar(nomDestino);
        if (origen != null && destino != null) {
            FilaDeDestinos fila = new FilaDeDestinos(origen, destino);
            if (filas.contains(fila)) {
                filas.remove(fila);
                return true;
            } else {
                System.out.println("No se encontro fila de destino");
                return false;
            }
        } else {
            System.out.println("No se encontro origen/destino");
            return false;
        }
    }

    public void cargaLugares() throws FileNotFoundException {
        try (BufferedReader in = new BufferedReader(new FileReader("Lugares.txt"))) {
            String s, nombre, descripcion;
            String[] s3;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                nombre = s3[0];
                descripcion = s3[1];
                agregaLugar(nombre, descripcion);
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Productos.");
        }
    }

    public Lugar buscaLugar(String nombreLugar) {
        for (Iterator it = lugares.iterator(); it.hasNext();) {
            Lugar lugar = (Lugar) it.next();
            if (lugar.getNombre().equals(nombreLugar)) {
                return lugar;
            }
        }
        return null;
    }

    public boolean verificaLugar(String origen) {
        Lugar lugar = new Lugar(origen);
        return lugares.contains(lugar);
    }

    public void muestraLugares() {
        System.out.println(lugares.toString());
    }

    public long getGananciaEnMillas(Lugar origen, Lugar destino) {
        FilaDeDestinos f = new FilaDeDestinos(origen, destino);
        for (FilaDeDestinos fila : filas) {
            System.out.println(filas.toString());
            if (fila.equals(f)) {
                return fila.getGananciaMillas();
            }
        }
        return 0;
    }

    public long getCostoEnMillas(Lugar origen, Lugar destino) {
        FilaDeDestinos f = new FilaDeDestinos(origen, destino);
        for (FilaDeDestinos fila : filas) {
            if (fila.equals(f)) {
                return fila.getCostoEnMillas();
            }
        }
        return 0;
    }

    public boolean verificaFilaDestino(String origen, String destino) {
        Lugar o = buscaLugar(origen);
        Lugar d = buscaLugar(destino);
        FilaDeDestinos f = new FilaDeDestinos(o, d);
        return filas.contains(f);
    }

    public FilaDeDestinos buscaFilaDestinos(String origen, String destino) {
        Lugar o = buscaLugar(origen);
        Lugar d = buscaLugar(destino);
        FilaDeDestinos f = new FilaDeDestinos(o, d);
        for (FilaDeDestinos fila : filas) {
            if (fila.equals(f)) {
                return fila;
            }
        }
        return null;
    }

    void busquedaTablaDestinoOrigen(String origen) {
        String o;
        FilaDeDestinos fila;
        Iterator it = filas.iterator();
        while (it.hasNext()) {
            fila = (FilaDeDestinos) it.next();
            o = fila.getNombreOrigen();
            if (o.equals(origen)) {
                System.out.println(fila.toString());
            }
        }
    }

    void busquedaTablaDestinoDestino(String destino) {
        String d;
        FilaDeDestinos fila;
        Iterator it = filas.iterator();
        while (it.hasNext()) {
            fila = (FilaDeDestinos) it.next();
            d = fila.getNombreDestino();
            if (d.equals(destino)) {
                System.out.println(fila.toString());
            }
        }
    }

}
