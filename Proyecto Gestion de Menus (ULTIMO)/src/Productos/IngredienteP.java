package Productos;

import java.io.Serializable;


public class IngredienteP implements Serializable{
	 
	 private static final long serialVersionUID = 1L;
	 Ingrediente ing;
	 double medida;
	 
	 
	 public void ModificarMedida(double medida){
			SetMedida(medida);
		}
	 
	 public void ModificarCosto(double costo){
			ing.SetCosto(costo);
		}
	 
	 public IngredienteP(Ingrediente i, double m){
		 ing=i;
		 medida=m;
	 }
	 public Ingrediente GetIng(){return ing;}
	 public double GetMedida(){return medida;}
	 public void SetMedida(double m){medida=m;}
}
