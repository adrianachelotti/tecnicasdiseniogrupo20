package sensores;

import source.Suceso;

public class SensorDeTemperatura extends Sensor {
	
	private int temperatura;
	
	private boolean encendido ;

	public void deshabilitar() {
		encendido = false;
	}

	public void habilitar() {
		encendido = true;
	}

	public Suceso notificarSuceso() {
		return new Suceso("temperatua_"+temperatura);
	}

	public int obtenerMedicion() {
		
		return this.temperatura;
	}

}
