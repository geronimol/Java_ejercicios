package Productos;

import java.io.Serializable;

public final class Entrada extends Producto implements Serializable,Porcentaje{
	
	private static final long serialVersionUID = 1L;
	private double porcGan;
	private double costo;
	
	
	public void ModificarCosto(double c){
		SetCosto(c);
		PrecioVenta();
	}
	
	public void SetPorcGan(double p){
		porcGan=p;
	}
	public void SetCosto(double c){
		costo=c;
	}
	
	public double PorcentajedeGanancia(){
		return costo+costo*(porcGan/100);
	}
	
	public void PrecioVenta(){ SetPrecioVenta(PorcentajedeGanancia());}
	
	
	public Entrada(String np,double gan, double c){
		super(np);
		porcGan=gan;
		costo=c;
		PrecioVenta();
		
	}
	public double GetPorcGan(){return porcGan;}
	public double GetCosto(){return costo;}
	
	public void Mostrar(){
		super.MostrarP();
	}
	public void ModificarPorcGan(double porcGan){
		 SetPorcGan(porcGan);
	}
}
