package modelo.driver;
/**
 * Interface que deben implementar los drivers de los dispositivos
 * @author grupo20
 *
 */
public interface DriverDispositivo {
	
	/**
	 * Metodo para encender el dispositivo
	 */
	public void encender();
	
	/**
	 * Metodo para apagar el dispositivo
	 */
	public void apagar();
	
	/**
	 * Obtiene el nombre del dispositivo
	 * @return nombre del dispositivo
	 */
	public String obtenerNombre();
	
	/**
	 * Metodo que dice si esta encendido o no el dispositivo
	 * @return verdadero si el dispositvo esta encendido, false caso contrario
	 */
	public boolean isEncendido();
}
