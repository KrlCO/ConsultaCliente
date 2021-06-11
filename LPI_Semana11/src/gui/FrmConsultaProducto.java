package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entidad.Producto;
import model.ProductoModel;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmConsultaProducto extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblConsultaDeProductos;
	private JLabel lblPreciodesde;
	private JTextField txtDesde;
	private JLabel lblhasta;
	private JTextField txtHasta;
	private JButton btnFiltrar;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaProducto frame = new FrmConsultaProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmConsultaProducto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblConsultaDeProductos = new JLabel("Consulta de productos por precio");
		lblConsultaDeProductos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeProductos.setBounds(10, 11, 833, 22);
		contentPane.add(lblConsultaDeProductos);
		
		lblPreciodesde = new JLabel("Precio (Desde)");
		lblPreciodesde.setBounds(10, 85, 87, 14);
		contentPane.add(lblPreciodesde);
		
		txtDesde = new JTextField();
		txtDesde.setBounds(138, 82, 122, 20);
		contentPane.add(txtDesde);
		txtDesde.setColumns(10);
		
		lblhasta = new JLabel("(Hasta)");
		lblhasta.setBounds(420, 85, 46, 14);
		contentPane.add(lblhasta);
		
		txtHasta = new JTextField();
		txtHasta.setColumns(10);
		txtHasta.setBounds(476, 82, 122, 20);
		contentPane.add(txtHasta);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(754, 81, 89, 23);
		contentPane.add(btnFiltrar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 833, 313);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombre", "Precio", "Stock", "Serie"
			}
		));
		scrollPane.setViewportView(table);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrar(e);
		}
	}
	protected void actionPerformedBtnFiltrar(ActionEvent e) {
		String preIni = txtDesde.getText();
		String preFin = txtHasta.getText();
		
		if(preIni.matches(Validaciones.PRECIO)) {
			mensaje("El precio inicial es de formato ###.#");
			return;
		}
		
		if(preFin.matches(Validaciones.PRECIO)) {
			mensaje("El precio final es de formato ###.#");
			return;
		}
		
		double dbPreIni = Double.parseDouble(preIni);
		double dbPreFin = Double.parseDouble(preFin);
		
		if(dbPreIni > dbPreFin) {
			mensaje("El precio inicial es mayor que el precio incial");
			return;
		}
		
		
		ProductoModel model = new ProductoModel();
		List<Producto> lstProducto = model.listaPorPrecio(dbPreIni, dbPreFin);
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		for(Producto x : lstProducto) {
			Object[] fila  = {x.getIdProducto(), x.getNombre(), x.getPrecio(), x.getStock(), x.getSerie()};
			dtm.addRow(fila);
		}
	}
	
	public void mensaje(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
}
