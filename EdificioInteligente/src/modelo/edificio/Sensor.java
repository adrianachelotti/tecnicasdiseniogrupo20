package modelo.edificio;

import java.util.List;

import modelo.driver.DriverSensor;
import modelo.manejadorDeSucesos.Suceso;

public class Sensor{
	
	/**
	 * Ubicacion del Sensor en el piso
	 */
	private Ubicacion ubicacion;
	
	/**
	 * Descripcion de sensor
	 */
	private String descripcion;
	
	/**
	 * Driver del sensor
	 */
	private DriverSensor driver;
	
	/**
	 * Constructor del sensor cuyo driver es pasado como parametro
	 * Se lo inicializa en una medicion y se lo habilita
	 * @param driver driver del sensor
	 */
	public Sensor(DriverSensor driver){
		this.driver = driver;
		this.driver.establecerMedicion(driver.medicionesPosibles().get(0));
		this.driver.encender();
	}
	
	/**
	 * Obtiene la medicion actual de sensor
	 * @return  medicion del sensor
	 */
	public String obtenerMedicion(){
		return this.driver.obtenerMedicion();
	}
	/**
	 * Establece la medicion del sensor
	 * @param medicion medicion del sensor a cambiar
	 */
	public void establecerMedicion(String medicion){
		this.driver.establecerMedicion(medicion);
	}
	/**
	 * Obtiene un suceso de acuerdo con la medicion actual
	 * @return un suceso
	 */
	public Suceso notificarSuceso(){
		return new Suceso(obtenerMedicion());
	}
	/**
	 * Habilita el sensor 
	 */
	public void habilitar(){
		this.driver.encender();
	}
	/**
	 * Deshabilita el sensor
	 */
	public void deshabilitar(){
		this.driver.apagar();
	}
	/**
	 * Obtiene si el sensor esta habilitado o no
	 * @return true si esta habilitado
	 * 		false si esta deshabilitado
	 */
	public boolean estaHabilitado() {
		return this.driver.isEncendido();
	}

	/**
	 * Obtiene la descripcion del sensor
	 * @return descripcion del sensor
	 */
	public String obtenerDescripcion() {
		return descripcion;
	}
	/**
	 * Establece la descripcion para el sensor
	 * @param descripcion descripcion del sensor
	 */
	public void establecerDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Establece al sensor el driver pasado como parametro
	 * @param driver del sensor
	 */
	public void establecerDriver(DriverSensor driver) {
		this.driver = driver;
	}

	/**
	 * Obtiene la ubicacion del sensor
	 * @return la ubicacion del sensor
	 */
	public Ubicacion obtenerUbicacion() {
		return ubicacion;
	}
	
	/**
	 * Establecer la ubicacion del sensor
	 * @param ubicacion ubicacion del dispositivo
	 */
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	/**
	 * Obtiene las mediciones posibles que tiene el sensor
	 * @return mediciones posibles
	 */
	public List<String> obtenerMedicionesPosibles(){
		return this.driver.medicionesPosibles();
	}
			
}
