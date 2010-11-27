package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.SensorDeHumedadMock;
import modelo.driver.DriverSensor;

/**
 * Clase que modela el driver de un sensor de humedad.
 * @author Grupo20
 *
 */
public class SensorDeHumedadDriver implements DriverSensor {
	
	private static final String nombre = "Sensor Humedad-Driver";
	
	private SensorDeHumedadMock sensor;
	
	/**
	 * Constructor sin parametros.
	 */
	public SensorDeHumedadDriver(){
		this.sensor = new SensorDeHumedadMock();
	}
	
	/**
	 * Apaga el sensor de humedad asociado.
	 */
	public void apagar() {
		sensor.apagar();
	}

	/**
	 * Enciende el sensor de humedad asociado.
	 */
	public void encender() {
		sensor.encender();
	}

	/**
	 * Establece la medicion del sensor de humedad.
	 */
	public void establecerMedicion(String medicion) {
		sensor.setHumedad(medicion);
	}

	/**
	 * Determina si el sensor de humedad esta encendido.
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
	 * Obtiene la medicion de humedad del sensor.
	 */
	public String obtenerMedicion() {
		return sensor.getHumedad();
	}
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
}
