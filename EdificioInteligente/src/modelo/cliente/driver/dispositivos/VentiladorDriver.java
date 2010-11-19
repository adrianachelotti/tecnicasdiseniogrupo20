package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

public class VentiladorDriver implements DriverDispositivo{
	public boolean encendido;
	
	public void apagar() {
		this.encendido=false;
	}

	public void encender() {
		this.encendido=true;
	}

	public boolean isEncendido() {
		return this.encendido;
	}
}
