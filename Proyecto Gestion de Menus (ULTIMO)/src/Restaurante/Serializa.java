package Restaurante;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

import Productos.Ingrediente;
import Productos.Producto;

public class Serializa {
	public static void GuardarProductos(TreeSet<Producto> args) {
		try {
			FileOutputStream fout = new FileOutputStream("Productos.ser");
			ObjectOutputStream outStream = new ObjectOutputStream(fout);
			outStream.writeObject(args);
			outStream.flush();
			outStream.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	public static void GuardarIngredientes(ArrayList<Ingrediente> args) {
		try {
			FileOutputStream fout = new FileOutputStream("Ingredientes.ser");
			ObjectOutputStream outStream = new ObjectOutputStream(fout);
			outStream.writeObject(args);
			outStream.flush();
			outStream.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	public static void GuardarMenus(LinkedList<Menu> args) {
		try {
			FileOutputStream fout = new FileOutputStream("Menus.ser");
			ObjectOutputStream outStream = new ObjectOutputStream(fout);
			outStream.writeObject(args);
			outStream.flush();
			outStream.close();
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	public static TreeSet<Producto> CargaProductos() {
		try {
			FileInputStream fin = new FileInputStream("Productos.ser");
			ObjectInputStream inStream = new ObjectInputStream(fin);
			@SuppressWarnings("unchecked")
			TreeSet<Producto> Obj = (TreeSet<Producto>) inStream.readObject();
			inStream.close();
			return Obj;
		}
		catch (Exception e) {			
			System.out.println("Error: " + e.getMessage());
			return null;
		}

	}
	public static ArrayList<Ingrediente> CargaIngredientes() {
		try {
			FileInputStream fin = new FileInputStream("Ingredientes.ser");
			ObjectInputStream inStream = new ObjectInputStream(fin);
			@SuppressWarnings("unchecked")
			ArrayList<Ingrediente> Obj = (ArrayList<Ingrediente>) inStream.readObject();
			inStream.close();
			return Obj;
		}
		catch (Exception e) {			
			System.out.println("Error: " + e.getMessage());
			return null;
		}

	}
	public static LinkedList<Menu> CargaMenus() {
		try {
			FileInputStream fin = new FileInputStream("Menus.ser");
			ObjectInputStream inStream = new ObjectInputStream(fin);
			@SuppressWarnings("unchecked")
			LinkedList<Menu> Obj = (LinkedList<Menu>) inStream.readObject();
			inStream.close();
			return Obj;
		}
		catch (Exception e) {			
			System.out.println("Error: " + e.getMessage());
			return null;
		}

	}
	

}
