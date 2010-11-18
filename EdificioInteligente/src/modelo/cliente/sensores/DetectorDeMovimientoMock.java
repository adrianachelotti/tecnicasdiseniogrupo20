package modelo.cliente.sensores;

import modelo.manejadorDeSucesos.*;

public class DetectorDeMovimientoMock {

	private boolean hayMovimiento;
	
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
