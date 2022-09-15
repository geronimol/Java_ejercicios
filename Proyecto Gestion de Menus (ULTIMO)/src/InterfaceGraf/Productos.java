package InterfaceGraf;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Productos extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel pneast = null;
	private JPanel pncenter = null;
	
	private JButton btatras = null;
	private JButton btagregar = null;
	private JButton btmodificar = null;
	private JButton bteliminar = null;
	private JButton btver = null;
	
	private GridBagConstraints gbc = null;
	private GridBagConstraints gbc2 = null;
	
	ModificarProd modificarprod = null;
	AgregarProd agregarprod = null;
	EliminarProd eliminarprod = null;
	VerProd verprod = null;
	Inicio inicio = new Inicio();
	
	public Productos() {
		super("Productos");
		setSize(600,400);
		setResizable(false);
		
		pneast = new JPanel(new GridBagLayout());
		pncenter = new JPanel(new GridBagLayout());
		
		btatras = new JButton("Atras");
		btagregar = new JButton("Agregar");
		btmodificar = new JButton("Modificar");
		bteliminar = new JButton("Eliminar");
		btver = new JButton("Ver");
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(290,0,0,25);
		
		pneast.add(btatras, gbc);
		
		gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(15,70,15,15);

		gbc2.gridx = 1;
		gbc2.gridy = 1;
		pncenter.add(btagregar, gbc2);
		gbc2.gridx = 2;
		gbc2.gridy = 1;
		pncenter.add(btmodificar, gbc2);
		gbc2.gridx = 3;
		gbc2.gridy = 1;
		pncenter.add(bteliminar, gbc2);
		gbc2.gridx = 2;
		gbc2.gridy = 2;
		pncenter.add(btver, gbc2);
		
		add(pncenter, BorderLayout.CENTER);
		add(pneast, BorderLayout.EAST);
		
		btatras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inicio.setVisible(true);
				dispose();
			}
		});
		
		btagregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(agregarprod == null)
					agregarprod = new AgregarProd();
				agregarprod.setVisible(true);
				dispose();
			}
		});
	
		btmodificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(modificarprod == null)
					modificarprod = new ModificarProd();
				modificarprod.setVisible(true);
				dispose();
			}
		});
		
		bteliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(eliminarprod == null)
					eliminarprod = new EliminarProd();
				eliminarprod.setVisible(true);
				dispose();
			}
		});
		
		btver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(verprod == null)
					verprod = new VerProd();
				verprod.setVisible(true);
				dispose();
			}
		});
	}
}

