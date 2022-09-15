/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaCanjeMillas;

import Excepciones.InstanciaExcepcion;
import Interfaz.Menu;
import Serializa.Carga;
import Serializa.Guarda;
import SistemaCanjeMillas.GeneradorDeMillas.Estado;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import cargaxml.RegistroDeViajero;
import cargaxml.VersionesSAX;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.Serializable;

/**
 *
 * @author Majo
 */
public class SistemaCanjeMillas implements Serializable {

    private static SistemaCanjeMillas sistemaCanjeMillas;
    private TreeSet<Viaje> viajesCanjeables;
    private TreeSet<Producto> productos;
    private ArrayList<RegistroDeViajero> registrosDeViajeros;
    private TreeSet<Viajero> viajeros;
    private TreeSet<GeneradorDeMillas> generadores;
    private TablaDeDestinos tablaDeDestinos;
    private Reportes reportes;

    //CONSTRUCTOR
    public SistemaCanjeMillas() {
        viajesCanjeables = new TreeSet<>();
        productos = new TreeSet<>();
        viajeros = new TreeSet<>();
        generadores = new TreeSet<>();
        tablaDeDestinos = TablaDeDestinos.getInstanceTablaDeDestinos();
        reportes = new Reportes(viajeros);
    }

    //Crea una única instancia de SistemaCanjeMillas
    public static SistemaCanjeMillas getInstanciaSingleton() throws InstanciaExcepcion {
        if (sistemaCanjeMillas == null) {
            sistemaCanjeMillas = new SistemaCanjeMillas();
        } else {
            throw new InstanciaExcepcion("No se puede crear el objeto, ya existe una instancia");
        }
        return sistemaCanjeMillas;
    }

    //MAIN
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, InstanciaExcepcion {
        SistemaCanjeMillas s;
        s = SistemaCanjeMillas.getInstanciaSingleton();
        //Carga archivo serializado.
        s = Carga.cargaAerolinea(s);
        //Inicia el menu por salida estandar.
        Menu m = new Menu(s);
        m.iniciar();
        //Guarda archivo serializado.
        Guarda.guardaAerolinea(s);
    }

    //ALTAS
    //VIAJERO NORMAL
    public void agregaViajero(String nom, long dni, long millasAcumuladas) {
        Normal viajero = new Normal(nom, dni, millasAcumuladas);
        if (viajeros.contains(viajero)) {
            System.out.println("Ya existe un viajero con ese dni");
        } else {
            viajeros.add(viajero);
        }
    }

    //GENERADOR DE HOTEL
    public void agregaGeneradorHotel(String nombreLugar, String categoria, float precioNoche, int cantNoches, String id, String nombre, String descripcion, float factorDeMillas, Estado e) {
        Lugar lugar = buscaLugar(nombreLugar);
        if (lugar != null) {
            generadores.add(new Hotel(lugar, categoria, precioNoche, cantNoches, id, nombre, descripcion, factorDeMillas, e));
        } else {
            System.out.println("No se encontro lugar");
        }
    }

    //GENERADOR DE COMBUSTIBLE
    public void agregaGeneradorCombustible(String id, String nombre, String descripcion, float factorDeMillas, Estado e) {
        generadores.add(new Combustible(id, nombre, descripcion, factorDeMillas, e));
    }

    //GENERADOR DE CONSUMO
    public void agregaGeneradorConsumo(String id, String nombre, String descripcion, float factorDeMillas, Estado e) {
        generadores.add(new Consumo(id, nombre, descripcion, factorDeMillas, e));
    }

    //GENERADOR DE VIAJERO TIPO PRIMERA
    public void agregaGeneradorPrimera(Primera primera) {
        generadores.add(primera);
    }

    //GENERADOR DE VIAJERO TIPO TURISTA
    public void agregaGeneradorTurista(Turista turista) {
        generadores.add(turista);
    }

    //GENERADOR DE VIAJERO TIPO BUSINESS
    public void agregaGeneradorBusiness(Business business) {
        generadores.add(business);
    }

    // VIAJE CANJEABLE TURISTA
    public void agregaViajeCanjeableTurista(Turista t) {
        viajesCanjeables.add(t);
    }

