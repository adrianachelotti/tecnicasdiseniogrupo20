package modelo.edificio;

import modelo.driver.DriverDispositivo;

public class Dispositivo {
	
	private Ubicacion ubicacion;
	
	private String descripcion;
	
	private DriverDispositivo driver;
	
	public Dispositivo(DriverDispositivo driver){
		this.driver = driver;
	}
	
	public void encender(){
		this.driver.encender();
	};
	
	public void apagar(){
		this.driver.apagar();
	};
	
	public boolean isEncendido(){
		return this.driver.isEncendido();
	}
			
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
