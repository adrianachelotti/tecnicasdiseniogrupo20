package modelo.cliente.driver.dispositivos;

import modelo.driver.DriverDispositivo;

public class VentiladorDriver implements DriverDispositivo{
	public boolean encendido;
	
	private static final String nombre ="Ventilador";
	
	public String obtenerNombre(){
		return nombre;
	}
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
