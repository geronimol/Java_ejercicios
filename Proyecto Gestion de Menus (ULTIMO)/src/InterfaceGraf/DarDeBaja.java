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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DarDeBaja extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pnnorth = null;
	private JPanel pncenter = null;
	
	private JButton btaceptar = null;
	private JButton btcancelar = null;
	
	private GridBagConstraints gbc = null;
	
	private JLabel lbfecha = null;
	private JLabel lb1 = null;
	private JLabel lb2 = null;
	
	private JComboBox<Integer> cbdia = null;
	private JComboBox<Integer> cbmes = null;
	private JComboBox<Integer> cba�o = null;
	
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
	public void creaCba�o(){
		cba�o.removeAllItems();
		for(int i=2014; i<=2036; i++)
			cba�o.addItem(i);
	}
	
	Menus menu = new Menus();
		
	public DarDeBaja() {
		super("Dar De Baja");
		setSize(600,400);
		setResizable(false);
		
		pnnorth = new JPanel(new GridBagLayout());
		pncenter = new JPanel(new GridBagLayout());
		
		btaceptar = new JButton("Aceptar");
		btcancelar = new JButton("Cancelar");
				
		lbfecha = new JLabel("Fecha ");
		lb1 = new JLabel(" / ");
		lb2 = new JLabel(" / ");
		
		cbdia = new JComboBox<Integer>();
		creaCbdia();
		cbmes = new JComboBox<Integer>();
		creaCbmes();
		cba�o = new JComboBox<Integer>();
		creaCba�o();
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(15,15,15,0);
		
		pnnorth.add(lbfecha);
		pnnorth.add(cbdia);
		pnnorth.add(lb1);
		pnnorth.add(cbmes);
		pnnorth.add(lb2);
		pnnorth.add(cba�o);
		pnnorth.add(btaceptar,gbc);
		pnnorth.add(btcancelar,gbc);
		
		add(pnnorth, BorderLayout.NORTH);
		add(pncenter, BorderLayout.CENTER);
		pncenter.setVisible(false);
		
		btcancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				menu.setVisible(true);
			}
		});
		
		btaceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Fecha de baja de menu");
				dispose();
				menu.setVisible(true);
			}
		});
	}
}
