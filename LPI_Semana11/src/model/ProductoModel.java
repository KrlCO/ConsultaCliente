package model;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entidad.Producto;
import util.MySqlDBConexion;

public class ProductoModel {

	public List<Producto> listaPorPrecio(double preIni, double preFin){

		ArrayList<Producto> lista = new ArrayList<Producto>();

		Connection conn = null;

		PreparedStatement psmt = null;

		ResultSet rs = null;

		try {

			conn = MySqlDBConexion.getConexion();

			
			String sql = "select * from producto where precio between ? and ?";

			psmt = conn.prepareStatement(sql);

			psmt.setDouble(1, preIni);

			psmt.setDouble(2, preFin);

			System.out.println("SQL ->" + sql);

			

			rs = psmt.executeQuery();

			Producto obj;

			while(rs.next()) {

				obj = new Producto();

				obj.setIdProducto(rs.getInt(1));

				obj.setNombre(rs.getString(2));

				obj.setPrecio(rs.getDouble(3));

				obj.setStock(rs.getInt(4));

				obj.setSerie(rs.getString(5));

				lista.add(obj);

			}

			

		} catch (Exception e) {

			e.printStackTrace();

		}finally {

			try {

				if (rs != null) rs.close();

				if (psmt != null) psmt.close();

				if (conn != null) conn.close();

			} catch (Exception e2) {}

		}

		return lista;

	}
	
}
