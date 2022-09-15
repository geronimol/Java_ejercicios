/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;

/**
 *
 * @author Cliente
 */
public class Menu {
    private Agencia a;
    
    public Menu(Agencia a){
        this.a=a;
}
    
    public void iniciar(){
        System.out.println("..........................M E N U   A G E N C I A...............................");
        System.out.println("        1)  ADMINISTRAR VEHICULOS(ABM)");
        System.out.println("        2)  ADMINISTRAR RESPONSABLES A BORDO(ABM)");
        System.out.println("        3)  CARGAR ARCHIVO DESTINOS.TXT");
        System.out.println("        4)  CREAR NUEVOS VIAJES");
        System.out.println("        5)  MONITOREO DE VIAJES");
        System.out.println("        6)  REPORTES");
        System.out.println("                                                        0)  SALIR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion=sc.nextInt();
        switch (opcion) {
            case 0:  
                     break;
            case 1:  administraVehiculos();
                     break;
            case 2:  administraResponsablesAbordo();
                     break;
            case 3:  cargaArchivoDestinos();
                     break;
            case 4:  creaNuevosViajes();
                     break;
            case 5: monitoreoViajes();
                     break;
            case 6: reportes();
                    break;
            default: System.out.println("Esa opción no existe, intente nuevamente.");
                     iniciar();
                     break;
        }
    }

    private void administraVehiculos() {
        System.out.println("..........................ADMINISTRA    VEHICULOS...............................");
        System.out.println("        1)  AGREGAR VEHICULO(ALTA)");
        System.out.println("        2)  ELIMINAR VEHICULO(BAJA)");
        System.out.println("        3)  MODIFICAR VEHICULO");
        System.out.println("        4)  MOSTRAR TODOS LOS VEHICULOS");
        System.out.println("        5)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion=sc.nextInt();
        switch (opcion) {
            case 1:  agregaVehiculo();
                     break;
            case 2:  eliminaVehiculo();
                     break;
            case 3:  modificarVehiculo();
                     break;
            case 4:  mostrarVehiculos();
                     break;
            case 5:  iniciar();
                     break;
            default: System.out.println("Esa opción no existe, intente nuevamente.");
                     administraVehiculos();
                     break;
        }
    
    }

    private void agregaVehiculo() {
           Scanner sc = new Scanner(System.in);
           String patente,tipo;
           float velocidadPromedio;
           System.out.println("Ingrese tipo de vehiculo: AUTO-COMBI-COCHECAMA-SEMICAMA");
           tipo=sc.nextLine();
           System.out.println("Ingrese patente");
           patente=sc.nextLine();
           System.out.println("Ingrese velocidad promedio");
           velocidadPromedio=sc.nextFloat();
           a.agregaVehiculo(tipo,patente, velocidadPromedio);
           System.out.println("Vehiculo agregado.");
           System.out.println("..........................ADMINISTRA    VEHICULOS...............................");
           System.out.println("        1)  AGREGAR OTRO VEHICULO");
           System.out.println("        2)  MENU ANTERIOR");
           System.out.println("        3)  MENU PRINCIPAL");
           System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
           int opcion=sc.nextInt();
            switch (opcion) {
                case 1:  agregaVehiculo();
                        break;
                case 2:  administraVehiculos();
                        break;
                case 3:  iniciar();
                        break;
            }
    }

    private void eliminaVehiculo() {
        Scanner sc = new Scanner(System.in);
           String patente;
           
           System.out.println("Ingrese patente del vehiculo a eliminar");
           patente=sc.nextLine();
           if(a.eliminaVehiculo(patente))
               System.out.println("Vehiculo eliminado.");
           else
               System.out.println("La patente no se ha encontrado.");
           System.out.println("..........................ADMINISTRA    VEHICULOS...............................");
           System.out.println("        1)  ELIMINAR OTRO VEHICULO");
           System.out.println("        2)  MENU ANTERIOR");
           System.out.println("        3)  MENU PRINCIPAL");
           System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
           int opcion=sc.nextInt();
            switch (opcion) {
                case 1:  eliminaVehiculo();
                        break;
                case 2:  administraVehiculos();
                        break;
                case 3:  iniciar();
                        break;
            }}

    private void modificarVehiculo() {
        Scanner sc = new Scanner(System.in);
           String patente;
           System.out.println("Ingrese patente del vehiculo a modificar");
           patente=sc.nextLine();
           a.modificaVehiculo(patente);
           System.out.println("..........................ADMINISTRA    VEHICULOS...............................");
           System.out.println("        1)  MODIFICAR OTRO VEHICULO");
           System.out.println("        2)  MENU ANTERIOR");
           System.out.println("        3)  MENU PRINCIPAL");
           System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
           int opcion=sc.nextInt();
            switch (opcion) {
                case 1:  modificarVehiculo();
                        break;
                case 2:  administraVehiculos();
                        break;
                case 3:  iniciar();
                        break;
            }
    }
    
    private void mostrarVehiculos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("VEHICULOS:");
        a.muestraVehiculos();
        System.out.println("Presione para continuar...");
        sc.nextLine();
        administraVehiculos();
    }

    private void administraResponsablesAbordo() {
        System.out.println("..........................ADMINISTRA RESPONSABLES A BORDO............................");
        System.out.println("        1)  AGREGAR RESPONSABLE A BORDO(ALTA)");
        System.out.println("        2)  ELIMINAR RESPONSABLE A BORDO(BAJA)");
        System.out.println("        3)  MODIFICAR RESPONSABLE A BORDO");
        System.out.println("        4)  MOSTRAR RESPONSABLES A BORDO");
        System.out.println("        5)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion=sc.nextInt();
        switch (opcion) {
            case 1:  agregaResponsableAbordo();
                     break;
            case 2:  eliminaResponsableAbordo();
                     break;
            case 3:  modificarResponsableAbordo();
                     break;
            case 4:  mostrarResponsablesAbordo();
                     break;       
            case 5:  iniciar();
                     break;
        }}

    private void agregaResponsableAbordo() {
        Scanner sc = new Scanner(System.in);
           String nombre;
           float velocidadPromedio;
           System.out.println("Ingrese nombre");
           nombre=sc.nextLine();
           System.out.println("Ingrese dni");
           int dni=sc.nextInt();
           System.out.println("Ingrese sueldo");
           float sueldo=sc.nextFloat();
           a.agregaResponsableAbordo(nombre, dni, sueldo);
           System.out.println("Responsable a bordo agregado.");
           System.out.println("..........................ADMINISTRA RESPONSABLES A BORDO............................");
           System.out.println("        1)  AGREGAR OTRO RESPONSABLE A BORDO");
           System.out.println("        2)  MENU ANTERIOR");
           System.out.println("        3)  MENU PRINCIPAL");
           System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
           int opcion=sc.nextInt();
            switch (opcion) {
                case 1:  agregaResponsableAbordo();
                        break;
                case 2:  administraResponsablesAbordo();
                        break;
                case 3:  iniciar();
                        break;
            }
    }

    private void eliminaResponsableAbordo() {
        Scanner sc = new Scanner(System.in);
           int dni;           
           System.out.println("Ingrese DNI del responsable a bordo a eliminar");
           dni=sc.nextInt();
           if(a.eliminaResponsableAbordo(dni))
               System.out.println("Responsable a bordo eliminado.");
           else
               System.out.println("El DNI no se ha encontrado.");
           System.out.println("..........................ADMINISTRA RESPONSABLES A BORDO............................");         
           System.out.println("        1)  ELIMINAR OTRO RESPONSABLE A BORDO");
           System.out.println("        2)  MENU ANTERIOR");
           System.out.println("        3)  MENU PRINCIPAL");
           System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
           int opcion=sc.nextInt();
            switch (opcion) {
                case 1:  eliminaResponsableAbordo();
                        break;
                case 2:  administraResponsablesAbordo();
                        break;
                case 3:  iniciar();
                        break;
            }}

    private void modificarResponsableAbordo() {
        Scanner sc = new Scanner(System.in);
           int dni;
           System.out.println("Ingrese DNI del responsable a bordo a modificar");
           dni=sc.nextInt();
           a.modificaResponsableAbordo(dni);
           System.out.println("..........................ADMINISTRA RESPONSABLES A BORDO............................");
           System.out.println("        1)  MODIFICAR OTRO RESPONSABLE A BORDO");
           System.out.println("        2)  MENU ANTERIOR");
           System.out.println("        3)  MENU PRINCIPAL");
           System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
           int opcion=sc.nextInt();
            switch (opcion) {
                case 1:  modificarResponsableAbordo();
                        break;
                case 2:  administraResponsablesAbordo();
                        break;
                case 3:  iniciar();
                        break;
            }
    }

    private void mostrarResponsablesAbordo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("RESPONSABLES A BORDO:");
        a.muestraResponsablesAbordo();
        System.out.println("Presione para continuar...");
        sc.nextLine();
        administraResponsablesAbordo();
    }

    private void cargaArchivoDestinos() {
        Scanner sc = new Scanner(System.in);
        a.eliminaDestinos();
        try {
            a.cargarDestinos();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Archivo Destinos.txt cargado. Presione para continuar...");
        sc.nextLine();
        iniciar();
    }

    private void creaNuevosViajes() {
        System.out.println("-------------------->CREANDO NUEVO VIAJE<----------------------");
        a.creaNuevoViaje();
        iniciar();
    }

    private void monitoreoViajes() {
        System.out.println("..........................MONITOREO   VIAJES...............................");
        System.out.println("        1)  INICIAR SIMULACIÓN(comenzar viajes pendientes)");
        System.out.println("        2)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion=sc.nextInt();
        switch (opcion) {
            case 1:  simulacion();
                     break;
            case 2:  iniciar();
                     break;
        }
    }    

    private void simulacion() {
        System.out.println("..........................MONITOREO   VIAJES...............................");
        System.out.println(":::::::::::::::::::::::::INICIANDO SIMULACIÓN::::::::::::::::::::::::::::::");
        a.iniciarViajes();
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("Finalizando simulación, liberando recursos.");
        a.detenerViajes();
        sc.nextLine();
        iniciar();
    }

    private void reportes() {
        System.out.println("..........................GENERA    REPORTES...............................");
        System.out.println("        1)  RECAUDACION DE VIAJES REALIZADOS");
        System.out.println("        2)  RANKING DE RESPONSABLES A BORDO(ordenado por kms recorridos)");
        System.out.println("        3)  REGRESAR");
        System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        Scanner sc = new Scanner(System.in);
        int opcion=sc.nextInt();
        switch (opcion) {
            case 1:  recaudacionTotal();
                     break;
            case 2:  rankingPorKms();
                     break;
            case 3:  iniciar();
                     break;
        }
    }


    private void recaudacionTotal() {
        Scanner sc = new Scanner(System.in);
        float total;
        try {
            total = a.recaudacionTotal();
            System.out.println("Recaudación total:"+total);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sc.nextLine();
        reportes();
    }

    private void rankingPorKms() {
        Scanner sc = new Scanner(System.in);
        try {
            a.rankingPorKms();
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        sc.nextLine();
        reportes();
    }
}