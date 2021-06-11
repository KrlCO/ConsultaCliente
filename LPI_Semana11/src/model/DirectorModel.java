package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Director;
import entidad.Grado;
import util.MySqlDBConexion;

public class DirectorModel {

	
	public List<Director> consultaDirector(String fecIni, String fecFin){
		ArrayList<Director> data = new ArrayList<Director>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = "select d.* , g.descripcion from director d inner "
						+ "join grado g on d.idgrado = g.idgrado where  "
						+ "fechaNacimiento  between ? and ? ";
			pstm = con.prepareStatement(sql);
			pstm.setDate(1, Date.valueOf(fecIni));
			pstm.setDate(2, Date.valueOf(fecFin));
			
			rs = pstm.executeQuery();
					
			Director objDirector = null;
			Grado objGrado = null;
			while(rs.next()){
				objDirector = new Director();
				objDirector.setIdDirector(rs.getInt(1));
				objDirector.setNombre(rs.getString(2));
				objDirector.setFechaNacimiento(rs.getDate(3));
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(4));
				objGrado.setDescripcion(rs.getString(5));
				
				objDirector.setGrado(objGrado);
				
				data.add(objDirector);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public List<Director> listaDirector(){
		ArrayList<Director> data = new ArrayList<Director>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			
			String sql = "select d.* , g.descripcion from director d inner "
						+ "join grado g on d.idgrado = g.idgrado ";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
					
			Director objDirector = null;
			Grado objGrado = null;
			while(rs.next()){
				objDirector = new Director();
				objDirector.setIdDirector(rs.getInt(1));
				objDirector.setNombre(rs.getString(2));
				objDirector.setFechaNacimiento(rs.getDate(3));
				
				objGrado = new Grado();
				objGrado.setIdGrado(rs.getInt(4));
				objGrado.setDescripcion(rs.getString(5));
				
				objDirector.setGrado(objGrado);
				
				data.add(objDirector);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)pstm.close();
				if (con != null)con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
