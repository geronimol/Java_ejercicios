package Restaurante;

import Productos.*;
import java.util.GregorianCalendar;
import java.util.Iterator;

import Restaurante.IngredientesTotales;
import Restaurante.Fecha;
import InterfaceGraf.*;

public class Restaurante {

	/**
	 * @param args
	 */
	
	public ListaProductos listap;
	public ListaMenus listamenus;
	public IngredientesTotales ingtot;
	
	public Restaurante() {
			
		listap=new ListaProductos();
		listamenus=new ListaMenus();
		ingtot=new IngredientesTotales();
		listap.Cargar();
		listamenus.Cargar(); 
		ingtot.Cargar();
		
		
		
		/*Inicio ventana = new Inicio();
		ventana.setVisible(true);*/
		
		
		listamenus.Mostrar();
		listap.MostrarProductos();
			
		Fecha fecha=new Fecha(11,2,2000);
		Menu m=new Menu(fecha);
		m.GeneraMenuActivos(listap.GetTreeSetListaProd()); 
		
		ingtot.GetIng(0).ModificarCosto(5000);
		
		listamenus.Mostrar();
		
		
		//Ingrediente i=new Ingrediente(1,"gr","nombre");
		//IngredienteP iP=new IngredienteP(i,1);
		//iP.GetIng().ModificarCosto(1);
		
		
		
	
	}
}
	
