package entidad;

import java.sql.Date;

public class Club {

	private int idClub;
	private String nombre;
	private Date  fechaCreacion;
	private String pais;
	private Auspiciador auspiciador;
	private String auspiciadorNombre;
	
	public int getIdClub() {
		return idClub;
	}
	public void setIdClub(int idClub) {
		this.idClub = idClub;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Auspiciador getAuspiciador() {
		return auspiciador;
	}
	public void setAuspiciador(Auspiciador auspiciador) {
		this.auspiciador = auspiciador;
	}
	public String getAuspiciadorNombre() {
		auspiciadorNombre =auspiciador.getNombre();
		return auspiciadorNombre;
	}
	public void setAuspiciadorNombre(String auspiciadorNombre) {
		this.auspiciadorNombre = auspiciadorNombre;
	}
	
	
	
}
