package InterfaceGraf;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ModificarProd extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pnsouth = null;
	private JPanel pneast = null;
	
	private JButton btmodificar = null;
	private JButton btatras = null;
	
	private JCheckBox cb = null;
	
	private GridBagConstraints gbc = null;
	
	Productos producto = new Productos();
	
	public ModificarProd() {
		super("Modificar Producto");
		setSize(600,400);
		setResizable(false);
		
		pnsouth = new JPanel();
		pneast = new JPanel(new GridBagLayout());
		
		btmodificar = new JButton("Modificar");
		btatras = new JButton("Atras");
		
		cb = new JCheckBox("Activado");
		
		pnsouth.add(btmodificar);
		pnsouth.add(cb);
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(290,0,0,25);
		
		pneast.add(btatras, gbc);
		
		add(pnsouth, BorderLayout.SOUTH);
		add(pneast, BorderLayout.EAST);
		
		btmodificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(cb.isSelected())
					JOptionPane.showMessageDialog(null, "Activado");
				else
					JOptionPane.showMessageDialog(null, "Desactivado");
				
			}
		});
		
		btatras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				producto.setVisible(true);
			}
		});
		
	}
}
