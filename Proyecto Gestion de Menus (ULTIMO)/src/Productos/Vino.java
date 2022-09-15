package Productos;

import java.io.Serializable;

public final class Vino extends Bebida implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String varietal; 
	private Provincia prov;
	
	
	
	public Provincia GetProvincia(){return prov;}
	public String GetVarietal(){return varietal;}
	
	public void Mostrar(){
		super.Mostrar();
		System.out.println(prov+" "+varietal);
	}
	
	public Vino(String np,  double pv,Provincia p, String v){
		super(np,pv);
		varietal=v;
		prov=p;		
	}
	

}
