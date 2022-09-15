package Productos;
import java.io.Serializable;
import java.util.*;


public class Principal extends Producto implements Serializable,Porcentaje{

	private static final long serialVersionUID = 1L;
	private double porcGan;
	private ArrayList<IngredienteP> listaingr=new ArrayList<IngredienteP>();
	
	
	
	
	
	public double CostoIngr(){
		double costo=0;
		for( int i=0; i<listaingr.size(); i++){
			IngredienteP in=listaingr.get( i );  
			costo+=in.ing.GetCosto()*in.GetMedida();
			}
		return costo;
		}
	
	public void Mostrar(){
		super.MostrarP();
		System.out.println(CostoIngr()+" "+porcGan+" "+GetPrecioVenta());
		MostrarIng();
	}
	
	public double PorcentajedeGanancia(){
		double costo=CostoIngr();
		return costo+costo*(porcGan/100);
	}

	public void PrecioVenta(){ SetPrecioVenta(PorcentajedeGanancia());}
	
	public Principal(String np, double pg){
		super(np);
		porcGan=pg;
	}

	public void ModificarCosto(double c){}   //NO VA... CREO
	
	
	public void AgregaIngrediente(Ingrediente ing,double m){
		
		
		listaingr.add(new IngredienteP(ing,m));
		PrecioVenta();
		
	}
	
	public void EliminaIngrediente(int i){
		
		listaingr.remove(i);
		
		
	}
	
	public void MostrarIng(){
		
		for( int i = 0 ; i < listaingr.size() ; i++ ){
			IngredienteP in=listaingr.get( i );  
			System.out.println(in.ing.GetCosto()+in.ing.GetDescripcion()+in.GetMedida());
			}
		
	}
	
	public double GetPorcGan(){return porcGan;}
	public double GetCosto(){return CostoIngr();}
	
	
	
	}
	

