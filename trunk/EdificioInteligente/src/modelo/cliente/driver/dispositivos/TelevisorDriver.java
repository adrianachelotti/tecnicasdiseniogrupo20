package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

public class TelevisorDriver implements DriverDispositivo {

	public boolean encendida;
	
	private static final String nombre ="Televisor-Driver";
	
	public String obtenerNombre(){
		return nombre;
	}
	public void apagar() {
		this.encendida=false;
	}

	public void encender() {
		this.encendida=true;
	}

	public boolean isEncendido() {
		return this.encendida;
	}
}
