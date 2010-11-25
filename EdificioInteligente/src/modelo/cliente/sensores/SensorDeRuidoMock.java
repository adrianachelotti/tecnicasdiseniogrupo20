package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela el mockup de un sensor de ruido.
 * @author Grupo20
 *
 */
public class SensorDeRuidoMock {
	private enum Ruido{RUIDO_BAJO,RUIDO_MEDIO,RUIDO_ALTO};
	private boolean encendido;
	private String ruido;
		
	/**
	 * Obtiene la medicion de ruido.
	 * @return medicion de ruido.
	 */
	public String getRuido() {
		return ruido;
	}

	/**
	 * Establece la medicion de ruido.
	 * @param ruido medicion de ruido a establecer.
	 */
	public void setRuido(String ruido) {
		this.ruido = ruido;
	}

	/**
	 * Enciende el sensor de ruido.
	 */
	public void encender(){
		this.encendido=true;
	}
	
	/**
	 * Apaga el sensor de ruido.
	 */
	public void apagar(){
		this.encendido=false;
	}
	
	/**
	 * Determina si el sensor de ruido esta encendido.
	 * @return true si el sensor esta encendido.
	 *         false en caso contrario.  
	 */   
	public boolean estaEncendido() {
		return this.encendido;
	}
	
	/**
	 * Obtiene las mediciones posible que realiza el sensor.
	 * @return mediciones posibles del sensor.
	 */
	public List<String> medicionesPosibles(){
		Ruido[] valores = Ruido.values();
		List<String> mediciones = new ArrayList<String>();
		for (Ruido ruido : valores) {
			mediciones.add(ruido.toString());
		}
		return mediciones;
	}
}
