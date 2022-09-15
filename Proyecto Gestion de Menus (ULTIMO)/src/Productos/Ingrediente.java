package Productos;

import java.io.Serializable;

public final class Ingrediente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private double costo;
	private String unidadMedida;
	private String nombre;
	
	
	public Ingrediente(double c, String um, String d){
		costo=c;
		unidadMedida=um;
		nombre=d;
	}
	
	public void ModificarCosto(double costo){
		SetCosto(costo);
	}
	
	public double GetCosto(){return costo;}
	public String GetMedida(){return unidadMedida;}
	public String GetDescripcion(){return nombre;}
	
	public void SetCosto(double c){costo=c;}
}
