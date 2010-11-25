package modelo.service.dto;

/**
 * Clase que modela el objeto de transferencia de una implicacion. 
 * @author Grupo20
 *
 */
public class ImplicacionDTO {
	private String dispositivo;
	private String accion;
	private String estado;
	private int regla;
	private String[] sucesos;
	
	/**
	 * Obtiene los sucesos que forman el antecedente de la implicacion.
	 * @return sucesos que forman el antecedente de la implicacion.
	 */
	public String[] getSucesos() {
		return sucesos;
	}
	
	/**
	 * Establece los sucesos que forman el antecedente de la implicacion. 
	 * @param sucesos sucesos que se establecen como antecedente.
	 */
	public void setSucesos(String[] sucesos) {
		this.sucesos = sucesos;
	}
	
	/**
	 * Obtiene el consecuente de la implicacion.
	 * @return consecuente de la implicacion.
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Establece el consecuente de la implicacion.
	 * @param accion consecuente de la implicacion.
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	/**
	 * Obtiene el identificador de la implicacion.
	 * @return identificador de la implicacion.
	 */
	public int getRegla() {
		return regla;
	}
	
	/**
	 * Establece el identificador de la implicacion.
	 * @param regla indentificador de la implicacion.
	 */
	public void setRegla(int regla) {
		this.regla = regla;
	}
	
	/**
	 * Obtiene el estado de la regla.
	 * @return estado de la regla.
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado de la regla.
	 * @param estado estado de la regla.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el dispositivo asociado a la regla.
	 * @return dispositivo asociado a la regla.
	 */
	public String getDispositivo() {
		return dispositivo;
	}
	
	/**
	 * Establece el dispositivo asociado a la regla.
	 * @param dispositivo dispositivo asociado a la regla. 
	 */
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}
			
}
