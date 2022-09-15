package Productos;
import java.io.Serializable;



public abstract class Producto implements Comparable<Object>, Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreProducto;
	private boolean activo;
	private boolean eliminable;
	private double precioVenta; 
	
	
	
	public boolean GetEliminable(){return eliminable;}
	public void SetEliminable(){eliminable=false;}
	public Producto(String np){nombreProducto=np; activo=false; eliminable=true;}
	public String GetNom(){return nombreProducto;}
	public boolean GetActivo(){return activo;}
	public double GetPrecioVenta(){return precioVenta;}
	public void SetPrecioVenta(double pv){precioVenta=pv;}
	public void Activar(){activo=true;}
	public void Desactivar(){activo=false;}
	
	public int compareTo( Object objeto ) {

		Producto producto = (Producto)objeto;
		String nombreObjeto = producto.nombreProducto.toLowerCase();
		String nombreThis = this.nombreProducto.toLowerCase();

		int compNombre=nombreThis.compareTo( nombreObjeto);
		int compClase=this.getClass().getName().compareTo(producto.getClass().getName());
		return compClase!=0? compClase :compNombre;
		
	}
	public boolean equals(Object o) { 
		 if (!(o instanceof Producto)) return false; 
		 Producto producto = (Producto)o; 
		 return this.nombreProducto.equals(producto.nombreProducto) && this.getClass().getName().equals(producto.getClass().getName()); 
	}	
	
	
	public void MostrarP(){System.out.println(nombreProducto+" "+activo+" "+precioVenta);}
	public void AgregaIngrediente(Ingrediente ing,double m){}
	public void EliminaIngrediente(int i){}
	abstract public void ModificarCosto(double costo);
	abstract public void PrecioVenta();
	abstract public void Mostrar();
	public void ModificarPorcGan(double porcGan){};
}
