package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.SensorDeRuidoMock;
import modelo.driver.DriverSensor;

/**
 * Clase que modela el driver de un sensor de ruido.
 * @author Grupo20
 *
 */
public class SensorDeRuidoDriver implements DriverSensor {

	private static final String nombre = "Sensor Ruido-Driver";
	
	private SensorDeRuidoMock sensor;
	
	/**
	 * Constructor sin parametros.
	 */
	public SensorDeRuidoDriver(){
		this.sensor = new SensorDeRuidoMock();
	}
	
	/**
	 * Apaga el sensor de ruido asociado.
	 */
	public void apagar() {
		sensor.apagar();
	}

	/**
	 * Enciende el sensor de ruido asociado.
	 */
	public void encender() {
		sensor.encender();
	}

	/**
	 * Establece la medicion de ruido en el sensor.
	 */
	public void establecerMedicion(String medicion) {
		sensor.setRuido(medicion);
	}

	/**
	 * Determina si el sensor se encuentra encendido
	 */
	public boolean isEncendido() {
		return sensor.estaEncendido();
	}

	/**
	 * Obtiene las mediciones posibles que realiza el sensor.
	 */
	public List<String> medicionesPosibles() {
		return sensor.medicionesPosibles();
	}

	/**
	 * Obtiene la medicion de ruido del sensor.
	 */
	public String obtenerMedicion() {
		return sensor.getRuido();
	}
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
}
