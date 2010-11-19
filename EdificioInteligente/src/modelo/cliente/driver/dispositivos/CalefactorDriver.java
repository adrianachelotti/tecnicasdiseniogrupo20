package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

public class CalefactorDriver implements DriverDispositivo{

	private boolean encendido;
	
	public void apagar() {
		this.encendido = false;
	}

	public void encender() {
		this.encendido = true;
	}

	public boolean isEncendido() {
		return encendido;
	}
	
}
