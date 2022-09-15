/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import SistemaCanjeMillas.Business;
import SistemaCanjeMillas.FilaDeDestinos;
import SistemaCanjeMillas.GeneradorDeMillas;
import SistemaCanjeMillas.GeneradorDeMillas.Estado;
import SistemaCanjeMillas.Lugar;
import SistemaCanjeMillas.Primera;
import Validaciones.Validaciones;
import SistemaCanjeMillas.SistemaCanjeMillas;
import SistemaCanjeMillas.Turista;
import SistemaCanjeMillas.Viaje;
import SistemaCanjeMillas.Viajero;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Cliente
 */
public class Menu {

    private SistemaCanjeMillas s;

    public Menu(SistemaCanjeMillas s) {
        this.s = s;
    }

    public void iniciar() {
        System.out.println("..........................M E N U   A E R O L I N E A...............................");
        System.out.println("        1)  ADMINISTRAR VIAJEROS(ABM)");
        System.out.println("        2)  ADMINISTRAR GENERADORES DE MILLAS(ABM)");
        System.out.println("        3)  ADMINISTRAR VIAJES CANJEABLES(ABM)");
        System.out.println("        4)  ADMINISTRAR TABLA DE DESTINOS(ABM)");
        System.out.println("        5)  CARGAR ARCHIVO PRODUCTOS.TXT");
        System.out.println("        6)  CARGAR ARCHIVO LUGARES.TXT");
        System.out.println("        7)  REGISTRAR MILLAS A PARTIR DEL ARCHIVO REGISTRO.XML");
        System.out.println("        8)  CANJEAR PRODUCTOS");
        System.out.println("        9)  CANJEAR VIAJES");
        System.out.println("        10)  REPORTES");
        System.out.println("        11)  CARGA DATOS");
        System.out.println("                                                        0)  SALIR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();
        int opcion = Validaciones.entero(op);
        switch (opcion) {
            case 0:
                break;
            case 1:
                administraViajeros();
                break;
            case 2:
                administraGeneradoresDeMillas();
                break;
            case 3:
                administraViajesCanjeables();
                break;
            case 4:
                administraTablaDeDestinos();
                break;
            case 5:
                cargaArchivoProductos();
                break;
            case 6:
                cargaArchivoLugares();
                break;
            case 7:
                registrarMillas();
                break;
            case 8:
                canjearProductos();
                break;
            case 9:
                canjearViajes();
                break;
            case 10:
                reportes();
                break;
            case 11:
                cargaArchivoDatos();
                break;
            default:
                System.out.println("Esa opción no existe, intente nuevamente.");
                iniciar();
                break;
        }
    }

    private void administraViajeros() {
        System.out.println("..........................ADMINISTRA    VIAJEROS...............................");
        System.out.println("        1)  AGREGAR VIAJERO(ALTA)");
        System.out.println("        2)  ELIMINAR VIAJERO(BAJA)");
        System.out.println("        3)  MODIFICAR VIAJERO");
        System.out.println("        4)  MOSTRAR TODOS LOS VIAJEROS");
        System.out.println("        5)  BUSQUEDA");
        System.out.println("        6)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();
        int opcion = Validaciones.entero(op);
        switch (opcion) {
            case 1:
                agregaViajero();
                break;
            case 2:
                eliminaViajero();
                break;
            case 3:
                modificarViajero();
                break;
            case 4:
                mostrarViajeros();
                break;
            case 5:
                buscarViajeros();
                break;
            case 6:
                iniciar();
                break;
            default:
                System.out.println("Esa opción no existe, intente nuevamente.");
                administraViajeros();
                break;
        }

    }

    private void agregaViajero() {
        Scanner sc = new Scanner(System.in);
        String nombre, dni, millasAcumuladas;
        System.out.println("Ingrese nombre");
        nombre = sc.nextLine();
        if (Validaciones.sonEspacios(nombre) || !Validaciones.sonCaracteresCorrectos(nombre)) {
            System.out.println("No puede existir una persona con ese nombre ");
        } else {
            System.out.println("Ingrese DNI");
            dni = sc.nextLine();
            long Dni = Validaciones.longg(dni);
            if (Validaciones.esMayorACero(Dni)) {
                System.out.println("Ingrese millas acumuladas");
                millasAcumuladas = sc.nextLine();
                long Millas = Validaciones.longg(millasAcumuladas);
                if (Validaciones.esMayorIgualACero(Millas)) {
                    s.agregaViajero(nombre, Dni, Millas);
                } else {
                    System.out.println("Las millas deben ser mayores o iguales a 0");
                }
            } else {
                System.out.println("Dni debe ser mayor a 0");
            }
        }
        administraViajeros();
    }

    private void leeViaje() {
        Scanner sc = new Scanner(System.in);
        String tipo, id, nombre, descripcion, factor, origen, destino, estado, ad, cantAd, nombreAdicional;
        int adicional, cantAdicional;
        Estado e;
        float factorDeMillas;
        System.out.println("Ingrese categoria GENERADOR VIAJE: PRIMERA-BUSINESS-TURISTA");
        tipo = sc.nextLine();
        switch (tipo) {
            case "PRIMERA":
                sc = new Scanner(System.in);
                System.out.println("Ingrese ID");
                id = sc.nextLine();
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                if (Validaciones.sonEspacios(nombre) || !Validaciones.sonCaracteresCorrectos(nombre)) {
                    System.out.println("No puede existir un generador con ese nombre ");
                } else {
                    System.out.println("Ingrese descripcion");
                    descripcion = sc.nextLine();
                    System.out.println("Ingrese factor de millas");
                    factor = sc.nextLine();
                    factorDeMillas = Validaciones.real(factor);
                    if (Validaciones.esMayorACero(factorDeMillas)) {
                        System.out.println("Ingrese origen");
                        origen = sc.nextLine();
                        if (s.verificaLugar(origen)) {
                            Lugar o = new Lugar(origen);
                            System.out.println("Ingrese destino: ");
                            destino = sc.nextLine();
                            if (s.verificaLugar(destino)) {
                                Lugar d = new Lugar(destino);
                                System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
                                estado = sc.nextLine();

                                switch (estado) {
                                    case "D":
                                        e = Estado.DISPONIBLE;
                                        break;
                                    case "S":
                                        e = Estado.SUSPENDIDO;
                                        break;
                                    case "C":
                                        e = Estado.CADUCO;
                                        break;
                                    default:
                                        System.out.println("Estado incorrecto, por defecto queda DISPONIBLE.");
                                        e = Estado.DISPONIBLE;
                                        break;
                                }
                                Primera primera = new Primera(id, nombre, descripcion, factorDeMillas, o, d, e);
                                System.out.println("Desea agrega adicionales, 1.Si");
                                ad = sc.nextLine();
                                adicional = Validaciones.entero(ad);
                                if (adicional != 1) {
                                    System.out.println("Se agrego el generador sin adicionales");
                                } else {
                                    while (adicional == 1 && !primera.verificaCantAdicionales()) {
                                        System.out.println("Ingrese nombre del adicional");
                                        nombreAdicional = sc.nextLine();
                                        System.out.println("Ingrese valor de millas");
                                        cantAd = sc.nextLine();
                                        cantAdicional = Validaciones.entero(cantAd);
                                        primera.agregaAdicional(nombreAdicional, cantAdicional);
                                        System.out.println("Desea agregar otro adicional? 1.Si");
                                        ad = sc.nextLine();
                                        adicional = Validaciones.entero(ad);
                                    }
                                }
                                s.agregaGeneradorPrimera(primera);
                            } else {
                                System.out.println("El destino no es valido");
                            }

                        } else {
                            System.out.println("El origen no es valido.");
                        }
                    } else {
                        System.out.println("El factor de millas debe ser mayor a 0");
                    }
                }
                break;
            case "BUSINESS":
                sc = new Scanner(System.in);
                System.out.println("Ingrese ID");
                id = sc.nextLine();
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                if (Validaciones.sonEspacios(nombre) || !Validaciones.sonCaracteresCorrectos(nombre)) {
                    System.out.println("No puede existir un generador con ese nombre ");
                } else {
                    System.out.println("Ingrese descripcion");
                    descripcion = sc.nextLine();
                    System.out.println("Ingrese factor de millas");
                    factor = sc.nextLine();
                    factorDeMillas = Validaciones.real(factor);
                    if (Validaciones.esMayorACero(factorDeMillas)) {
                        System.out.println("Ingrese origen");
                        origen = sc.nextLine();
                        if (s.verificaLugar(origen)) {
                            Lugar o = new Lugar(origen);
                            System.out.println("Ingrese destino: ");
                            destino = sc.nextLine();
                            if (s.verificaLugar(destino)) {
                                Lugar d = new Lugar(destino);
                                System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
                                estado = sc.nextLine();

                                switch (estado) {
                                    case "D":
                                        e = Estado.DISPONIBLE;
                                        break;
                                    case "S":
                                        e = Estado.SUSPENDIDO;
                                        break;
                                    case "C":
                                        e = Estado.CADUCO;
                                        break;
                                    default:
                                        System.out.println("Estado incorrecto, por defecto queda DISPONIBLE.");
                                        e = Estado.DISPONIBLE;
                                        break;
                                }
                                Business business = new Business(id, nombre, descripcion, factorDeMillas, o, d, e);
                                System.out.println("Desea agrega adicionales, 1. Si");
                                ad = sc.nextLine();
                                adicional = Validaciones.entero(ad);
                                if (adicional != 1) 
                                {
                                    System.out.println("Opcion invalida, se agrego el generador sin adicionales");
                                } else {
                                    while (adicional == 1 && !business.verificaCantAdicionales()) {
                                        System.out.println("Ingrese nombre del adicional");
                                        nombreAdicional = sc.nextLine();
                                        System.out.println("Ingrese valor de millas");
                                        cantAd = sc.nextLine();
                                        cantAdicional = Validaciones.entero(cantAd);
                                        business.agregaAdicional(nombreAdicional, cantAdicional);
                                        System.out.println("Desea agregar otro adicional? 1.Si");
                                        ad = sc.nextLine();
                                        adicional = Validaciones.entero(ad);
                                    }
                                }
                                s.agregaGeneradorBusiness(business);
                            } else {
                                System.out.println("El destino no es valido");
                            }

                        } else {
                            System.out.println("El origen no es valido.");
                        }
                    } else {
                        System.out.println("El factor de millas debe ser mayor a 0");
                    }
                }
                break;
            case "TURISTA":
                sc = new Scanner(System.in);
                System.out.println("Ingrese ID");
                id = sc.nextLine();
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                if (Validaciones.sonEspacios(nombre) || !Validaciones.sonCaracteresCorrectos(nombre)) {
                    System.out.println("No puede existir un generador con ese nombre ");
                } else {
                    System.out.println("Ingrese descripcion");
                    descripcion = sc.nextLine();
                    System.out.println("Ingrese factor de millas");
                    factor = sc.nextLine();
                    factorDeMillas = Validaciones.real(factor);
                    if (Validaciones.esMayorACero(factorDeMillas)) {
                        System.out.println("Ingrese origen");
                        origen = sc.nextLine();
                        if (s.verificaLugar(origen)) {
                            Lugar o = new Lugar(origen);
                            System.out.println("Ingrese destino: ");
                            destino = sc.nextLine();
                            if (s.verificaLugar(destino)) {
                                Lugar d = new Lugar(destino);
                                System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
                                estado = sc.nextLine();

                                switch (estado) {
                                    case "D":
                                        e = Estado.DISPONIBLE;
                                        break;
                                    case "S":
                                        e = Estado.SUSPENDIDO;
                                        break;
                                    case "C":
                                        e = Estado.CADUCO;
                                        break;
                                    default:
                                        System.out.println("Estado incorrecto, por defecto queda DISPONIBLE.");
                                        e = Estado.DISPONIBLE;
                                        break;
                                }
                                Turista turista = new Turista(id, nombre, descripcion, factorDeMillas, o, d, e);
                                s.agregaGeneradorTurista(turista);
                            } else {
                                System.out.println("El destino no es valido");
                            }

                        } else {
                            System.out.println("El origen no es valido.");
                        }
                    } else {
                        System.out.println("El factor de millas debe ser Mayor a 0");
                    }
                }
                break;
            default:
                System.out.println("El tipo no es correcto.");
                break;
        }
    }

    private void leeHotel() {
        Scanner sc = new Scanner(System.in);
        Estado e;
        String nombreLugar, categoria, precioNoche, cantNoches, id, descripcion, factorMillas, nombreGenerador, estado;
        System.out.println("Ingrese nombre del lugar");
        nombreLugar = sc.nextLine();
        System.out.println("Ingrese categoria");
        categoria = sc.nextLine();
        System.out.println("Ingrese precio por noche");
        precioNoche = sc.nextLine();
        float precio = Validaciones.real(precioNoche);
        System.out.println("Ingrese cantidad de noches");
        cantNoches = sc.nextLine();
        int noches = Validaciones.entero(cantNoches);
        System.out.println("Ingrese ID");
        id = sc.nextLine();
        System.out.println("Ingrese Nombre del generador");
        nombreGenerador = sc.nextLine();
        System.out.println("Ingrese descripcion");
        descripcion = sc.nextLine();
        System.out.println("Ingrese factor de millas");
        factorMillas = sc.nextLine();
        float factor = Validaciones.real(factorMillas);
        System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
        estado = sc.nextLine();

        switch (estado) {
            case "D":
                e = Estado.DISPONIBLE;
                break;
            case "S":
                e = Estado.SUSPENDIDO;
                break;
            case "C":
                e = Estado.CADUCO;
                break;
            default:
                System.out.println("Estado incorrecto, por defecto queda DISPONIBLE.");
                e = Estado.DISPONIBLE;
                break;
        }
        s.agregaGeneradorHotel(nombreLugar, categoria, precio, noches, id, nombreGenerador, descripcion, factor, e);
    }

    private void leeCombustible() {
        Scanner sc = new Scanner(System.in);
        Estado e;
        String nombre, id, descripcion, factorMillas, estado;
        System.out.println("Ingrese ID");
        id = sc.nextLine();
        System.out.println("Ingrese nombre");
        nombre = sc.nextLine();
        System.out.println("Ingrese descripcion");
        descripcion = sc.nextLine();
        System.out.println("Ingrese factor de millas");
        factorMillas = sc.nextLine();
        float factor = Validaciones.real(factorMillas);
        System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
        estado = sc.nextLine();

        switch (estado) {
            case "D":
                e = Estado.DISPONIBLE;
                break;
            case "S":
                e = Estado.SUSPENDIDO;
                break;
            case "C":
                e = Estado.CADUCO;
                break;
            default:
                System.out.println("Estado incorrecto, por defecto queda DISPONIBLE.");
                e = Estado.DISPONIBLE;
                break;
        }
        s.agregaGeneradorCombustible(id, nombre, descripcion, factor, e);

    }

    private void leeConsumo() {
        Scanner sc = new Scanner(System.in);
        Estado e;
        String nombre, id, descripcion, factorMillas, estado;
        System.out.println("Ingrese ID");
        id = sc.nextLine();
        System.out.println("Ingrese nombre");
        nombre = sc.nextLine();
        System.out.println("Ingrese descripcion");
        descripcion = sc.nextLine();
        System.out.println("Ingrese factor de millas");
        factorMillas = sc.nextLine();
        float factor = Validaciones.real(factorMillas);
        System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
        estado = sc.nextLine();

        switch (estado) {
            case "D":
                e = Estado.DISPONIBLE;
                break;
            case "S":
                e = Estado.SUSPENDIDO;
                break;
            case "C":
                e = Estado.CADUCO;
                break;
            default:
                System.out.println("Estado incorrecto, por defecto queda DISPONIBLE.");
                e = Estado.DISPONIBLE;
                break;
        }
        s.agregaGeneradorConsumo(id, nombre, descripcion, factor, e);
    }

    private void eliminaViajero() {
        Scanner sc = new Scanner(System.in);
        String dni;

        System.out.println("Ingrese DNI del viajero eliminar");
        dni = sc.nextLine();
        long Dni = Validaciones.longg(dni);
        if (s.eliminaViajero(Dni)) {
            System.out.println("Viajero eliminado.");
        } else {
            System.out.println("El DNI no se ha encontrado.");
        }
        administraViajeros();
    }

    private void modificarViajero() { // No valida si son negativas las Millas Acumuladas
        Scanner sc = new Scanner(System.in);
        String dni, millas;
        s.muestraViajeros();
        System.out.println("Ingrese DNI del viajero a modificar");
        dni = sc.nextLine();
        long dniMod = Validaciones.longg(dni);
        Viajero v = s.buscaViajero(dniMod);
        if (v != null) {
            System.out.println("Ingrese nuevas millas acumulables");
            millas = sc.nextLine();
            long millasMod = Validaciones.longg(millas);
            if (Validaciones.esMayorIgualACero(millasMod)) {
                s.modificaMillasAcumuladas(v, millasMod);
            } else {
                System.out.println("Las millas deben ser mayores o iguales a 0");
            }
        } else {
            System.out.println("El viajero no se encontro / no existe");
        }
        administraViajeros();
    }

    private void mostrarViajeros() {
        Scanner sc = new Scanner(System.in);
        System.out.println("VIAJEROS: ");
        s.muestraViajeros();
        System.out.println("Presione para continuar...");
        sc.nextLine();
        administraViajeros();
    }

    private void administraGeneradoresDeMillas() {
        System.out.println("..........................ADMINISTRA GENERADORES DE MILLAS............................");
        System.out.println("        1)  AGREGAR GENERADORES DE MILLAS(ALTA)");
        System.out.println("        2)  ELIMINAR GENERADORES DE MILLAS(BAJA)");
        System.out.println("        3)  MODIFICAR GENERADORES DE MILLAS");
        System.out.println("        4)  MOSTRAR GENERADORES DE MILLAS");
        System.out.println("        5)  BUSQUEDA");
        System.out.println("        6)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                agregaGeneradorDeMillas();
                break;
            case 2:
                eliminaGeneradorDeMillas();
                break;
            case 3:
                modificaGeneradorDeMillas();
                break;
            case 4:
                mostrarGeneradoresDeMillas();
                break;
            case 5:
                buscarGeneradorDeMillas();
                break;
            case 6:
                iniciar();
                break;
            default:
                iniciar();
                break;
        }
    }

    private void agregaGeneradorDeMillas() {
        Scanner sc = new Scanner(System.in);
        String tipo;
        System.out.println("Ingrese tipo de GENERADOR: VIAJE-HOTEL-COMBUSTIBLE-CONSUMO");
        tipo = sc.nextLine();
        switch (tipo) {
            case "VIAJE":
                leeViaje();
                break;
            case "HOTEL":
                leeHotel();
                break;
            case "COMBUSTIBLE":
                leeCombustible();
                break;
            case "CONSUMO":
                leeConsumo();
                break;
            default:
                System.out.println("El tipo no es correcto.");
                break;
        }
        administraGeneradoresDeMillas();
    }

    private void eliminaGeneradorDeMillas() {
        Scanner sc = new Scanner(System.in);
        String id;
        System.out.println("Ingrese ID del generador de millas a eliminar");
        id = sc.nextLine();
        if (s.eliminaGeneradorDeMillas(id)) {
            System.out.println("Generador de millas eliminado.");
        } else {
            System.out.println("El ID no se ha encontrado.");
        }
        administraGeneradoresDeMillas();
    }

    private void modificaGeneradorDeMillas() {
        Scanner sc = new Scanner(System.in);
        String id, mod, nombre, descripcion, factordemillas, estado;
        Estado e;
        s.muestraGeneradores();
        System.out.println("Ingrese ID del generador de millas a modificar");
        id = sc.nextLine();
        GeneradorDeMillas g = s.buscaGenerador(id);
        if (g != null) {
            System.out.println("Ingrese lo que desea moddificar : NOMBRE - DESCRIPCION - FACTORDEMILLAS - ESTADO");
            mod = sc.nextLine();
            switch (mod) {
                case "NOMBRE":
                    System.out.println("Escriba nuevo nombre");
                    nombre = sc.nextLine();
                    if (Validaciones.sonEspacios(nombre) || !Validaciones.sonCaracteresCorrectos(nombre)) {
                        System.out.println("No puede existir una persona con ese nombre ");
                    } else {
                        s.modificaNombreGenerador(g, nombre);
                        System.out.println("Nombre Modificado");
                    }
                    break;
                case "DESCRIPCION":
                    System.out.println("Escriba nueva descripcion");
                    descripcion = sc.nextLine();
                    s.modificaDescripcionGenerador(g, descripcion);
                    System.out.println("Descripcion Modificada");
                    break;
                case "FACTORDEMILLAS":
                    System.out.println("Escriba nuevo factor de millas");
                    factordemillas = sc.nextLine();
                    long modfactor = Validaciones.longg(factordemillas);
                    if (Validaciones.esMayorACero(modfactor)) {
                        s.modificaFactorDeMillasGenerador(g, modfactor);
                        System.out.println("Factor de Millas Modificado");
                    } else {
                        System.out.println("El factor de millas debe ser mayor a 0");
                    }
                    break;
                case "ESTADO":
                    System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
                    estado = sc.nextLine();
                    switch (estado) {
                        case "D":
                            e = Estado.DISPONIBLE;
                            s.modificaEstadoGenerador(g, e);
                            break;
                        case "S":
                            e = Estado.SUSPENDIDO;
                            s.modificaEstadoGenerador(g, e);
                            break;
                        case "C":
                            e = Estado.CADUCO;
                            s.modificaEstadoGenerador(g, e);
                            break;
                        default:
                            System.out.println("Estado incorrecto");
                            break;
                    }
                    System.out.println("Estado Modificado");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } else {
            System.out.println("No se encontro / no existe Generador");
        }

        administraGeneradoresDeMillas();
    }

    private void mostrarGeneradoresDeMillas() {
        Scanner sc = new Scanner(System.in);
        System.out.println("GENERADORES DE MILLAS:");
        s.muestraGeneradores();
        System.out.println("Presione para continuar...");
        sc.nextLine();
        administraGeneradoresDeMillas();
    }

    private void cargaArchivoProductos() {
        Scanner sc = new Scanner(System.in);
        try {
            s.cargaProductos();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Archivo Productos.txt cargado.");
        iniciar();
    }

    private void reportes() {
        System.out.println("..........................GENERA    REPORTES...............................");
        System.out.println("        1)  RANKING DE VIAJEROS");
        System.out.println("        2)  LISTADO DE DESTINOS CONSUMIDOS AL MENOS 5 VECES");
        System.out.println("        3)  ESTADISTICA DE GENERADORES DE MILLAS");
        System.out.println("        4)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                rankingViajeros();
                break;
            case 2:
                listadoLugares();
                break;
            case 3:
                estadisticaGen();
                break;
            case 4:
                iniciar();
                break;
        }
    }

    private void rankingViajeros() {
        Scanner sc = new Scanner(System.in);
        s.rankingViajerosPorMillasTotales();
        sc.nextLine();
        reportes();
    }

    private void listadoLugares() {
        Scanner sc = new Scanner(System.in);
        s.listadoDeDestinos();
        sc.nextLine();
        reportes();
    }

    private void estadisticaGen() {
        Scanner sc = new Scanner(System.in);
        s.graficoGeneradores();
        sc.nextLine();
        reportes();
    }

    private void administraViajesCanjeables() {
        System.out.println("..........................ADMINISTRA VIAJES CANJEABLES............................");
        System.out.println("        1)  AGREGAR VIAJES CANJEABLES(ALTA)");
        System.out.println("        2)  ELIMINAR VIAJES CANJEABLES(BAJA)");
        System.out.println("        3)  MODIFICAR VIAJES CANJEABLES");
        System.out.println("        4)  MOSTRAR VIAJES CANJEABLES");
        System.out.println("        5)  BUSQUEDA");
        System.out.println("        6)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                agregaViajeCanjeable();
                break;
            case 2:
                eliminaViajeCanjeable();
                break;
            case 3:
                modificaViajesCanjeables();
                break;
            case 4:
                mostrarViajesCanjeables();
                break;
            case 5:
                buscarViajesCanjeables();
                break;
            case 6:
                iniciar();
                break;
        }
    }

    private void agregaViajeCanjeable() {
        Scanner sc = new Scanner(System.in);
        String tipo, id, nombre, descripcion, factor, origen, destino, estado, ad, cantAd, nombreAdicional;
        int adicional, cantAdicional;
        Estado e = Estado.DISPONIBLE;
        float factorDeMillas;
        System.out.println("Ingrese categoria tipo de VIAJE CANJEABLE: PRIMERA-BUSINESS-TURISTA");
        tipo = sc.nextLine();
        switch (tipo) {
            case "PRIMERA":
                sc = new Scanner(System.in);
                System.out.println("Ingrese ID");
                id = sc.nextLine();
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                System.out.println("Ingrese descripcion");
                descripcion = sc.nextLine();
                System.out.println("Ingrese factor de millas");
                factor = sc.nextLine();
                factorDeMillas = Validaciones.real(factor);
                System.out.println("Ingrese origen");
                origen = sc.nextLine();
                if (s.verificaLugar(origen)) {
                    Lugar o = new Lugar(origen);
                    System.out.println("Ingrese destino: ");
                    destino = sc.nextLine();
                    if (s.verificaLugar(destino)) {
                        Lugar d = new Lugar(destino);
                        Primera primera = new Primera(id, nombre, descripcion, factorDeMillas, o, d, e);
                        System.out.println("Desea agrega adicionales, 1.Si");
                        ad = sc.nextLine();
                        adicional = Validaciones.entero(ad);
                        if (adicional != 1) 
                        {
                            System.out.println("Se agrego el viaje canjeable sin adicionales");
                        } else {
                            while (adicional == 1 && !primera.verificaCantAdicionales()) {
                                System.out.println("Ingrese nombre del adicional");
                                nombreAdicional = sc.nextLine();
                                System.out.println("Ingrese valor de millas");
                                cantAd = sc.nextLine();
                                cantAdicional = Validaciones.entero(cantAd);
                                primera.agregaAdicional(nombreAdicional, cantAdicional);
                                System.out.println("Desea agregar otro adicional? 1.Si");
                                ad = sc.nextLine();
                                adicional = Validaciones.entero(ad);
                            }
                        }
                        if (primera.verificaCantAdicionales()) {
                            System.out.println("No se pueden agregar mas adicionales");
                        }
                        s.agregaViajeCanjeablePrimera(primera);
                        System.out.println("Viaje canjeable agregado.");
                    } else {
                        System.out.println("El destino no es valido");
                    }

                } else {
                    System.out.println("El origen no es valido.");
                }
                break;
            case "BUSINESS":
                sc = new Scanner(System.in);
                System.out.println("Ingrese ID");
                id = sc.nextLine();
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                System.out.println("Ingrese descripcion");
                descripcion = sc.nextLine();
                System.out.println("Ingrese factor de millas");
                factor = sc.nextLine();
                factorDeMillas = Validaciones.real(factor);
                System.out.println("Ingrese origen");
                origen = sc.nextLine();
                if (s.verificaLugar(origen)) {
                    Lugar o = new Lugar(origen);
                    System.out.println("Ingrese destino: ");
                    destino = sc.nextLine();
                    if (s.verificaLugar(destino)) {
                        Lugar d = new Lugar(destino);
                        Business business = new Business(id, nombre, descripcion, factorDeMillas, o, d, e);
                        System.out.println("Desea agrega adicionales, 1. Si");
                        ad = sc.nextLine();
                        adicional = Validaciones.entero(ad);
                        if (adicional != 1) 
                        {
                            System.out.println("Opcion invalida, se agrego el generador sin adicionales");
                        } else {
                            while (adicional == 1 && !business.verificaCantAdicionales()) {
                                System.out.println("Ingrese nombre del adicional");
                                nombreAdicional = sc.nextLine();
                                System.out.println("Ingrese valor de millas");
                                cantAd = sc.nextLine();
                                cantAdicional = Validaciones.entero(cantAd);
                                business.agregaAdicional(nombreAdicional, cantAdicional);
                                System.out.println("Desea agregar otro adicional? 1.Si");
                                ad = sc.nextLine();
                                adicional = Validaciones.entero(ad);
                            }
                        }
                        if (business.verificaCantAdicionales()) {
                            System.out.println("No se pueden agregar mas adicionales");
                        }
                        s.agregaViajeCanjeableBusiness(business);
                        System.out.println("Viaje canjeable agregado.");
                    } else {
                        System.out.println("El destino no es valido");
                    }

                } else {
                    System.out.println("El origen no es valido.");
                }
                break;
            case "TURISTA":
                sc = new Scanner(System.in);
                System.out.println("Ingrese ID");
                id = sc.nextLine();
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                System.out.println("Ingrese descripcion");
                descripcion = sc.nextLine();
                System.out.println("Ingrese factor de millas");
                factor = sc.nextLine();
                factorDeMillas = Validaciones.real(factor);
                System.out.println("Ingrese origen");
                origen = sc.nextLine();
                if (s.verificaLugar(origen)) {
                    Lugar o = new Lugar(origen);
                    System.out.println("Ingrese destino: ");
                    destino = sc.nextLine();
                    if (s.verificaLugar(destino)) {
                        Lugar d = new Lugar(destino);
                        Turista turista = new Turista(id, nombre, descripcion, factorDeMillas, o, d, e);
                        s.agregaViajeCanjeableTurista(turista);
                        System.out.println("Viaje canjeable agregado.");
                    } else {
                        System.out.println("El destino no es valido");
                    }

                } else {
                    System.out.println("El origen no es valido.");
                }
                break;
            default:
                System.out.println("El tipo no es correcto.");
                break;
        }
        administraViajesCanjeables();
    }

    private void eliminaViajeCanjeable() {
        Scanner sc = new Scanner(System.in);
        String id;
        System.out.println("Ingrese ID del viaje canjeable a eliminar");
        id = sc.nextLine();
        if (s.eliminaViajeCanjeable(id)) {
            System.out.println("Viaje canjeable eliminado.");
        } else {
            System.out.println("El ID no se ha encontrado.");
        }
        administraViajesCanjeables();
    }

    private void modificaViajesCanjeables() {
        Scanner sc = new Scanner(System.in);
        String id, mod, nombre, descripcion, factordemillas, estado;
        Estado e;
        s.muestraViajesCanjeables();
        System.out.println("Ingrese ID del viaje canjeable a modificar");
        id = sc.nextLine();
        Viaje v = s.buscaViajeCanjeable(id);
        if (v != null) {
            System.out.println("Ingrese lo que desea moddificar : NOMBRE - DESCRIPCION - FACTORDEMILLAS - ESTADO");
            mod = sc.nextLine();
            switch (mod) {
                case "NOMBRE":
                    System.out.println("Escriba nuevo nombre");
                    nombre = sc.nextLine();
                    if (Validaciones.sonEspacios(nombre) || !Validaciones.sonCaracteresCorrectos(nombre)) {
                        System.out.println("No puede existir una persona con ese nombre ");
                    } else {

                        s.modificaNombreGenerador(v, nombre);
                        System.out.println("Nombre Modificado");
                    }
                    break;
                case "DESCRIPCION":
                    System.out.println("Escriba nueva descripcion");
                    descripcion = sc.nextLine();
                    s.modificaDescripcionGenerador(v, descripcion);
                    System.out.println("Descripcion Modificada");
                    break;
                case "FACTORDEMILLAS":
                    System.out.println("Escriba nuevo factor de millas");
                    factordemillas = sc.nextLine();
                    long modfactor = Validaciones.longg(factordemillas);
                    if (Validaciones.esMayorACero(modfactor)) {
                        s.modificaFactorDeMillasGenerador(v, modfactor);
                        System.out.println("Factor de Millas Modificado");
                    } else {
                        System.out.println("El factor de millas debe ser mayor a 0");
                    }
                    break;
                case "ESTADO":
                    System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
                    estado = sc.nextLine();
                    switch (estado) {
                        case "D":
                            e = Estado.DISPONIBLE;
                            s.modificaEstadoGenerador(v, e);
                            break;
                        case "S":
                            e = Estado.SUSPENDIDO;
                            s.modificaEstadoGenerador(v, e);
                            break;
                        case "C":
                            e = Estado.CADUCO;
                            s.modificaEstadoGenerador(v, e);
                            break;
                        default:
                            System.out.println("Estado incorrecto");
                            administraGeneradoresDeMillas();
                            break;
                    }
                    System.out.println("Estado Modificado");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    administraGeneradoresDeMillas();
                    break;
            }
        } else {
            System.out.println("No se encontro / no existe Generador");
        }
        administraViajesCanjeables();
    }

    private void mostrarViajesCanjeables() {
        Scanner sc = new Scanner(System.in);
        System.out.println("VIAJES CANJEABLES:");
        s.muestraViajesCanjeables();
        System.out.println("Presione para continuar...");
        sc.nextLine();
        administraViajesCanjeables();
    }

    private void administraTablaDeDestinos() {
        System.out.println("..........................ADMINISTRA TABLA DE DESTINOS............................");
        System.out.println("        1)  AGREGAR TABLA DE DESTINOS(ALTA)");
        System.out.println("        2)  ELIMINAR TABLA DE DESTINOS(BAJA)");
        System.out.println("        3)  MODIFICAR TABLA DE DESTINOS");
        System.out.println("        4)  MOSTRAR TABLA DE DESTINOS");
        System.out.println("        5)  BUSQUEDA");
        System.out.println("        6)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                agregaTablaDeDestinos();
                break;
            case 2:
                eliminaTablaDeDestinos();
                break;
            case 3:
                modificaTablaDeDestinos();
                break;
            case 4:
                mostrarTablaDeDestinos();
                break;
            case 5:
                buscarTablaDestino();
                break;
            case 6:
                iniciar();
                break;
        }
    }

    private void agregaTablaDeDestinos() {
        Scanner sc = new Scanner(System.in);
        String origen, destino, millasCanje, millasObtenidas;
        System.out.println("Ingrese origen");
        origen = sc.nextLine();
        System.out.println("Ingrese destino");
        destino = sc.nextLine();
        System.out.println("Ingrese millas para canje");
        millasCanje = sc.nextLine();
        long millasC = Validaciones.longg(millasCanje);
        System.out.println("Ingrese millas obtenidas");
        millasObtenidas = sc.nextLine();
        long millasO = Validaciones.longg(millasObtenidas);
        s.agregaFilaDestinos(origen, destino, millasC, millasO);
        administraTablaDeDestinos();
    }

    private void eliminaTablaDeDestinos() {
        Scanner sc = new Scanner(System.in);
        String origen, destino;
        System.out.println("Ingrese origen ");
        origen = sc.nextLine();
        System.out.println("Ingrese destino ");
        destino = sc.nextLine();
        if (s.eliminaTablaDestinos(origen, destino)) {
            System.out.println("Tabla eliminada.");
        } else {
            System.out.println("La tabla no se ha encontrado.");
        }
        administraTablaDeDestinos();
    }

    private void modificaTablaDeDestinos() {
        Scanner sc = new Scanner(System.in);
        String menu, origen, destino, millasCanje, millasObtenidas;
        s.muestraTablaDeDestinos();
        System.out.println("Ingrese Origen a modificar");
        origen = sc.nextLine();
        if (s.verificaLugar(origen)) {
            System.out.println("Ingrese Destino a modificar");
            destino = sc.nextLine();
            if (s.verificaLugar(destino)) {
                if (s.verficaFilaDestino(origen, destino)) {
                    FilaDeDestinos fila = s.buscaFiladeDestinos(origen, destino);
                    System.out.println("Escriba que desea modificar MILLASCANJE - MILLASOBTENIDAS");
                    menu = sc.nextLine();
                    switch (menu) {
                        case "MILLASOBTENIDAS":
                            System.out.println("Ingrese nuevas millas obtenidas");
                            millasObtenidas = sc.nextLine();
                            long millasOb = Validaciones.entero(millasObtenidas);
                            if (Validaciones.esMayorIgualACero(millasOb)) {
                                s.modificaMillasObtenidasFilaDestinos(fila, millasOb);
                                System.out.println("Millas obtenidas Modificadas");
                            } else {
                                System.out.println("Las millas obtenidas deben ser mayor o igual a 0");
                            }
                            break;
                        case "MILLASCANJE":
                            System.out.println("Ingrese nuevas millas para el canje");
                            millasCanje = sc.nextLine();
                            long millasCa = Validaciones.entero(millasCanje);
                            if (Validaciones.esMayorIgualACero(millasCa)) {
                                s.modificaMillasCanjeFilaDestinos(fila, millasCa);
                                System.out.println("Millas para Canje Modificadas");
                            } else {
                                System.out.println("Las millas para canje deben ser mayor o igual a 0");
                            }
                            break;
                        default:
                            System.out.println("Opcion incorrecta");
                    }
                } else {
                    System.out.println("Error. No existe la fila de destinos.");
                }
            } else {
                System.out.println("Error. No existe el destino.");
            }
        } else {
            System.out.println("Error. No existe el origen.");
        }
        administraTablaDeDestinos();
    }

    private void mostrarTablaDeDestinos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("TABLAS DE DESTINOS: ");
        s.muestraTablaDeDestinos();
        System.out.println("Presione para continuar...");
        sc.nextLine();
        administraTablaDeDestinos();
    }

    private void cargaArchivoLugares() {
        Scanner sc = new Scanner(System.in);
        //s.eliminaProductos();
        try {
            s.cargaLugares();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Archivo Lugares.txt cargado.");
        iniciar();
    }

    private void registrarMillas() {
        Scanner sc = new Scanner(System.in);
        try {
            s.cargaRegistroDeViajeros();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        s.registrarMillas();
        System.out.println("Se registraron millas a partir de archivo Registro.XML.");
        iniciar();
    }

    private void canjearProductos() {
        s.muestraProductos();
        Scanner sc = new Scanner(System.in);
        String dni, nombre;
        System.out.println("Ingrese DNI");
        dni = sc.nextLine();
        long Dni = Validaciones.longg(dni);
        System.out.println("Ingrese nombre");
        nombre = sc.nextLine();
        s.canjeDeProducto(Dni, nombre);
        sc.nextLine();
        iniciar();
    }

    private void canjearViajes() {
        s.muestraViajesCanjeables();
        Scanner sc = new Scanner(System.in);
        String dni, id;
        System.out.println("Ingrese ID");
        id = sc.nextLine();
        System.out.println("Ingrese DNI");
        dni = sc.nextLine();
        long Dni = Validaciones.longg(dni);
        s.canjeDeViajes(id, Dni);
        sc.nextLine();
        iniciar();
    }

    private void buscarViajeros() {
        Scanner sc = new Scanner(System.in);
        String nombre, dni, menu;
        System.out.println("Ingrese atributo a buscar: NOMBRE - DNI");
        menu = sc.nextLine();
        switch (menu) {
            case "NOMBRE":
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                s.busquedaViajeroNombre(nombre);
                break;
            case "DNI":
                System.out.println("Ingrese DNI");
                dni = sc.nextLine();
                s.busquedaViajeroDNI(dni);
                break;
            default:
                System.out.println("Atributo erroneo.");
        }
        administraViajeros();
    }

    private void buscarGeneradorDeMillas() {
        Scanner sc = new Scanner(System.in);
        String nombre, id, menu, estado;
        Estado e;
        System.out.println("Ingrese atributo a buscar: NOMBRE - ID - ESTADO");
        menu = sc.nextLine();
        switch (menu) {
            case "NOMBRE":
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                s.busquedaGeneradorDeMillasNombre(nombre);
                break;
            case "ID":
                System.out.println("Ingrese ID");
                id = sc.nextLine();
                s.busquedaGeneradorDeMillasID(id);
                break;
            case "ESTADO":
                System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
                estado = sc.nextLine();
                switch (estado) {
                    case "D":
                        e = Estado.DISPONIBLE;
                        s.busquedaGeneradorDeMillasEstado(e);
                        break;
                    case "S":
                        e = Estado.SUSPENDIDO;
                        s.busquedaGeneradorDeMillasEstado(e);
                        break;
                    case "C":
                        e = Estado.CADUCO;
                        s.busquedaGeneradorDeMillasEstado(e);
                        break;
                    default:
                        System.out.println("Estado incorrecto");
                        break;
                }
                break;
            default:
                System.out.println("Atributo erroneo.");
                break;
        }
        administraGeneradoresDeMillas();
    }

    private void buscarViajesCanjeables() {
        Scanner sc = new Scanner(System.in);
        String nombre, id, menu, estado, origen, destino;
        Estado e;
        System.out.println("Ingrese atributo a buscar: NOMBRE - ID - ESTADO - ORIGEN - DESTINO");
        menu = sc.nextLine();
        switch (menu) {
            case "NOMBRE":
                System.out.println("Ingrese nombre");
                nombre = sc.nextLine();
                s.busquedaViajesCanjeablesNombre(nombre);
                break;
            case "ID":
                System.out.println("Ingrese ID");
                id = sc.nextLine();
                s.busquedaViajesCanjeablesId(id);
                break;
            case "ESTADO":
                System.out.println("Ingrese estado (D: Disponible, S: Suspendido, C: Caduco)");
                estado = sc.nextLine();
                switch (estado) {
                    case "D":
                        e = Estado.DISPONIBLE;
                        s.busquedaViajesCanjeablesEstado(e);
                        break;
                    case "S":
                        e = Estado.SUSPENDIDO;
                        s.busquedaViajesCanjeablesEstado(e);
                        break;
                    case "C":
                        e = Estado.CADUCO;
                        s.busquedaViajesCanjeablesEstado(e);
                        break;
                    default:
                        System.out.println("Estado incorrecto");
                        break;
                }
                break;
            case "ORIGEN":
                System.out.println("Ingrese origen");
                origen = sc.nextLine();
                s.busquedaViajesCanjeablesOrigen(origen);
                break;
            case "DESTINO":
                System.out.println("Ingrese destino");
                destino = sc.nextLine();
                s.busquedaViajesCanjeablesDestino(destino);
                break;
            default:
                System.out.println("Atributo erroneo.");
                break;
        }
        administraViajesCanjeables();
    }

    private void buscarTablaDestino() {
        Scanner sc = new Scanner(System.in);
        String menu, origen, destino;
        System.out.println("Ingrese atributo a buscar: ORIGEN - DESTINO");
        menu = sc.nextLine();
        switch (menu) {
            case "ORIGEN":
                System.out.println("Ingrese origen");
                origen = sc.nextLine();
                s.busquedaTablaDestinoOrigen(origen);
                break;
            case "DESTINO":
                System.out.println("Ingrese destino");
                destino = sc.nextLine();
                s.busquedaTablaDestinoDestino(destino);
                break;
            default:
                System.out.println("Atributo erroneo.");
                break;
        }
        administraTablaDeDestinos();
    }

    private void cargaArchivoDatos() {
        try {
            s.cargaDatosViajeros();
            s.cargaDatosDeTablaDestino();
            s.cargaGeneradorConsumo();
            s.cargaGeneradorCombustible();
            s.cargaGeneradorHotel();
            s.cargaViajes();
            s.cargaViajesCanjeables();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Lote de datos cargado.");
        iniciar();
    }
}
