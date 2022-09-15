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

public class Menus extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pneast = null;
	private JPanel pncenter = null;
	
	private JButton btatras = null;
	private JButton btcrear = null;
	private JButton btbuscar = null;
	private JButton btbaja = null;
	
	private GridBagConstraints gbc = null;
	private GridBagConstraints gbc2 = null;
	
	CrearMenu crearmenu = null;
	BuscarMenu buscarmenu = null;
	DarDeBaja dardebaja = null;
	Inicio inicio = new Inicio();

	public Menus() {
		super("Menus");
		setSize(600,400);
		setResizable(false);
		
		pneast = new JPanel(new GridBagLayout());
		pncenter = new JPanel(new GridBagLayout());
		
		btatras = new JButton("Atras");
		btcrear = new JButton("Crear");
		btbuscar = new JButton("Buscar");
		btbaja = new JButton("Dar de Baja");
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(290,0,0,25);
		
		pneast.add(btatras, gbc);
		
		gbc2 = new GridBagConstraints();
		gbc2.insets = new Insets(30,90,0,0);
		
		gbc2.gridx = 1;
		gbc2.gridy = 1;
		pncenter.add(btcrear, gbc2);
		gbc2.gridx = 1;
		gbc2.gridy = 2;
		pncenter.add(btbuscar, gbc2);
		gbc2.gridx = 1;
		gbc2.gridy = 3;
		pncenter.add(btbaja, gbc2);
		
		add(pneast, BorderLayout.EAST);
		add(pncenter, BorderLayout.CENTER);
		
		btatras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				inicio.setVisible(true);
			}
		});
		
		btcrear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(crearmenu == null)
					crearmenu = new CrearMenu();
				crearmenu.setVisible(true);
				dispose();
			}
		});

		btbuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(buscarmenu == null)
					buscarmenu = new BuscarMenu();
				buscarmenu.setVisible(true);
				dispose();
			}
		});

		btbaja.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dardebaja == null)
					dardebaja = new DarDeBaja();
				dardebaja.setVisible(true);
				dispose();
			}
		});

	}
}
