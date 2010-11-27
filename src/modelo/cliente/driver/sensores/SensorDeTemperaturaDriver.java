package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.SensorDeTemperaturaMock;
import modelo.driver.DriverSensor;

/**
 * Clase que modela el driver de un sensor de temperatura.
 * @author Grupo20
 *
 */
public class SensorDeTemperaturaDriver implements DriverSensor{
	
	private static final String nombre = "Sensor Temperatura-Driver";
	
	private SensorDeTemperaturaMock sensor;
	
	/**
	 * Constructor sin parametros.
	 */
	public SensorDeTemperaturaDriver(){
		this.sensor = new SensorDeTemperaturaMock(); 
	}
	
	/**
	 * Apaga el sensor de temperatura asociado.
	 */
	public void apagar() {
		this.sensor.apagar();
	}

	/**
	 * Enciende el sensor de temperatura asociado.
	 */
	public void encender() {
		this.sensor.encender();
	}

	/**
	 * Determina si el sensor de temperatura esta encendido.
	 */
	public boolean isEncendido() {
		return this.sensor.estaEncendido();
	}

	/**
	 * Obtiene la medicion de temperatura del sensor.
	 */
	public String obtenerMedicion() {
		return this.sensor.getTemperatura();
	}

	/**
	 * Establece la medicion de temperatura en el sensor.
	 */
	public void establecerMedicion(String medicion) {
		this.sensor.setTemperatura(medicion);
	}
	
	/**
	 * Obtiene las mediciones posibles que realiza el sensor.
	 */
	public List<String> medicionesPosibles(){
		return this.sensor.medicionesPosibles();
	}
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}
}