    // VIAJE CANJEABLE BUSINESS
    public void agregaViajeCanjeableBusiness(Business b) {
        viajesCanjeables.add(b);
    }

    // VIAJE CANJEABLE PRIMERA
    public void agregaViajeCanjeablePrimera(Primera p) {
        viajesCanjeables.add(p);
    }

    //TABLA DE DESTINOS
    public void agregaFilaDestinos(String nomOrigen, String nomDestino, long millasCanje, long millasObtenidas) {
        tablaDeDestinos.agregaFilaDeDestino(nomOrigen, nomDestino, millasCanje, millasObtenidas);
    }

    //BAJAS
    //VIAJERO
    public boolean eliminaViajero(long dni) {
        Viajero v = buscaViajero(dni);
        if (v != null) {
            viajeros.remove(v);
            return true;
        } else {
            System.out.println("No se encuentra viajero con ese DNI");
            return false;
        }
    }

    //GENERADOR DE MILLAS
    public boolean eliminaGeneradorDeMillas(String id) {
        GeneradorDeMillas g = buscaGenerador(id);
        if (generadores.contains(g)) {
            generadores.remove(g);
            return true;
        } else {
            System.out.println("Generador inexistente");
            return false;
        }

    }

    //VIAJE CANJEABLE
    public boolean eliminaViajeCanjeable(String id) {
        Viaje v = buscaViajeCanjeable(id);
        if (viajesCanjeables.contains(v)) {
            viajesCanjeables.remove(v);
            return true;
        } else {
            System.out.println("El viaje es inexistente");
            return false;
        }
    }

    //TABLA DE DESTINOS
    public boolean eliminaTablaDestinos(String origen, String destino) {
        return tablaDeDestinos.eliminaFilaDeDestino(origen, destino);
    }

