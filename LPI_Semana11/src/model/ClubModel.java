package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidad.Auspiciador;
import entidad.Club;
import util.MySqlDBConexion;

public class ClubModel {

	
	public List<Club> consultaClub(String idAuspiciador){
		ArrayList<Club> data = new ArrayList<Club>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="SELECT d.*,g.nombre FROM Club d inner join Auspiciador g on d.idAuspiciador = g.idAuspiciador where d.idAuspiciador = ?";  
			pstm = con.prepareStatement(sql);
			pstm.setString(1, idAuspiciador);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			
			Club c = null;
			Auspiciador g = null;
			while(rs.next()){
				c = new Club();
				c.setIdClub(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setFechaCreacion(rs.getDate(3));
				c.setPais(rs.getString(4));
				
				g = new Auspiciador();
				g.setIdAuspiciador(rs.getInt(5));
				g.setNombre(rs.getString(6));
				
				c.setAuspiciador(g);
				data.add(c);
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
	
	public List<Club> listaClub(){
		ArrayList<Club> data = new ArrayList<Club>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; //Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql ="SELECT d.*,g.nombre FROM Club d inner join Auspiciador g on d.idAuspiciador = g.idAuspiciador ";  
			pstm = con.prepareStatement(sql);
			System.out.println("SQL-->" + pstm);
			rs = pstm.executeQuery();
			
			Club c = null;
			Auspiciador g = null;
			while(rs.next()){
				c = new Club();
				c.setIdClub(rs.getInt(1));
				c.setNombre(rs.getString(2));
				c.setFechaCreacion(rs.getDate(3));
				c.setPais(rs.getString(4));
				
				g = new Auspiciador();
				g.setIdAuspiciador(rs.getInt(5));
				g.setNombre(rs.getString(6));
				
				c.setAuspiciador(g);
				data.add(c);
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
