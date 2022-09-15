package Restaurante;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

import Productos.Ingrediente;

public class IngredientesTotales implements Serializable{

	private static final long serialVersionUID = 1L;
	ArrayList<Ingrediente> totalIngredientes;
	
	
	public IngredientesTotales(){
		totalIngredientes=new ArrayList<Ingrediente>();
	}	
	public Ingrediente GetIng(int i){return totalIngredientes.get(i);}
	
	public void Leer() {
		int c=0,i=0;
    	String sub="",sub2="";
    	double f=0;
	        try{
	            // Abrimos el archivo
	            FileInputStream fstream = new FileInputStream("Ingredientes.txt");
	            // Creamos el objeto de entrada
	            DataInputStream entrada = new DataInputStream(fstream);
	            // Creamos el Buffer de Lectura
	            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
	            String strLinea;
	            // Leer el archivo linea por linea
	            while ((strLinea = buffer.readLine()) != null)   {
	                // Imprimimos la línea por pantalla
	                c=0;   sub="";    i=0;
	            	
	            	while (i!=strLinea.length()){
	            		if(strLinea.charAt(i)!=',')
	            			sub=sub+strLinea.charAt(i);
	            		else{
	            			if(c==0)
	            				f=Double.valueOf(sub);
	            			else
	            				if(c==1)
	            					sub2=sub;
	            			c++;
	            			sub="";
	            		}
	            		i++;
	            	}
	            	totalIngredientes.add(new Ingrediente(f,sub,sub2));
	            	//System.out.println (f+sub2+sub);
	            }
	            // Cerramos el archivo
	            entrada.close();
	        }catch (Exception e){ //Catch de excepciones
	            System.err.println("Ocurrio un error: " + e.getMessage());
	        }
	    }
	
	public void Guardar(){
		Serializa.GuardarIngredientes(totalIngredientes);
		
	}
	public void Cargar(){
		totalIngredientes=Serializa.CargaIngredientes();
	}
}
