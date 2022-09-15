/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Agencia;

import Comparador.DniComp;
import Comparador.KmRecorridosComp;
import Serializa.Carga;
import Serializa.Guarda;
import Vehiculo.Auto;
import Vehiculo.CocheCama;
import Vehiculo.Combi;
import Vehiculo.SemiCama;
import Vehiculo.Vehiculo;
import Viaje.CortaDistancia;
import Viaje.Destino;
import Viaje.LargaDistancia;
import Viaje.ResponsableAbordo;
import Viaje.Viaje;
import Viaje.Viaje.Estado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;

/**
 *
 * @author Cliente
 */
public class Agencia implements Serializable{

    /**
     * @param args the command line arguments
     */
    private static Agencia agencia;
    private TreeSet<Viaje> viajes;
    private TreeSet<Vehiculo> vehiculos;
    private TreeSet<Destino> destinos;
    private TreeSet<ResponsableAbordo> responsablesAbordo;
    private int numero;
    //Constructor
    private Agencia(){
        viajes=new TreeSet<Viaje>();
        vehiculos=new TreeSet<Vehiculo>();
        responsablesAbordo=new TreeSet<ResponsableAbordo>(new DniComp());
        destinos=new TreeSet<Destino>();
        numero=0;
        
    }
    //Crea una única instancia de Agencia
    public static Agencia getInstanciaSingleton(){
        if(agencia==null)
            agencia=new Agencia();
        else
            System.out.println("No se puede crear el objeto, ya existe una instancia");
        return agencia;
    }
        
