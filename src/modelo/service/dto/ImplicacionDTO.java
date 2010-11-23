package modelo.service.dto;

public class ImplicacionDTO {
	private String dispositivo;
	private String accion;
	private String estado;
	private int regla;
	private String[] sucesos;
	
	public String[] getSucesos() {
		return sucesos;
	}
	public void setSucesos(String[] sucesos) {
		this.sucesos = sucesos;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	public int getRegla() {
		return regla;
	}
	public void setRegla(int regla) {
		this.regla = regla;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDispositivo() {
		return dispositivo;
	}
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
			
}
