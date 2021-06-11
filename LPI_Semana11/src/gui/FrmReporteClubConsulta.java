package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import componentes.JComboBoxBD;
import entidad.Club;
import model.ClubModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import reporte.GeneradorReporte;

public class FrmReporteClubConsulta extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBoxBD comboBox;
	// Para llamar al archivo: database_sql.properties
	private ResourceBundle rb = ResourceBundle.getBundle("database_sql");
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
					FrmReporteClubConsulta frame = new FrmReporteClubConsulta();
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
	public FrmReporteClubConsulta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConsultaDeClubs = new JLabel("Consulta de  Clubs por auspiciador");
		lblConsultaDeClubs.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblConsultaDeClubs.setForeground(Color.BLACK);
		lblConsultaDeClubs.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeClubs.setBounds(32, 11, 906, 40);
		contentPane.add(lblConsultaDeClubs);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Consultas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(32, 46, 896, 70);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblAuspiciador = new JLabel("Auspiciador");
		lblAuspiciador.setBounds(288, 31, 86, 14);
		panel.add(lblAuspiciador);

		comboBox = new JComboBoxBD(rb.getString("SQL_AUSPICIADOR"));
		comboBox.addItemListener(this);
		comboBox.setBounds(396, 28, 249, 20);
		panel.add(comboBox);

		panelReporte = new JPanel();
		panelReporte.setBounds(32, 127, 896, 500);
		contentPane.add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));

	}

	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource() == comboBox) {
			do_comboBox_itemStateChanged(arg0);
		}
	}

	// La data de club sera global
	List<Club> data = new ArrayList<Club>();

	protected void do_comboBox_itemStateChanged(ItemEvent arg0) {
		int index = comboBox.getSelectedIndex();
		if (index == 0) {
			data.clear();
		} else {
			String item = comboBox.getSelectedItem().toString();
			String s[] = item.split(":");
			String id = s[0];
			ClubModel model = new ClubModel();
			if (id.equals("-1")) {
				data = model.listaClub();
			} else {
				data = model.consultaClub(id);
			}
		}

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
