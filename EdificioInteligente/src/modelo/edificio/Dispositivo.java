package modelo.edificio;

import modelo.driver.DriverDispositivo;

public class Dispositivo {
	
	/**
	 * Ubicacion del dispositivo en el piso
	 */
	private Ubicacion ubicacion;
	
	/**
	 * Descripcion del dispositivo
	 */
	private String descripcion;
	
	/**
	 * Driver del dispositivo
	 */
	private DriverDispositivo driver;
	
	/**
	 * Constructor del dispositivo cuyo driver es pasado como parametro
	 * @param driver driver del dispositivo
	 */
	public Dispositivo(DriverDispositivo driver){
		this.driver = driver;
	}
	
	/**
	 * Enciende al dispositivo fisico a traves del driver
	 */
	public void encender(){
		this.driver.encender();
	};
	/**
	 * Apaga al dispositivo fisico a traves del driver
	 */
	public void apagar(){
		this.driver.apagar();
	};
	
	/**
	 * Obtiene si esta encendido o no el dispositivo
	 * @return true esta encendido
	 * 		  false esta apagado 		 
	 */
	public boolean isEncendido(){
		return this.driver.isEncendido();
	}
	/**
	 * Obtiene la ubicacion del dispositivo dentro del piso		
	 * @return Ubicacion Ubicacion del dispositivo
	 */
	public Ubicacion obtenerUbicacion() {
		return ubicacion;
	}
	
	/**
	 * Establece una ubicacion para el dispositvo 
	 * @param ubicacion ubicacion nueva ubicacion del dispositivo
	 */
	public void establecerUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * Obtiene la descripcion del dispositivo
	 * @return descripcion del dispositivo
	 */
	public String obtenerDescripcion() {
		return descripcion;
	}
	/**
	 * Establece la descripcion para el dispositivo
	 * @param descripcion descripcion del dispositivo
	 */
	public void establecerDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
