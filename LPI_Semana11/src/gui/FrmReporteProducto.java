package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FrmReporteProducto extends JFrame {

	private JPanel contentPane;
	private JLabel lblConsultaDeProductos;
	private JLabel lblPreciodesde;
	private JTextField txtDesde;
	private JLabel lblhasta;
	private JTextField txtHasta;
	private JButton btnFiltrar;
	private JPanel pnlReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmReporteProducto frame = new FrmReporteProducto();
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
	public FrmReporteProducto() {
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
		btnFiltrar.setBounds(754, 81, 89, 23);
		contentPane.add(btnFiltrar);
		
		pnlReporte = new JPanel();
		pnlReporte.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Reporte", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlReporte.setBounds(10, 110, 833, 313);
		contentPane.add(pnlReporte);
	}
}
