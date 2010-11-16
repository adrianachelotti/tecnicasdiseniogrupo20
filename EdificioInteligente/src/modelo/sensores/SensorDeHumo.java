package modelo.sensores;

import modelo.manejadorDeSucesos.Suceso;

public class SensorDeHumo extends Sensor {
	
	private int densidadDeHumo;
	
	public Suceso notificarSuceso() {
		return new Suceso("humo_"+this.densidadDeHumo);
	}

	public int obtenerMedicion() {
		return densidadDeHumo;
	}

}
