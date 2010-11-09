package sensores;

import source.Suceso;

public class SensorDeHumo extends Sensor {
	
	private int densidadDeHumo;
	private boolean encendido;

	public void deshabilitar() {
		encendido = false;
	}

	public void habilitar() {
		encendido = true;
	}

	public Suceso notificarSuceso() {
		return new Suceso("humo_"+this.densidadDeHumo);
	}

	public int obtenerMedicion() {
		return densidadDeHumo;
	}

}
