package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela el mockup de un sensor de humedad.
 * @author Grupo20
 *
 */
public class SensorDeHumedadMock {
	
	private enum Humedad{HUMEDAD_BAJA,HUMEDAD_MEDIA,HUMEDAD_ALTA};
	private boolean encendido;
	private String humedad;
		
	/**
	 * Obtiene la medicion de humedad.
	 * @return medicion de humedad.
	 */
	public String getHumedad() {
		return humedad;
	}

	/**
	 * Establece la medicion de humedad.
	 * @param humedad medicion de humedad a establecer.
	 */
	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}

	/**
	 * Enciende el sensor de humedad.
	 */
	public void encender(){
		this.encendido=true;
	}
	
	/**
	 * Apaga el sensor de humedad.
	 */
	public void apagar(){
		this.encendido=false;
	}
	
	/**
	 * Determina si el sensor esta encendido.
	 * @return
	 */
	public boolean estaEncendido() {
		return this.encendido;
	}
	
	/**
	 * Obtiene las mediciones posible que realiza el sensor.
	 * @return mediciones posibles del sensor.
	 */
	public List<String> medicionesPosibles(){
		Humedad[] valores = Humedad.values();
		List<String> mediciones = new ArrayList<String>();
		for (Humedad humedad : valores) {
			mediciones.add(humedad.toString());
		}
		return mediciones;
	}
}
