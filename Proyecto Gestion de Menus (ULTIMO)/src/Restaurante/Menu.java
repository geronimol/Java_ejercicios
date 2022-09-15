package Restaurante;

import java.io.*;
import java.util.Iterator;
import java.util.TreeSet;

import Productos.Producto;
import Restaurante.ListaProductos;

public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Fecha fechaInicio;
	private Fecha fechaFinal;
	private ListaProductos listaProductos;    
	
	
	public ListaProductos GetListaProductos(){
		return listaProductos;
	}
	
	public Menu(Fecha fi){
		listaProductos=new ListaProductos();
		fechaInicio=fi;
	}
	
	public void GeneraMenuActivos(TreeSet<Producto> listaProd){
		Iterator<Producto> it = listaProd.iterator();
		while(it.hasNext()){
			Producto producto = (Producto)it.next();
			if (producto.GetActivo()){
				listaProductos.AgregarProducto(producto);
				producto.SetEliminable();
			}
		}
		CreaArchivo();
	}
	
	public void CreaArchivo(){
		File f;
		int i;
		boolean tb,te,tpo,tpr,tv;
		tb=te=tpo=tpr=tv=false;
		String fecha=this.fechaInicio.getDd()+"-"+this.fechaInicio.getMm()+"-"+this.fechaInicio.getAa();
		f = new File("Menu "+fecha+".txt");
		try{
			FileWriter w = new FileWriter(f);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);  
			wr.println("Fecha Inicio:"+fecha);
			wr.println("Fecha Final: No Definida");
			Iterator<Producto> it = listaProductos.GetTreeSetListaProd().iterator();
			while(it.hasNext()){
				Producto producto = (Producto)it.next();
				switch (producto.getClass().getCanonicalName()){
					case "Productos.Entrada": {
						if (te==false){
							wr.println(); wr.println();
							wr.println("ENTRADAS");
							wr.println();
							te=true;
						}	
						wr.print(producto.GetNom());
						
						for(i=0;i<(30-producto.GetNom().length());i++)
							wr.print(".");
						
						wr.println(" $"+producto.GetPrecioVenta());
						break;
					}
					case "Productos.Principal": {
						if (tpr==false){
							wr.println(); wr.println();
							wr.println("PLATOS PRINCIPALES");
							wr.println();
							tpr=true;
						}	
						wr.print(producto.GetNom());
						
						for(i=0;i<(30-producto.GetNom().length());i++)
							wr.print(".");
						
						wr.println(" $"+producto.GetPrecioVenta());
						break;
					}
					case "Productos.Vino": {
						if (tv==false){
							wr.println(); wr.println();
							wr.println("VINOS");
							wr.println();
							tv=true;
						}	
						wr.print(producto.GetNom());
						
						for(i=0;i<(30-producto.GetNom().length());i++)
							wr.print(".");
						
						wr.println(" $"+producto.GetPrecioVenta());
						break;
					}
					case "Productos.Bebida": {
						if (tb==false){
							wr.println(); wr.println();
							wr.println("BEBIDAS");
							wr.println();
							tb=true;
						}	
						wr.print(producto.GetNom());
						
						for(i=0;i<(30-producto.GetNom().length());i++)
							wr.print(".");
						
						wr.println(" $"+producto.GetPrecioVenta());
						break;
					}
					case "Productos.Postre": {
						if (tpo==false){
							wr.println(); wr.println();
							wr.println("POSTRES");
							wr.println();
							tpo=true;
						}	
						wr.print(producto.GetNom());
						
						for(i=0;i<(30-producto.GetNom().length());i++)
							wr.print(".");
						
						wr.println(" $"+producto.GetPrecioVenta());
						break;
					}
				}
			}
			wr.close();
			bw.close();
		}
		catch(IOException e){};
		 

	}
	
	
	public void AgregaProducto(Producto prod){
		listaProductos.AgregarProducto(prod);
		prod.SetEliminable();
	}
	
	
	public void MuestraListaProd(){
		System.out.println(fechaInicio.getDd()+"/"+fechaInicio.getMm()+"/"+fechaInicio.getAa());
		listaProductos.MostrarProductos();
	}
}
