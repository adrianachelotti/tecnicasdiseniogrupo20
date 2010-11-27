package modelo.driver;

import java.util.List;
/**
 * Interface que deben implementar los drivers de sensores
 * @author grupo 20
 *
 */
public interface DriverSensor {

	/**
	 * Enciende el sensore 
	 */
	public void encender();
	
	/**
	 * Apaga el sensor
	 */
	public void apagar();
	
	/**
	 * Obtiene el nombre del sensor
	 * @return nombre del sensor
	 */
	public String obtenerNombre();
	
	/**
	 * Obtiene si esta o no encendido el sensor
	 * @return verdadero si esta encendido, falso 
	 * si esta apagado
	 */
	public boolean isEncendido();
	
	/**
	 * Obtiene la medicion del sensor
	 * @return medicion del sensor
	 */
	public String obtenerMedicion();
	
	/**
	 * Establece la medicion del sensor	 
	 * @param medicion medicion a cambiar del sensor
	 */
	public void establecerMedicion(String medicion);
	
	/**
	 * Obtiene las mediciones posibles del sensor
	 * @return una lista de mediciones posibles
	 */
	public List<String> medicionesPosibles();
}
