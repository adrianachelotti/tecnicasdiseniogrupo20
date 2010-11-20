package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

public class LucesDriver implements DriverDispositivo{

	
	private boolean encendida;
	
	private static final String nombre ="Luces-Driver";
	
	public String obtenerNombre(){
		return nombre;
	}
	
	public LucesDriver(){
		this.encendida = false;
	}
	
	public void apagar() {
		this.encendida = false;
	}

	public void encender() {
		this.encendida = true;
	}

	public boolean isEncendido() {
		return this.encendida;
	}

}
