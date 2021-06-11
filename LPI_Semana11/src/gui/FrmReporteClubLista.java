package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import entidad.Club;
import model.ClubModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporte.GeneradorReporte;

public class FrmReporteClubLista extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// Para llamar al archivo: database_sql.properties
	private JButton btnNewButton;
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
					FrmReporteClubLista frame = new FrmReporteClubLista();
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
	public FrmReporteClubLista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 972, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultaDeClubs = new JLabel("Lista de Clubes");
		lblConsultaDeClubs.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblConsultaDeClubs.setForeground(Color.BLACK);
		lblConsultaDeClubs.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeClubs.setBounds(22, 21, 924, 40);
		contentPane.add(lblConsultaDeClubs);

		btnNewButton = new JButton("Filtrar");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(22, 72, 924, 23);
		contentPane.add(btnNewButton);

		panelReporte = new JPanel();
		panelReporte.setBounds(22, 112, 924, 550);
		contentPane.add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnNewButton) {
			actionPerformedBtnNewButton(arg0);
		}
	}

	protected void actionPerformedBtnNewButton(ActionEvent arg0) {
		ClubModel model = new ClubModel();
		List<Club> data = model.listaClub();

		// 1 La data
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

		// 2 El diseño del reporte
		String file = "reporteClub.jasper";

		// 3 Se genera el reporte
		JasperPrint jasperPrint = GeneradorReporte.genera(file, dataSource, null);

		// 4 Se muestra en el visor
		JRViewer jRViewer = new JRViewer(jasperPrint);

		// 5 Se añade el visor al panel
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();
	}
}
