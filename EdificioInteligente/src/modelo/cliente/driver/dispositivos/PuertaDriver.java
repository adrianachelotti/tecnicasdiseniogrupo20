package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

public class PuertaDriver implements DriverDispositivo{

	private boolean encendida;
	
	private static final String nombre ="Puerta-Driver";
	
	public String obtenerNombre(){
		return nombre;
	}
	public void apagar() {
		this.encendida = false;
	}

	public void encender() {
		this.encendida = true;
	}

	public boolean isEncendido() {
		return encendida;
	}

}