    public void muestraViajesCanjeables() {
        Iterator it = viajesCanjeables.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public Viaje buscaViajeCanjeable(String id) {
        for (Iterator it = viajesCanjeables.iterator(); it.hasNext();) {
            Viaje viaje = (Viaje) it.next();
            if (viaje.getId().equals(id)) {
                return viaje;
            }
        }
        return null;
    }

    //Busca un generador y lo devuelve.
    public GeneradorDeMillas buscaGenerador(String id) {
        for (Iterator it = generadores.iterator(); it.hasNext();) {
            GeneradorDeMillas generador = (GeneradorDeMillas) it.next();
            if (generador.getId().equals(id)) {
                return generador;
            }
        }
        return null;
    }

    //MUESTRA LA COLECCION DE VIAJEROS
    public void muestraViajeros() {
        Iterator it = viajeros.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    public void muestraGeneradores() {
        Iterator it = generadores.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    //CARGA LOS PRODUCTOS DEL ARCHIVO TXT
    public void cargaProductos() throws FileNotFoundException {
        try (BufferedReader in = new BufferedReader(new FileReader("Productos.txt"))) {
            String s, nombre, descripcion;
            String[] s3;
            long cantMillas;
            int stock;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                nombre = s3[0];
                descripcion = s3[1];
                cantMillas = Long.parseLong(s3[2]);
                stock = Integer.parseInt(s3[3]);
                agregaProducto(nombre, descripcion, cantMillas, stock);
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Productos.");
        }
    }

    private void agregaProducto(String nombre, String descripcion, long cantMillas, int stock) {
        productos.add(new Producto(nombre, descripcion, cantMillas, stock));
    }

    public void muestraProductos() {
        System.out.println(productos.toString());
    }

    public void cargaLugares() throws FileNotFoundException {
        tablaDeDestinos.cargaLugares();
    }

    public void muestraLugares() {
        tablaDeDestinos.muestraLugares();
    }

    //Carga el archivo XML en una lista.
    public void cargaRegistroDeViajeros() throws ParserConfigurationException, SAXException, IOException {
        VersionesSAX v = new VersionesSAX();
        v.CargaRegistrosDeViajeros();
        registrosDeViajeros = v.getRegistrosDeViajeros();
    }

    //Muestra la lista con el contenido del archivo XML.
    public void muestraRegistroDeViajeros() {
        for (RegistroDeViajero v : registrosDeViajeros) {
            System.out.println(v);
        }
    }

    // Lee el archivo XML, calcula las millas obtenidas y agrega generadores al viajero.
    public void registrarMillas() {
        long dni;
        String id;
        float valorNumerico;
        for (RegistroDeViajero v : registrosDeViajeros) {
            dni = v.getDNI();
            if (verificaDni(dni)) {
                Viajero viajero = buscaViajero(dni);
                id = v.getID();
                if (verificaId(id)) {
                    GeneradorDeMillas g = buscaGenerador(id);
                    if (g.getEstado() == Estado.DISPONIBLE) {
                        valorNumerico = v.getValorNumerico();
                        if (valorNumerico >= 0) {
                            GeneradorDeMillas generador = g.copiaGen(g);
                            viajero.setMillasAcumuladas((viajero.getMillasAcumuladas() + (long) generador.generaMillas(valorNumerico)));
                            agregaGeneradorAViajero(viajero, generador);
                            if (viajero instanceof Normal && viajero.getMillasTotales() >= 10000) {
                                actualizaViajero(viajero);
                            }
                        } else {
                            System.out.println("El archivo XML contiene dos valores numericos");
                        }
                    } else {
                        System.out.println("No se encuentra disponible generador: " + g.getId());
                    }
                } else {
                    System.out.println("No se encuentra ID: " + id);
                }
            } else {
                System.out.println("No se encuentra DNI: " + dni);
            }
        }
    }

    private boolean verificaDni(long dni) {
        if (buscaViajero(dni) == null) {
            return false;
        } else {
            return true;
        }
    }

    public Viajero buscaViajero(long dni) {
        for (Iterator it = viajeros.iterator(); it.hasNext();) {
            Viajero viajero = (Viajero) it.next();
            if (viajero.getDni() == dni) {
                return viajero;
            }
        }
        return null;
    }

    private boolean verificaId(String id) {
        if (buscaGenerador(id) == null) {
            return false;
        } else {
            return true;
        }
    }

    private void agregaGeneradorAViajero(Viajero viajero, GeneradorDeMillas generador) {
        viajero.agregaGenerador(generador);
    }

    public Lugar buscaLugar(String nom) {
        return tablaDeDestinos.buscaLugar(nom);
    }

    public boolean verificaLugar(String origen) {
        return tablaDeDestinos.verificaLugar(origen);
    }

    public Producto buscaProducto(String nombreProducto) {
        Producto p = new Producto(nombreProducto);
        for (Producto prod : productos) {
            if (prod.equals(p)) {
                return prod;
            }
        }
        return null;
    }

    //Cambia un viajero de NORMAL a FRECUENTE.
    private void actualizaViajero(Viajero viajero) {
        Frecuente frecuente = new Frecuente(viajero.getNombre(), viajero.getDni(), viajero.getMillasAcumuladas());
        frecuente.setMillasCanjeadas(viajero.getMillasCanjeadas());
        frecuente.setGeneradores(viajero.getGeneradores());
        viajeros.remove(viajero);
        viajeros.add(frecuente);
    }

    // CANJE DE MILLAS EN PRODUCTOS.
    public void canjeDeProducto(long dni, String nombreProducto) {
        Viajero viajero = buscaViajero(dni);
        Producto producto = buscaProducto(nombreProducto);
        long millasAcumuladas, costoMillas;
        if (viajero != null && producto != null) {
            millasAcumuladas = viajero.getMillasAcumuladas();
            costoMillas = producto.getCostoMillas();
            if (millasAcumuladas >= costoMillas) {
                if (producto.getStock() > 0) {
                    viajero.setMillasAcumuladas(millasAcumuladas - costoMillas);
                    viajero.setMillasCanjeadas(costoMillas);
                    producto.decrementaStock();
                    viajero.registraProductoCanjeable(producto);
                }
                if (producto.getStock() == 0) {
                    productos.remove(producto);
                }
            } else {
                System.out.println("No se tienen los suficientes puntos para canjear este producto");
            }
        } else {
            System.out.println("NO EXISTE VIAJERO / PRODUCTO");
        }
    }

    //Canjea viajes canjeables.
    public void canjeDeViajes(String id, long dni) {
        Viajero viajero = buscaViajero(dni);
        Viaje viaje = buscaViajeCanjeable(id);
        long millasAcumuladas, costoMillas;
        if (viajero != null && viaje != null) {
            millasAcumuladas = viajero.getMillasAcumuladas();
            costoMillas = viaje.getCostoEnMillas();
            if (millasAcumuladas >= costoMillas) {
                viajero.setMillasAcumuladas(millasAcumuladas - costoMillas);
                viajero.setMillasCanjeadas(costoMillas);
                viajero.registraViajeCanjeable(viaje);
                viajesCanjeables.remove(viaje);
                System.out.println("Viaje Canjeado!");
            } else {
                System.out.println("No se tienen los suficientes puntos para canjear este producto");
            }
        } else {
            System.out.println("NO EXISTE VIAJERO / VIAJE");
        }
    }

    //Reportes
    public void rankingViajerosPorMillasTotales() {
        reportes.rankingViajerosPorMillasTotales();
    }

    public void listadoDeDestinos() {
        reportes.listadoDeDestinos();
    }

    public void graficoGeneradores() {
        reportes.graficoGeneradores();
    }

    public void muestraTablaDeDestinos() {
        tablaDeDestinos.muestraFilasDestino();
    }

    public void modificaMillasAcumuladas(Viajero v, long millasMod) {
        v.setMillasAcumuladas(millasMod);
    }

    public void modificaNombreGenerador(GeneradorDeMillas g, String nombre) {
        g.setNombre(nombre);
    }

    public void modificaDescripcionGenerador(GeneradorDeMillas g, String descripcion) {
        g.setDescripcion(descripcion);
    }

    public void modificaFactorDeMillasGenerador(GeneradorDeMillas g, long modfactor) {
        g.setFactorDeMillas(modfactor);
    }

    public void modificaEstadoGenerador(GeneradorDeMillas g, Estado e) {
        g.setEstado(e);
    }

    public boolean verficaFilaDestino(String origen, String destino) {
        return tablaDeDestinos.verificaFilaDestino(origen, destino);
    }

    public FilaDeDestinos buscaFiladeDestinos(String origen, String destino) {
        return tablaDeDestinos.buscaFilaDestinos(origen, destino);
    }

    public void modificaMillasObtenidasFilaDestinos(FilaDeDestinos fila, long millasOb) {
        fila.setGananciaMillas(millasOb);
    }

    public void modificaMillasCanjeFilaDestinos(FilaDeDestinos fila, long millasCa) {
        fila.setCostoEnMillas(millasCa);
    }

    //BUSQUEDAS
    public void busquedaViajeroNombre(String nombre) {
        int lenght = nombre.length();
        String nombreViajero, subNombre;
        Viajero viajero;
        Iterator it = viajeros.iterator();
        while (it.hasNext()) {
            viajero = (Viajero) it.next();
            nombreViajero = (viajero.getNombre()).toUpperCase();
            if (nombreViajero.length() >= lenght) {
                subNombre = nombreViajero.substring(0, lenght);
                if (subNombre.equals(nombre.toUpperCase())) {
                    System.out.println(viajero.toString());
                }
            }
        }
    }

    public void busquedaViajeroDNI(String dni) {
        int lenght = dni.length();
        String dniViajero, subDni;
        Viajero viajero;
        Iterator it = viajeros.iterator();
        while (it.hasNext()) {
            viajero = (Viajero) it.next();
            dniViajero = String.valueOf(viajero.getDni());
            if (dniViajero.length() >= lenght) {
                subDni = dniViajero.substring(0, lenght);
                if (subDni.equals(dni)) {
                    System.out.println(viajero.toString());
                }
            }
        }
    }

    public void busquedaGeneradorDeMillasNombre(String nombre) {
        int lenght = nombre.length();
        String nombreGenerador, subNombre;
        GeneradorDeMillas generador;
        Iterator it = generadores.iterator();
        while (it.hasNext()) {
            generador = (GeneradorDeMillas) it.next();
            nombreGenerador = (generador.getNombre()).toUpperCase();
            if (nombreGenerador.length() >= lenght) {
                subNombre = nombreGenerador.substring(0, lenght);
                if (subNombre.equals(nombre.toUpperCase())) {
                    System.out.println(generador.toString());
                }
            }
        }
    }

    public void busquedaGeneradorDeMillasID(String id) {
        int lenght = id.length();
        String idGenerador, subId;
        GeneradorDeMillas generador;
        Iterator it = generadores.iterator();
        while (it.hasNext()) {
            generador = (GeneradorDeMillas) it.next();
            idGenerador = String.valueOf(generador.getId());
            if (idGenerador.length() >= lenght) {
                subId = idGenerador.substring(0, lenght);
                if (subId.equals(id)) {
                    System.out.println(generador.toString());
                }
            }
        }
    }

    public void busquedaGeneradorDeMillasEstado(Estado e) {
        GeneradorDeMillas generador;
        Iterator it = generadores.iterator();
        while (it.hasNext()) {
            generador = (GeneradorDeMillas) it.next();
            if (generador.getEstado() == e) {
                System.out.println(generador.toString());
            }
        }
    }

    public void busquedaViajesCanjeablesNombre(String nombre) {
        int lenght = nombre.length();
        String nombreGenerador, subNombre;
        GeneradorDeMillas generador;
        Iterator it = viajesCanjeables.iterator();
        while (it.hasNext()) {
            generador = (GeneradorDeMillas) it.next();
            nombreGenerador = (generador.getNombre()).toUpperCase();
            if (nombreGenerador.length() >= lenght) {
                subNombre = nombreGenerador.substring(0, lenght);
                if (subNombre.equals(nombre.toUpperCase())) {
                    System.out.println(generador.toString());
                }
            }
        }
    }

    public void busquedaViajesCanjeablesId(String id) {
        int lenght = id.length();
        String idGenerador, subId;
        GeneradorDeMillas generador;
        Iterator it = viajesCanjeables.iterator();
        while (it.hasNext()) {
            generador = (GeneradorDeMillas) it.next();
            idGenerador = String.valueOf(generador.getId());
            if (idGenerador.length() >= lenght) {
                subId = idGenerador.substring(0, lenght);
                if (subId.equals(id)) {
                    System.out.println(generador.toString());
                }
            }
        }
    }

    public void busquedaViajesCanjeablesEstado(Estado e) {
        GeneradorDeMillas generador;
        Iterator it = viajesCanjeables.iterator();
        while (it.hasNext()) {
            generador = (GeneradorDeMillas) it.next();
            if (generador.getEstado() == e) {
                System.out.println(generador.toString());
            }
        }
    }

    public void busquedaViajesCanjeablesOrigen(String origen) {
        String o;
        Viaje viaje;
        Iterator it = viajesCanjeables.iterator();
        while (it.hasNext()) {
            viaje = (Viaje) it.next();
            o = viaje.getNombreOrigen();
            if (o.equals(origen)) {
                System.out.println(viaje.toString());
            }
        }
    }

    public void busquedaViajesCanjeablesDestino(String destino) {
        String d;
        Viaje viaje;
        Iterator it = viajesCanjeables.iterator();
        while (it.hasNext()) {
            viaje = (Viaje) it.next();
            d = viaje.getNombreDestino();
            if (d.equals(destino)) {
                System.out.println(viaje.toString());
            }
        }
    }

    public void busquedaTablaDestinoOrigen(String origen) {
        tablaDeDestinos.busquedaTablaDestinoOrigen(origen);
    }

    public void busquedaTablaDestinoDestino(String destino) {
        tablaDeDestinos.busquedaTablaDestinoDestino(destino);
    }

    public void cargaDatosDeTablaDestino() throws FileNotFoundException {
        try (BufferedReader in = new BufferedReader(new FileReader("DatosTablaDestino.txt"))) {
            String s, origen, destino;
            String[] s3;
            long millasCanje, millasObtenidas;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                origen = s3[0];
                destino = s3[1];
                millasCanje = Long.parseLong(s3[2]);
                millasObtenidas = Integer.parseInt(s3[3]);
                agregaFilaDestinos(origen, destino, millasCanje, millasObtenidas);
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Tabla De Destino.");
        }
    }

    public void cargaDatosViajeros() {
        try (BufferedReader in = new BufferedReader(new FileReader("DatosViajeros.txt"))) {
            String s, nombre;
            String[] s3;
            long dni, millasAcumuladas;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                nombre = s3[0];
                dni = Long.parseLong(s3[1]);
                millasAcumuladas = Long.parseLong(s3[2]);
                agregaViajero(nombre, dni, millasAcumuladas);
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Viajeros.");
        }
    }

    public void cargaGeneradorConsumo() {
        try (BufferedReader in = new BufferedReader(new FileReader("DatosGeneradorConsumo.txt"))) {
            String s, nombre, id, descripcion, estado;
            String[] s3;
            Estado e;
            float factorMillas;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                id = s3[0];
                nombre = s3[1];
                descripcion = s3[2];
                estado = s3[3];
                factorMillas = Float.parseFloat(s3[4]);
                switch (estado) {
                    case "disponible":
                        e = Estado.DISPONIBLE;
                        break;
                    case "suspendido":
                        e = Estado.SUSPENDIDO;
                        break;
                    case "caduco":
                        e = Estado.CADUCO;
                        break;
                    default:
                        e = Estado.DISPONIBLE;
                        break;
                }
                agregaGeneradorConsumo(id, nombre, descripcion, factorMillas, e);
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Generador Consumo.");
        }
    }

    public void cargaGeneradorCombustible() {
        try (BufferedReader in = new BufferedReader(new FileReader("DatosGeneradorCombustible.txt"))) {
            String s, nombre, id, descripcion, estado;
            String[] s3;
            Estado e;
            float factorMillas;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                id = s3[0];
                nombre = s3[1];
                descripcion = s3[2];
                estado = s3[3];
                factorMillas = Float.parseFloat(s3[4]);
                switch (estado) {
                    case "disponible":
                        e = Estado.DISPONIBLE;
                        break;
                    case "suspendido":
                        e = Estado.SUSPENDIDO;
                        break;
                    case "caduco":
                        e = Estado.CADUCO;
                        break;
                    default:
                        e = Estado.DISPONIBLE;
                        break;
                }
                agregaGeneradorCombustible(id, nombre, descripcion, factorMillas, e);
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Generador Combustible.");
        }
    }

    public void cargaGeneradorHotel() {
        try (BufferedReader in = new BufferedReader(new FileReader("DatosGeneradorHotel.txt"))) {
            String s, nombre, id, descripcion, estado, categoria, Lugar;
            String[] s3;
            Estado e;
            float factorMillas, precioPorNoche;
            int cantNoches;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                id = s3[0];
                nombre = s3[1];
                descripcion = s3[2];
                estado = s3[3];
                factorMillas = Float.parseFloat(s3[4]);
                categoria = s3[5];
                precioPorNoche = Float.parseFloat(s3[6]);
                cantNoches = Integer.parseInt(s3[7]);
                Lugar = s3[8];
                switch (estado) {
                    case "disponible":
                        e = Estado.DISPONIBLE;
                        break;
                    case "suspendido":
                        e = Estado.SUSPENDIDO;
                        break;
                    case "caduco":
                        e = Estado.CADUCO;
                        break;
                    default:
                        e = Estado.DISPONIBLE;
                        break;
                }
                agregaGeneradorHotel(Lugar, categoria, precioPorNoche, cantNoches, id, nombre, descripcion, factorMillas, e);
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Generador Hotel.");
        }
    }

    public void cargaViajes() {
        try (BufferedReader in = new BufferedReader(new FileReader("DatosViajes.txt"))) {
            String s, nombre, id, descripcion, estado, origen, destino, tipo, nombreAd;
            int adicionales;
            String[] s3;
            Estado e;
            long millas;
            float factorMillas;
            Primera primera;
            Business business;
            Turista turista;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                id = s3[0];
                nombre = s3[1];
                descripcion = s3[2];
                estado = s3[3];
                factorMillas = Float.parseFloat(s3[4]);
                origen = s3[5];
                destino = s3[6];
                tipo = s3[7];
                if (verificaLugar(origen) && verificaLugar(destino)) {
                    switch (estado) {
                        case "disponible":
                            e = Estado.DISPONIBLE;
                            break;
                        case "suspendido":
                            e = Estado.SUSPENDIDO;
                            break;
                        case "caduco":
                            e = Estado.CADUCO;
                            break;
                        default:
                            e = Estado.DISPONIBLE;
                            break;
                    }
                    switch (tipo) {
                        case "Primera":
                            primera = new Primera(id, nombre, descripcion, factorMillas, new Lugar(origen), new Lugar(destino), e);
                            //adicionales
                            adicionales = Integer.parseInt(s3[8]);
                            for (int i = 9; i < (adicionales * 2 + 9); i++) {
                                nombreAd = s3[i];
                                millas = Long.parseLong(s3[++i]);
                                primera.agregaAdicional(nombreAd, millas);
                            }
                            agregaGeneradorPrimera(primera);
                            break;
                        case "Business":
                            business = new Business(id, nombre, descripcion, factorMillas, new Lugar(origen), new Lugar(destino), e);
                            //adicionales
                            adicionales = Integer.parseInt(s3[8]);
                            for (int i = 9; i < (adicionales * 2 + 9); i++) {
                                nombreAd = s3[i];
                                millas = Long.parseLong(s3[++i]);
                                business.agregaAdicional(nombreAd, millas);
                            }
                            agregaGeneradorBusiness(business);
                            break;
                        case "Turista":
                            turista = new Turista(id, nombre, descripcion, factorMillas, new Lugar(origen), new Lugar(destino), e);
                            agregaGeneradorTurista(turista);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Viajes.");
        }
    }

    public void cargaViajesCanjeables() {
        try (BufferedReader in = new BufferedReader(new FileReader("DatosViajesCanjeables.txt"))) {
            String s, nombre, id, descripcion, estado, origen, destino, tipo, nombreAd;
            int adicionales;
            String[] s3;
            Estado e;
            long millas;
            float factorMillas;
            Primera primera;
            Business business;
            Turista turista;
            while ((s = in.readLine()) != null) {
                s3 = s.split(" ");
                id = s3[0];
                nombre = s3[1];
                descripcion = s3[2];
                estado = s3[3];
                factorMillas = Float.parseFloat(s3[4]);
                origen = s3[5];
                destino = s3[6];
                tipo = s3[7];
                if (verificaLugar(origen) && verificaLugar(destino)) {
                    switch (estado) {
                        case "disponible":
                            e = Estado.DISPONIBLE;
                            break;
                        case "suspendido":
                            e = Estado.SUSPENDIDO;
                            break;
                        case "caduco":
                            e = Estado.CADUCO;
                            break;
                        default:
                            e = Estado.DISPONIBLE;
                            break;
                    }
                    switch (tipo) {
                        case "Primera":
                            primera = new Primera(id, nombre, descripcion, factorMillas, new Lugar(origen), new Lugar(destino), e);
                            //adicionales
                            adicionales = Integer.parseInt(s3[8]);
                            for (int i = 9; i < (adicionales * 2 + 9); i++) {
                                nombreAd = s3[i];
                                millas = Long.parseLong(s3[++i]);
                                primera.agregaAdicional(nombreAd, millas);
                            }
                            agregaViajeCanjeablePrimera(primera);
                            break;
                        case "Business":
                            business = new Business(id, nombre, descripcion, factorMillas, new Lugar(origen), new Lugar(destino), e);
                            //adicionales
                            adicionales = Integer.parseInt(s3[8]);
                            for (int i = 9; i < (adicionales * 2 + 9); i++) {
                                nombreAd = s3[i];
                                millas = Long.parseLong(s3[++i]);
                                business.agregaAdicional(nombreAd, millas);
                            }
                            agregaViajeCanjeableBusiness(business);
                            break;
                        case "Turista":
                            turista = new Turista(id, nombre, descripcion, factorMillas, new Lugar(origen), new Lugar(destino), e);
                            agregaViajeCanjeableTurista(turista);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al cargar el archivo de Viajes Canjeables.");
        }
    }
}
