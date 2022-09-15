package Restaurante;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class ListaMenus implements Serializable{
	
	private LinkedList<Menu> menus;
	private static final long serialVersionUID = 1L;
	
	public ListaMenus(){
		menus=new LinkedList<Menu>();
	}
	
	public void AgregaMenu(Menu m){
		
		menus.add(m);
		
	}
	
	public LinkedList<Menu> GetLinkedMenus(){return menus;}
	
	public void Mostrar(){
		for( Iterator<Menu> it = menus.iterator(); it.hasNext();) {
			Menu menu = (Menu)it.next();
			menu.MuestraListaProd();
			}
		
	}
	
	public void Guardar(){
		Serializa.GuardarMenus(menus);
		
	}
	
	public void Cargar(){
		menus=Serializa.CargaMenus();
	}

}
