package modelo.sensores;

import modelo.manejadorDeSucesos.*;

public class DetectorDeMovimiento extends Sensor {

	private boolean encendido ;
	
	private boolean hayMovimiento;
	
	public void deshabilitar() {
		this.encendido = false;
	}

	public void habilitar() {
		this.encendido = true;
	}

	public Suceso notificarSuceso() {
		if (hayMovimiento){
			return new Suceso("hay movimiento");
		}else{
			return new Suceso("no hay movimiento");
			
		}
			
	}

	public int obtenerMedicion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
