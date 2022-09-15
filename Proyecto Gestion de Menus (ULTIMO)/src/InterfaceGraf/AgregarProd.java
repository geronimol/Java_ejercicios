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
import javax.swing.JTextField;

public class AgregarProd extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JPanel pnsouth = null;
	JPanel pnnorth = null;
	JPanel pncenter = null;
	
	JButton btaceptar = null;
	JButton btcancelar = null;
	JButton btgo = null;
	
	JLabel lbtipoprod = null;
	JLabel lbnombre = null;
	JLabel lbpventa = null;
	JLabel lbpganancia = null;
	JLabel lbcosto = null;
	JLabel lbvarietal = null;
	JLabel lbprovincia = null;
	
	JTextField tfnombre = null;
	JTextField tfpventa = null;
	JTextField tfpganancia = null;
	JTextField tfcosto = null;
	JTextField tfvarietal = null;
	JTextField tfprovincia = null;
	
	private GridBagConstraints gbc = null;
	
	JComboBox<String> cb = null;
	
	void creacb(){
		cb.addItem("Bebida");
		cb.addItem("Entrada");
		cb.addItem("Principal");
		cb.addItem("Postre");
		cb.addItem("Vinos");
	}
	
	Productos producto = new Productos();
	
	public AgregarProd() {
		super("Agregar Producto");
		setSize(600,400);
		setResizable(false);
		
		pnsouth = new JPanel();
		pnnorth = new JPanel();
		pncenter = new JPanel(new GridBagLayout());
		
		btaceptar = new JButton("Aceptar");
		btcancelar = new JButton("Cancelar");
		btgo = new JButton("Go");
		
		pnsouth.add(btaceptar);
		pnsouth.add(btcancelar);
		
		cb = new JComboBox<String>();
		creacb();
		
		lbtipoprod = new JLabel("Tipo de producto");
		
		pnnorth.add(lbtipoprod);
		pnnorth.add(cb);
		pnnorth.add(btgo);
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(15,10,15,10);
		
		lbnombre = new JLabel("Nombre");
		tfnombre = new JTextField(25);
		
		lbpventa = new JLabel("Precio de Venta");
		tfpventa = new JTextField(25);
		
		lbpganancia = new JLabel("Porcentaje de Ganancia");
		tfpganancia = new JTextField(25);
		
		lbcosto = new JLabel("Costo");
		tfcosto = new JTextField(25);
		
		lbvarietal = new JLabel("Varietal");
		tfvarietal = new JTextField(25);
		
		lbprovincia = new JLabel("Provincia");
		tfprovincia = new JTextField(25);
		
		add(pnsouth, BorderLayout.SOUTH);
		add(pnnorth, BorderLayout.NORTH);
		add(pncenter, BorderLayout.CENTER);
		
		pncenter.setVisible(false);
		
		btaceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Producto agregado");
				pncenter.setVisible(false);
			}
		});
		
		btcancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				pncenter.setVisible(false);
				producto.setVisible(true);
			}
		});
		
		btgo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pncenter.setVisible(false);
				tfnombre.setText(null); tfpventa.setText(null); tfpganancia.setText(null); tfcosto.setText(null); tfvarietal.setText(null); tfprovincia.setText(null);
				pncenter.removeAll();
				String tipo = (String)cb.getSelectedItem();
				if(tipo == "Bebida"){
					gbc.gridx = 1; gbc.gridy = 1;
					pncenter.add(lbnombre, gbc);
					gbc.gridx = 2; gbc.gridy = 1;
					pncenter.add(tfnombre, gbc);
					gbc.gridx = 1; gbc.gridy = 2;
					pncenter.add(lbpventa, gbc);
					gbc.gridx = 2; gbc.gridy = 2;
					pncenter.add(tfpventa, gbc);
				}else
					if(tipo == "Entrada"){
						gbc.gridx = 1; gbc.gridy = 1;
						pncenter.add(lbnombre, gbc);
						gbc.gridx = 2; gbc.gridy = 1;
						pncenter.add(tfnombre, gbc);
						gbc.gridx = 1; gbc.gridy = 2;
						pncenter.add(lbpganancia, gbc);
						gbc.gridx = 2; gbc.gridy = 2;
						pncenter.add(tfpganancia, gbc);
						gbc.gridx = 1; gbc.gridy = 3;
						pncenter.add(lbcosto, gbc);
						gbc.gridx = 2; gbc.gridy = 3;
						pncenter.add(tfcosto, gbc);
					}else
						if(tipo == "Principal"){
							gbc.gridx = 1; gbc.gridy = 1;
							pncenter.add(lbnombre, gbc);
							gbc.gridx = 2; gbc.gridy = 1;
							pncenter.add(tfnombre, gbc);
							gbc.gridx = 1; gbc.gridy = 2;
							pncenter.add(lbpganancia, gbc);
							gbc.gridx = 2; gbc.gridy = 2;
							pncenter.add(tfpganancia, gbc);
						}else
							if(tipo == "Postre"){
								gbc.gridx = 1; gbc.gridy = 1;
								pncenter.add(lbnombre, gbc);
								gbc.gridx = 2; gbc.gridy = 1;
								pncenter.add(tfnombre, gbc);
								gbc.gridx = 1; gbc.gridy = 2;
								pncenter.add(lbpganancia, gbc);
								gbc.gridx = 2; gbc.gridy = 2;
								pncenter.add(tfpganancia, gbc);
								gbc.gridx = 1; gbc.gridy = 3;
								pncenter.add(lbcosto, gbc);
								gbc.gridx = 2; gbc.gridy = 3;
								pncenter.add(tfcosto, gbc);
							}else{
								gbc.gridx = 1; gbc.gridy = 1;
								pncenter.add(lbnombre, gbc);
								gbc.gridx = 2; gbc.gridy = 1;
								pncenter.add(tfnombre, gbc);
								gbc.gridx = 1; gbc.gridy = 2;
								pncenter.add(lbpventa, gbc);
								gbc.gridx = 2; gbc.gridy = 2;
								pncenter.add(tfpventa, gbc);
								gbc.gridx = 1; gbc.gridy = 3;
								pncenter.add(lbvarietal, gbc);
								gbc.gridx = 2; gbc.gridy = 3;
								pncenter.add(tfvarietal, gbc);
								gbc.gridx = 1; gbc.gridy = 4;
								pncenter.add(lbprovincia, gbc);
								gbc.gridx = 2; gbc.gridy = 4;
								pncenter.add(tfprovincia, gbc);
							}
				pncenter.setVisible(true);
			}
		});
	}
}