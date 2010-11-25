package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.DetectorDeMovimientoMock;
import modelo.driver.DriverSensor;

/**
 * Clase que modela el driver de un detector de movimiento.
 * @author Grupo20
 *
 */
public class DetectorDeMovimientoDriver implements DriverSensor{

	private DetectorDeMovimientoMock detector;
	
	private static final String nombre = "Detector Movimiento-Driver";
	
	/**
	 * Constuctor si parametros.
	 */
	public DetectorDeMovimientoDriver(){
		this.detector = new DetectorDeMovimientoMock();
	}
	
	/**
	 * Apaga el detector de movimiento asociado.
	 */
	public void apagar() {
		detector.apagar();
		
	}

	/**
	 * Enciende el detector de movimiento asociado.
	 */
	public void encender() {
		detector.encender();
	}

	/**
	 * Determina si el detector de movimiento esta encendido.
	 */
	public boolean isEncendido() {
		return detector.estaEncendido();
	}
	
	/**
	 * Establece la medicion de movimiento en el detector.
	 */
	public void establecerMedicion(String medicion) {
		detector.setMovimiento(medicion);
	}

	/**
	 * Obtiene las mediciones posibles que realiza el sensor.
	 */
	public List<String> medicionesPosibles() {
		return detector.medicionesPosibles();
	}

	/**
	 * Obtiene la medicion de movimiento del detector.
	 */
	public String obtenerMedicion() {
		return detector.getMovimiento();
	}
	
	/**
	 * Obtiene el nombre del driver.
	 */
	public String obtenerNombre(){
		return nombre;
	}

}