        public static void main(String[] args) {
        // TODO code application logic here
        Agencia a=Agencia.getInstanciaSingleton();
        //Carga archivo serializado.
        a=Carga.cargaAgencia(a);
        //Carga archivo Destinos.txt
         try {
            a.cargarDestinos();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Agencia.class.getName()).log(Level.SEVERE, null, ex);
        }
         //Agrego algunos vehiculos...
        a.agregaVehiculo("AUTO","123asd", 2);
        a.agregaVehiculo("AUTO","321asd", 3);
        a.agregaVehiculo("AUTO","333asd", 2);
        a.agregaVehiculo("COMBI","444asd", 1);
        a.agregaVehiculo("COMBI","444DSA", 3);
        a.agregaVehiculo("COCHECAMA","555ddd", 1);
        a.agregaVehiculo("COCHECAMA","555asd", 1);
        a.agregaVehiculo("SEMICAMA","666DSA", 3);
        a.agregaVehiculo("SEMICAMA","666ddd", 1);
        //Inicia el menu por salida estandar.
        Menu m=new Menu(a);
        m.iniciar();
        //Guarda archivo serializado.
        Guarda.guardaAgencia(a);
       
    }

    

    //Alta vehículo.
    public void agregaVehiculo(String tipo,String patente,float velocidadPromedio){
        switch (tipo) {
            case "AUTO":
                vehiculos.add(new Auto(patente,velocidadPromedio));
                break;
            case "COMBI":
                vehiculos.add(new Combi(patente,velocidadPromedio));
                break;
            case "COCHECAMA":
                vehiculos.add(new CocheCama(patente,velocidadPromedio));
                break;
            case "SEMICAMA":
                vehiculos.add(new SemiCama(patente,velocidadPromedio));
                break;
            default:
                System.out.println("El tipo no es correcto.");
                break;
        }
    }
    //Baja vehículo.
    public boolean eliminaVehiculo(String patente){
        Iterator it=vehiculos.iterator();
        while(it.hasNext() && ((Vehiculo)it.next()).getPatente().equals(patente)){
            it.remove();
            return true;
        }
        return false;
            
    }
    //Modifica vehículo.(por salida estándar)
    public void modificaVehiculo(String patenteVehiculo){
        Vehiculo v=buscaVehiculo(patenteVehiculo);
        if(v==null)
            System.out.println("El vehiculo no existe o no se encuentra disponible.");
        else{
            Scanner sc = new Scanner(System.in);
            System.out.println("    Modificar   ");
            System.out.println("    1)Modificar patente");
            System.out.println("    2)Modificar capacidad de pasajeros");
            System.out.println("    3)Regresar");
            int opcion=sc.nextInt();
            switch (opcion) {
                case 1:  modificarPatente(v);
                        break;
                case 2:  modificarCantPasajeros(v);
                        break;
                case 3:  
                        break;
            }
        }
    }
    private void modificarPatente(Vehiculo v) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese nueva patente del vehículo.");
        String patente=sc.nextLine();
        v.setPatente(patente);
        System.out.println("Patente del vehículo modificado.");
    }
    
    private void modificarCantPasajeros(Vehiculo v) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese nueva capacidad de pasajeros del vehículo.");
        int capacidad=sc.nextInt();
        v.setCapacidadPasajeros(capacidad);
        System.out.println("Capacidad del vehículo modificado.");
    }
    
    private Vehiculo buscaVehiculo(String patenteVehiculo) {
        for(Iterator it=vehiculos.iterator(); it.hasNext();){
            Vehiculo v =(Vehiculo)it.next();
            if(v.getPatente().equals(patenteVehiculo) && v.estaDisponible())
                return v;
        }
        return null;
    }
    
    
    public void muestraVehiculos(){
        Iterator it=vehiculos.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
    public void muestraVehiculosDisponibles(){
        for(Vehiculo v: vehiculos){
            if(v.estaDisponible())
                System.out.println(v);
        }
    } 
    
    //Alta responsable a bordo.
    public void agregaResponsableAbordo(String nombre,int dni,float sueldo){
        responsablesAbordo.add(new ResponsableAbordo(nombre,dni,sueldo));
    }
    //Baja responsable a bordo.
    public boolean eliminaResponsableAbordo(int dni){
        Iterator it=responsablesAbordo.iterator();
        while(it.hasNext() && ((ResponsableAbordo)it.next()).getDni()==dni){
            it.remove();
            return true;
        }
        return false;
    }
    
    public void muestraResponsablesAbordo(){
        Iterator it=responsablesAbordo.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    public void muestraResponsablesAbordoDisponibles(){
        for(ResponsableAbordo r: responsablesAbordo)
            if(r.estaDisponible())
                System.out.println(r);
    }
    public ResponsableAbordo buscaResponsablesAbordo(int dni){
        for(Iterator it=responsablesAbordo.iterator(); it.hasNext();){
            ResponsableAbordo r =(ResponsableAbordo)it.next();
            if(r.getDni()==dni && r.estaDisponible())
                return r;
        }
        return null;
    }
    //Carga destinos desde archivo de texto.
    public void cargarDestinos() throws FileNotFoundException{
        
        try (BufferedReader in = new BufferedReader(new FileReader("Destinos.txt"))) {
            String s, nombre;
            String[] s3;
            float km;
            while((s = in.readLine())!= null){
                s3= s.split(" ");
                nombre=s3[0];
                km = Float.parseFloat(s3[1]);
                agregaDestino(nombre,km);
            }

        } catch (IOException ex) {
            Logger.getLogger(Agencia.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    private void agregaDestino(String nombre, float km) {
        destinos.add(new Destino(nombre,km));
    }
    
    public void eliminaDestinos(){
        destinos.clear();
    }
    
    private void eliminaDestino(String nombre){
        Iterator it=destinos.iterator();
        while(it.hasNext() && ((Destino)it.next()).getNombre().equals(nombre))
            it.remove();
    }
    
    public Destino buscaDestino(String nombre){
        for(Iterator it=destinos.iterator(); it.hasNext();){
            Destino d =(Destino)it.next();
            if(d.getNombre().equals(nombre))
                return d;
        }
        return null;
    }
    
    private void muestraDestinos() {
        Iterator it=destinos.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
    private Destino solicitaDestino(){
        Scanner sc = new Scanner(System.in);
        String nombreDestino;
        System.out.println("Ingrese nombre de alguno de los siguientes destinos:");
        muestraDestinos();
        nombreDestino=sc.nextLine();
        Destino d=buscaDestino(nombreDestino);
        if(d==null)
            System.out.println("El destino no se ha encontrado.");
        return d;
    }
    
    private int solicitaCantPasajeros(){
        Scanner sc = new Scanner(System.in);
        int cantPasajeros;
        System.out.println("Ingrese cantidad de pasajeros.");
        cantPasajeros=sc.nextInt();
        if(cantPasajeros==0)
            System.out.println("No puede tener 0 pasajeros.");
        return cantPasajeros;
    }
    
    private Vehiculo solicitaVehiculo(){
        Scanner sc = new Scanner(System.in);
        String patenteVehiculo;
        System.out.println("Ingrese alguna de las siguientes PATENTES:");
        muestraVehiculosDisponibles();
        patenteVehiculo=sc.nextLine();
        Vehiculo v=buscaVehiculo(patenteVehiculo);
        if(v==null)
            System.out.println("El vehiculo no se ha encontrado o no se encuentra disponible.");
        return v;
            
    }
        private boolean verificaListas() {
        if(destinos.isEmpty()){
            System.out.println("La lista de destinos no debe estar vacía.");
            return false;
        }    
        if(vehiculos.isEmpty()){
            System.out.println("La lista de vehiculos no debe estar vacía.");
            return false;
        }
        //Verifica si hay algun vehiculo disponible.
        for(Vehiculo v:vehiculos)
            if(v.estaDisponible())
                return true;
        System.out.println("Debe haber por lo menos un vehiculo disponible.");
        return false;
    }
        
    public void creaNuevoViaje(){
        if(verificaListas()){
            
            Destino destino=solicitaDestino();
            if(destino==null)
                System.out.println("No se pudo crear viaje.");
            else{
                int cantPasajeros=solicitaCantPasajeros();
                if(cantPasajeros==0)
                    System.out.println("No se pudo crear viaje.");
                else{
                    Vehiculo vehiculo=solicitaVehiculo();
                if(vehiculo==null)
                    System.out.println("No se pudo crear viaje.");
                else
                    creaViaje(destino,cantPasajeros,vehiculo);
                }
                
            }
        }
    }
    
    public void creaViaje(Destino destino,int cantPasajeros,Vehiculo vehiculo){
        String nombre=destino.getNombre()+(numero++);
        if(cantPasajeros>vehiculo.getCapacidadPasajeros())
            System.out.println("La cantidad de pasajeros supera la capacidad del vehículo.");  
        else
            if(destino.getKm() > 100){
                if(vehiculo instanceof Auto)
                    System.out.println("El vehiculo para larga distancia no puede ser un auto.");                  
                else{
                    if(vehiculo instanceof CocheCama){
                        asignaPlazas((CocheCama) vehiculo,cantPasajeros);
                    }
                    //System.out.println("Valor: "+calculaValor(vehiculo,destino.getKm(),0));
                    
                    ArrayList<ResponsableAbordo> listaResponsablesAbordo= asignaResponsablesAbordo();
                    if(listaResponsablesAbordo==null)
                        System.out.println("No se pudo crear viaje.");
                    else{
                        Viaje viaje=new LargaDistancia(nombre,destino,vehiculo,listaResponsablesAbordo,cantPasajeros);
                        viaje.setValorTotal(calculaValor(vehiculo,destino.getKm(),viaje.getValor(),cantPasajeros));
                        System.out.println("Valor: "+viaje.getValorTotal()+" Crear viaje? 1:SI 2:NO");
                        Scanner sc = new Scanner(System.in);
                        int opcion=sc.nextInt();
                        if(opcion==1){
                            viajes.add(viaje);
                            System.out.println("Viaje de larga distancia creado.");
                        }else
                            System.out.println("Cancelado."); 
                    }
                    
                }
            }
            else
                if(vehiculo instanceof CocheCama){
                    System.out.println("El vehiculo no puede ser coche-cama para corta distancia");
                    creaNuevoViaje();
                }                    
                else{
                    Viaje viaje=new CortaDistancia(nombre,destino,vehiculo,cantPasajeros);
                    viaje.setValorTotal(calculaValor(vehiculo,destino.getKm(), viaje.getValor(),cantPasajeros));
                    System.out.println("Valor: "+viaje.getValorTotal()+" Crear viaje? 1:SI 2:NO");
                    Scanner sc = new Scanner(System.in);
                    int opcion=sc.nextInt();
                    if(opcion==1){
                        viajes.add(viaje);
                        System.out.println("Viaje de corta distancia creado.");
                    }else
                        System.out.println("Cancelado.");
                }
                    
        
    }
    
    public void eliminaViaje(String nombre){
        for(Iterator it=viajes.iterator(); it.hasNext();){
            Viaje v =(Viaje)it.next();
            if(v.getNombre().equals(nombre))
                    it.remove();           
        }
    }
    
    public void muestraViajes(){
        Iterator it=viajes.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    
    private float calculaValor(Vehiculo vehiculo, float km, float valor,int cantPasajeros) {
        float total=0;
   
        if(vehiculo instanceof Auto){
            Auto a=((Auto)vehiculo);
            total=a.getValorBase()+a.getValorPorKm()*km;
        }
        else
            if(vehiculo instanceof Combi){
                Combi c=((Combi)vehiculo);
                total=c.getValorBase()+c.getValorPasajero()*cantPasajeros*km;
            }
            else
                if(vehiculo instanceof SemiCama){
                    SemiCama s=((SemiCama)vehiculo);
                    total=s.getValorPasajero()*cantPasajeros*km;
                }
                else
                    if(vehiculo instanceof CocheCama){
                            CocheCama c=((CocheCama)vehiculo);
                            total=c.getValorPasajero()*cantPasajeros*km+c.getPlazas()*c.getValorPlazas()*km;
                    }
                        
                    
        return total+valor;
    }

    private ArrayList<ResponsableAbordo> asignaResponsablesAbordo() {
            if(!verificaListaResAbordo())
                return null;
            Scanner sc = new Scanner(System.in);
            ArrayList<ResponsableAbordo> lista=new ArrayList<ResponsableAbordo>();
            int dni;
            char c='S';
            while(c=='S'){
                dni=solicitaDni();
                ResponsableAbordo r=buscaResponsablesAbordo(dni);
                if(r==null)
                    System.out.println("El responsable a bordo no existe.");
                else{
                    if(!lista.contains(r))
                        lista.add(r);
                    else
                        System.out.println("El responsable a bordo ya estaba en la lista.");
                    System.out.println("Asignar mas responsables a bordo? S/N");
                    c=(char)sc.next().charAt(0);
                }    
            }
            
        return lista;
    }

    private void asignaPlazas(CocheCama cocheCama,int cantPasajeros) {
        int plazas=99,maxPlazas=cocheCama.getMaxPlazas();
        if(cantPasajeros<maxPlazas)
            cocheCama.setPlazas(cantPasajeros);
        else
            cocheCama.setPlazas(maxPlazas);
        
    }

    public void iniciarViajes(){
        for(Viaje v: viajes)
            if(v.getEstado()==Estado.PENDIENTE){
                System.out.println("Nombre:"+v.getNombre()+" Kms totales:"+
                        v.getKmsTotales()+" Cant pasajeros:"+v.getCantPasajeros()+
                        " Transporte asignado:"+v.getTransporte()+" Valor:"+v.getValorTotal());
                v.iniciar();
            }
        
                
    }
    public void detenerViajes(){
        for(Viaje v: viajes)
            if(v.getEstado()==Estado.EN_CURSO)
                v.detenerViajes();
    }

    void modificaResponsableAbordo(int dni) {
        ResponsableAbordo r=buscaResponsablesAbordo(dni);
        if(r==null)
            System.out.println("El responsable a bordo no existe o no se encuentra disponible.");
        else{
            Scanner sc = new Scanner(System.in);
            System.out.println("    Modificar   ");
            System.out.println("    1)Modificar dni");
            System.out.println("    2)Modificar nombre");
            System.out.println("    3)Modificar sueldo");
            System.out.println("    4)Regresar");
            int opcion=sc.nextInt();
            switch (opcion) {
                case 1:  modificarDni(r);
                        break;
                case 2: modificarNombre(r);
                        break;
                case 3: modificarSueldo(r); 
                        break;
                case 4:  
                        break;
            }
        }
    }

    private void modificarDni(ResponsableAbordo r) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese nuevo DNI del responsable a bordo.");
        int dni=sc.nextInt();
        r.setDni(dni);
        System.out.println("DNI del responsable a bordo modificado.");
    }

    private void modificarNombre(ResponsableAbordo r) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese nuevo nombre del responsable a bordo.");
        String nombre=sc.nextLine();
        r.setNombre(nombre);
        System.out.println("Nombre del responsable a bordo modificado.");
    }

    private void modificarSueldo(ResponsableAbordo r) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese nuevo sueldo del responsable a bordo.");
        float sueldo=sc.nextFloat();
        r.setSueldo(sueldo);
        System.out.println("Sueldo del responsable a bordo modificado.");}

    private int solicitaDni() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Ingrese DNI de alguno de los siguientes responsables a bordo:");
            muestraResponsablesAbordoDisponibles();
            int dni=sc.nextInt();
            return dni;
    }

    private boolean verificaListaResAbordo() {
        if(responsablesAbordo.isEmpty()){
            System.out.println("La lista de responsables a bordo esta vacía.");
            return false;
        }  
        //Verifica si hay algun responsable a bordo disponible.
        for(ResponsableAbordo r:responsablesAbordo)
            if(r.estaDisponible())
                return true;
        System.out.println("Debe haber por lo menos un responsable a bordo disponible.");
        return false;
    }

    public float recaudacionTotal() throws IOException {
        FileWriter fichero = new FileWriter("RECAUDACION.txt");
        float total=0;
        for(Viaje v: viajes)
            if(v.getEstado()==Estado.FINALIZADO){
                System.out.println(v.toString());
                fichero.write(v.toString() + "\n");
                total+=v.getValorTotal();
            }
        fichero.write(" RECAUDACION TOTAL:"+total +"\n");
        fichero.close();        
        return total;
    }

    void rankingPorKms() throws IOException {
        FileWriter fichero = new FileWriter("RANKING_POR_KMS.txt");
        TreeSet<ResponsableAbordo> rankingKm=new TreeSet<ResponsableAbordo>(new KmRecorridosComp());
        for(Viaje v: viajes)
            if(v.getEstado()==Estado.FINALIZADO && v instanceof LargaDistancia){
                LargaDistancia ld=(LargaDistancia)v;
                ArrayList<ResponsableAbordo> lista=ld.getResponsablesAbordo();
                for(ResponsableAbordo r:lista){
                    r.sumaKmRecorridos(v.getKmsTotales());
                    rankingKm.add(r);
                }    
            } 
        for(ResponsableAbordo r: rankingKm){
            System.out.println(r.toString()+"   KMS RECORRIDOS:"+r.getKmRecorridos());
            //Escribe archivo.
            fichero.write(r.toString()+"   KMS RECORRIDOS:"+r.getKmRecorridos() + "\n");
        }
        fichero.close();    
    }
    
    
}
