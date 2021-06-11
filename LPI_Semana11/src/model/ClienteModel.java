package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.TipoCliente;
import util.MySqlDBConexion;
/**
 * 
 * @author Carlos Cerquin Oropeza
 *
 */
public class ClienteModel {

	
	public List<Cliente> consultaCliente(String dni) {
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();

			String sql = "SELECT c.*,t.nombre FROM cliente c inner join tipo_cliente t  on t.idTpoCliente = c.idTipoCliente where c.dni=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dni);
			System.out.println("SQL-->" + pstm);

			rs = pstm.executeQuery();

			Cliente objCliente = null;
			TipoCliente objTipClient = null;
			while (rs.next()) {
				objCliente = new Cliente();
				objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setDni(rs.getString(4));
				objCliente.setFechaNacimiento(rs.getDate(5));

				objTipClient = new TipoCliente();
				objTipClient.setIdTipoCliente(rs.getInt(6));
				objTipClient.setNombre(rs.getString(7));

				objCliente.setTpClient(objTipClient);

				data.add(objCliente);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	public List<Cliente> ListaCliente() {
		ArrayList<Cliente> data = new ArrayList<Cliente>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "SELECT c.*,t.nombre FROM cliente c inner join tipo_cliente t  on t.idTpoCliente = c.idTipoCliente";
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();

			Cliente objCliente = null;
			TipoCliente objTipClient = null;
			while (rs.next()) {
				objCliente = new Cliente();
				objCliente.setIdCliente(rs.getInt(1));
				objCliente.setNombres(rs.getString(2));
				objCliente.setApellidos(rs.getString(3));
				objCliente.setDni(rs.getString(4));
				objCliente.setFechaNacimiento(rs.getDate(5));

				objTipClient = new TipoCliente();
				objTipClient.setIdTipoCliente(rs.getInt(6));
				objTipClient.setNombre(rs.getString(7));

				objCliente.setTpClient(objTipClient);

				data.add(objCliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

}
