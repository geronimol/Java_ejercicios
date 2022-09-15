package InterfaceGraf;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inicio extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pncenter = null;
	private JPanel pnnorth = null;
	private JPanel pneast = null; 
	
	private JButton btmenu = null;
	private JButton btprod = null;
	private JButton btingred = null;
	private JButton btsalir = null;
	
	JLabel label = null;
	
	private GridBagConstraints gbc = null;
	private GridBagConstraints gbc2 = null;
	
	Menus menu = null;
	Productos producto = null;
	VerIngredientes ingrediente = null;
	
	public Inicio() {
		super("Restaurante");
		setSize(600,400);
		setResizable(false);
		
		pncenter = new JPanel(new GridBagLayout());
		pnnorth = new JPanel();
		pneast = new JPanel(new GridBagLayout());
		
		label = new JLabel("Bienvenido Usuario");
		
		pnnorth.add(label);
		
		btmenu = new JButton("Menus");	
		btprod = new JButton("Productos");
		btingred = new JButton("Ver Ingredientes");
		btsalir = new JButton("Salir");
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(15,100,15,15);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		pncenter.add(btmenu, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		pncenter.add(btprod, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		pncenter.add(btingred, gbc);
		
		gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(270,0,0,25);
		
		pneast.add(btsalir, gbc2);
		
		add(pncenter, BorderLayout.CENTER);
		add(pnnorth, BorderLayout.NORTH);
		add(pneast, BorderLayout.EAST);
		
		btmenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menu == null)
					menu = new Menus();
				menu.setVisible(true);
				dispose();
			}
		});
		
		btprod.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(producto == null)
					producto = new Productos();
				producto.setVisible(true);
				dispose();
			}
		});
		
		btingred.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ingrediente == null)
					ingrediente = new VerIngredientes();
				ingrediente.setVisible(true);
				dispose();
			}
		});
		btsalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
	}

}
