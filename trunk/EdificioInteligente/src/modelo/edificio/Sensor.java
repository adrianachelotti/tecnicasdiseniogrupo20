package modelo.edificio;

import modelo.driver.DriverSensor;
import modelo.manejadorDeSucesos.Suceso;

public class Sensor{
	private Ubicacion ubicacion;
	private String descripcion;
	private DriverSensor driver;
	
	public Sensor(DriverSensor driver){
		this.driver = driver;
	}
	
	public String obtenerMedicion(){
		return this.driver.obtenerMedicion();
	}
	
	public void establecerMedicion(String medicion){
		this.driver.establecerMedicion(medicion);
	}
	
	public Suceso notificarSuceso(){
		return new Suceso(obtenerMedicion());
	}
		
	public void habilitar(){
		this.driver.encender();
	}
	
	public void deshabilitar(){
		this.driver.apagar();
	}
		
	public boolean estaHabilitado() {
		return this.driver.isEncendido();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setDriver(DriverSensor driver) {
		this.driver = driver;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

			
}
