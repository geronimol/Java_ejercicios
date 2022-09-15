package Restaurante;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

import Productos.Producto;

public class ListaProductos implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private TreeSet<Producto> listaProd;
	
	
	
	public TreeSet<Producto> GetListaProd(){return listaProd;}
	
	public ListaProductos(){listaProd=new TreeSet<Producto>();}
	
	public void AgregarProducto(Producto prod){
		
		listaProd.add(prod);
	}
	
	public TreeSet<Producto> GetTreeSetListaProd(){return listaProd;}
	
	public void MostrarProductos(){
		for( Iterator<Producto> it = listaProd.iterator(); it.hasNext();) {
			Producto producto = (Producto)it.next();
			producto.Mostrar();
			}
	}
	
	public void Guardar(){
		Serializa.GuardarProductos(listaProd);
		
	}
	public void Cargar(){
		listaProd=Serializa.CargaProductos();
	}
	
	public void EliminarProducto(Producto prod){

		if (listaProd.contains(prod)){
			if (prod.GetEliminable()){
				listaProd.remove(prod);
				System.out.println("se puede eliminar pibe");
			}	
			else
				System.out.println("no se puede eliminar PORQUE ESTA EN UN MENU");
		}
		else
			System.out.println("no se puede eliminar PORQUE NO EXISTE");
	}
	public void BuscarProductoTotales(String buscado){
		for( Iterator<Producto> it = listaProd.iterator(); it.hasNext();){
			
			Producto producto = (Producto)it.next();
			if (producto.GetNom().startsWith(buscado)){
				producto.Mostrar();
			}
		}
		
		
	}
	
	/*public void Modif (String prod){
		
			for( Iterator<Producto> it = listaProd.iterator(); it.hasNext();){
				Producto producto = (Producto)it.next();
				if(producto.GetNom().equals(prod)){
					producto.SetPVenta(9);
				}
			}
					
	}*/
	
	public void BuscarProductoPorGrupo(String buscado,String grupo){
		
		for( Iterator<Producto> it = listaProd.iterator(); it.hasNext();){
			
			Producto producto = (Producto)it.next();
			if (producto.getClass().getName().substring(10,producto.getClass().getName().length()).equals(grupo))
					if(producto.GetNom().startsWith(buscado)){
				producto.Mostrar();
			}
		
		}
	}
	

}
