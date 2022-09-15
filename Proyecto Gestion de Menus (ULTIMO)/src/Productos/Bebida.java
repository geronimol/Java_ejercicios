package Productos;

import java.io.Serializable;

public class Bebida extends Producto implements Serializable{

	/**
	 * @param args
	 */
	private double costo;
	
	private static final long serialVersionUID = 1L;

	
	public void PrecioVenta(){SetPrecioVenta(costo);}
	
	public void Mostrar(){
		super.MostrarP();
	}
	
	public void ModificarCosto(double c){
		SetCosto(c);
		PrecioVenta();
	}
	
	public void SetCosto(double c){
		costo=c;
	}
	
	public Bebida(String np, double c){
		super(np);
		costo=c;
		PrecioVenta();
	}
	
}
