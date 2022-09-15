package InterfaceGraf;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BuscarMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pnnorth = null;
	private JPanel pnwest = null;
	
	private JButton btaceptar = null;
	private JButton btcancelar = null; 
	
	private JLabel lbfecha = null;
	private JLabel lb1 = null;
	private JLabel lb2 = null;
	
	private GridBagConstraints gbc = null;
	
	private JComboBox<Integer> cbdia = null;
	private JComboBox<Integer> cbmes = null;
	private JComboBox<Integer> cbaño = null;
	
	public void creaCbdia(){
		cbdia.removeAllItems();
		for(int i=1; i<=31; i++)
			cbdia.addItem(i);
	}
	public void creaCbmes(){
		cbmes.removeAllItems();
		for(int i=1; i<=12; i++)
			cbmes.addItem(i);
	}
	public void creaCbaño(){
		cbaño.removeAllItems();
		for(int i=2014; i<=2036; i++)
			cbaño.addItem(i);
	}
	
	Menus menu = new Menus();
	
	public BuscarMenu() {
		super("Buscar Menus");
		setSize(600,400);
		setResizable(false);
		
		pnnorth = new JPanel(new GridBagLayout());
		pnwest = new JPanel(new GridBagLayout());
		
		btaceptar = new JButton("Aceptar");
		btcancelar = new JButton("Cancelar");
		
		lbfecha = new JLabel("Fecha ");
		lb1 = new JLabel(" / ");
		lb2 = new JLabel(" / ");
		
		cbdia = new JComboBox<Integer>();
		creaCbdia();
		cbmes = new JComboBox<Integer>();
		creaCbmes();
		cbaño = new JComboBox<Integer>();
		creaCbaño();
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(15,15,15,0);
		
		pnnorth.add(lbfecha);
		pnnorth.add(cbdia);
		pnnorth.add(lb1);
		pnnorth.add(cbmes);
		pnnorth.add(lb2);
		pnnorth.add(cbaño);
		pnnorth.add(btaceptar,gbc);
		pnnorth.add(btcancelar,gbc);
		
		add(pnnorth, BorderLayout.NORTH);
		add(pnwest, BorderLayout.WEST);
		pnwest.setVisible(false);
		
		btcancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				pnwest.setVisible(false);
				menu.setVisible(true);
			}
		});
		btaceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnwest.setVisible(true);
				
			}
		});
	}
}
