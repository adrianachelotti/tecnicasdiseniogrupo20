package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela el mockup de un sensor de humo.
 * @author Grupo20
 *
 */
public class SensorDeHumoMock {
	
	private enum Humo{HUMO_EXISTENTE,HUMO_NO_EXISTENTE};
	private boolean encendido;
	private String humo;
		
	/**
	 * Obtiene la medicion de humo.
	 * @return medicion de humo.
	 */
	public String getHumo() {
		return humo;
	}

	/**
	 * Establece la medicion de humo.
	 * @param humo medicion de humo a establecer.
	 */
	public void setHumo(String humo) {
		this.humo = humo;
	}

	/**
	 * Enciende el sensor de humo.
	 */
	public void encender(){
		this.encendido=true;
	}
	
	/**
	 * Apaga el sensor de humo.
	 */
	public void apagar(){
		this.encendido=false;
	}
	
	/**
	 * Determina si el sensor esta encendido.
	 * @return true si el sensor esta encendido.
	 * 		   false en caso contrario.	
	 */
	public boolean estaEncendido() {
		return this.encendido;
	}
	
	/**
	 * Obtiene las mediciones posible que realiza el sensor.
	 * @return mediciones posibles del sensor.
	 */
	public List<String> medicionesPosibles(){
		Humo[] valores = Humo.values();
		List<String> mediciones = new ArrayList<String>();
		for (Humo humo : valores) {
			mediciones.add(humo.toString());
		}
		return mediciones;
	}
}
