package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;

import entidad.Cliente;
import model.ClienteModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporte.GeneradorReporte;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmReporteClienteConsulta extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblConsultaDeCliente;
	private JLabel lblDni;
	private JTextField txtDni;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						FrmReporteClienteConsulta frame = new FrmReporteClienteConsulta();
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
	public FrmReporteClienteConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 955, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblConsultaDeCliente = new JLabel("CONSULTA DE CLIENTE POR NRO DE DNI");
		lblConsultaDeCliente.setFont(new Font("Calibri", Font.BOLD, 20));
		lblConsultaDeCliente.setBounds(323, 11, 347, 23);
		contentPane.add(lblConsultaDeCliente);
		
		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Calibri", Font.BOLD, 12));
		lblDni.setBounds(33, 83, 46, 14);
		contentPane.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(89, 79, 159, 20);
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
		btnFiltrar.setFont(new Font("Calibri", Font.BOLD, 12));
		btnFiltrar.setBounds(767, 79, 89, 23);
		contentPane.add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setLayout(new BorderLayout() );
		panelReporte.setBounds(10, 108, 919, 522);
		contentPane.add(panelReporte);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrar(arg0);
		}
	}
	protected void actionPerformedBtnFiltrar(ActionEvent arg0) {
		String dni = txtDni.getText().trim();
		
		ClienteModel model = new ClienteModel();
		List<Cliente> lstData = null;
		if(dni.equals("")) {
			lstData = model.ListaCliente();
		}else {
			lstData = model.consultaCliente(dni);
		}
	
		// 1 La data
		JRBeanCollectionDataSource dataSource 
				= new JRBeanCollectionDataSource(lstData);

		// 2 El diseño del reporte (Diseño)
		String file = "reporteCliente.jasper";
		
		// 3 Se genera el reporte
		JasperPrint jasperPrint =
			GeneradorReporte.genera(file, dataSource, null);

		// 4 Se muestra en el visor
		JRViewer jRViewer = new JRViewer(jasperPrint);
		
		// 5 Se añade el visor al panel
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
		
	}
}
