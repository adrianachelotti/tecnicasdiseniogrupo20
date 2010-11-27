package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.SensorDeHumoMock;
import modelo.driver.DriverSensor;

/**
 * Clase que modela el driver de un sensor de humo.
 * @author Grupo20
 *
 */
public class SensorDeHumoDriver implements DriverSensor{

	private static final String nombre = "Sensor Humo-Driver";
	
	private SensorDeHumoMock sensor;
	
	/**
	 * Constructor sin parametros.
	 */
	public SensorDeHumoDriver(){
		this.sensor = new SensorDeHumoMock();
	}
	
	/**
	 * Apaga el sensor de humo asociado.
	 */
	public void apagar() {
		sensor.apagar();
		
	}

	/**
	 * Enciende el sensor de humo asociado.
	 */
	public void encender() {
		sensor.encender();
		
	}

	/**
	 * Establece la medicion de humo en el sensor.
	 */
	public void establecerMedicion(String medicion) {
		sensor.setHumo(medicion);
	}

	/**
	 * Determina si el sensor de humo esta encendido.
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
	 * Obtiene la medicion de humo del sensor.
	 */
	public String obtenerMedicion() {
		return sensor.getHumo();
	}

	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
}
