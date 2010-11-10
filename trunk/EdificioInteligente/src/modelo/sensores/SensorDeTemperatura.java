package modelo.sensores;

import modelo.manejadorDeSucesos.Suceso;

public class SensorDeTemperatura extends Sensor {
	
	private int temperatura =18;
	
	private boolean encendido ;

	public void deshabilitar() {
		encendido = false;
	}

	public void habilitar() {
		encendido = true;
	}

	public Suceso notificarSuceso() {
		return new Suceso("temperatura_"+temperatura);
	}

	public int obtenerMedicion() {
		
		return this.temperatura;
	}

}
