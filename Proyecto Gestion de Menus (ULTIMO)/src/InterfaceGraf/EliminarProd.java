package InterfaceGraf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EliminarProd extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pnnorth = null;
	
	private JButton btatras = null;
	private JButton bteliminar = null;
	
	private JComboBox<String> cbproductos = null;
	
	public void Creacbproductos(){
		cbproductos.removeAllItems();
		int n = 0;
		String str = "hola";
		while (n<5){
			cbproductos.addItem(str);	
			n++;
		}
	}
	
	Productos producto = new Productos();
	
	public EliminarProd() {
		super("Eliminar Producto");
		setSize(600,400);
		setResizable(false);
		
		pnnorth = new JPanel();
		
		btatras = new JButton("Atras");
		bteliminar = new JButton("Eliminar");
		
		cbproductos = new JComboBox<String>();
		Creacbproductos();
		
		pnnorth.add(cbproductos);
		pnnorth.add(bteliminar);
		pnnorth.add(btatras);
		
		add(pnnorth, BorderLayout.NORTH);
		
		
		btatras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				producto.setVisible(true);
			}
		});
		
		bteliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}
}








