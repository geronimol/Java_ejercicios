package Productos;

import java.io.Serializable;

public final class Postre extends Producto implements Serializable{

	private static final long serialVersionUID = 1L;
	private double costo;
	static private double porcGan;
	
	public double PorcentajedeGanancia(){
		return costo+costo*(porcGan/100);
	}
	
	public void PrecioVenta(){ SetPrecioVenta(PorcentajedeGanancia());}


	public static void setPorcGan(double porcGan) {
		Postre.porcGan = porcGan;
	}


	public void ModificarCosto(double c){
		SetCosto(c);
		PrecioVenta();
	}
	
	public Postre(String np, double c){
		super(np);
		costo=c;
		PrecioVenta();
		
	}
	
	public void SetCosto(double c){
		costo=c;
	}
	public void Mostrar(){
		System.out.println(GetNom()+" "+costo);
	}
	public void ModificarPorcGan(double porcGan){
		setPorcGan(porcGan);
	}
}
