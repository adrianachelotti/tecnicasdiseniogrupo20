package modelo.sensores;

import modelo.manejadorDeSucesos.Suceso;

public class SensorDeTemperatura extends Sensor {
	
	private int temperatura =18;
		
	public Suceso notificarSuceso() {
		return new Suceso("temperatura_"+temperatura);
	}

	public int obtenerMedicion() {
		return this.temperatura;
	}

}
