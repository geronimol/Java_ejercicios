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

public class VerProd extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JPanel pneast = null;
	
	JButton btatras = null;
	
	private GridBagConstraints gbc = null;
	
	Productos producto = new Productos();
	
	public VerProd() {
		super("Ver Produtos");
		setSize(600, 400);
		setResizable(false);
		
		pneast = new JPanel(new GridBagLayout());
		
		btatras = new JButton("Atras");
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(290,0,0,25);
		
		pneast.add(btatras, gbc);
		
		add(pneast, BorderLayout.EAST);
		
		btatras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				producto.setVisible(true);
			}
		});
	}

}
