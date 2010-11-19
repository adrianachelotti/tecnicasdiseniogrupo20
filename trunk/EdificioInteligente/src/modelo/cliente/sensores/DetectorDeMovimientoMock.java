package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

import modelo.cliente.sensores.SensorDeHumoMock.Humo;

public class DetectorDeMovimientoMock {

	enum Movimiento{EXISTENTE_MOVIMIENTO,NO_EXISTENTE_MOVIMIENTO};
	private boolean encendido;
	private String movimiento;
		
	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public void encender(){
		this.encendido=true;
	}
	
	public void apagar(){
		this.encendido=false;
	}
	
	public boolean estaEncendido() {
		return this.encendido;
	}
	
	public List<String> medicionesPosibles(){
		Humo[] valores = Humo.values();
		List<String> mediciones = new ArrayList<String>();
		for (Humo humo : valores) {
			mediciones.add(humo.toString());
		}
		return mediciones;
	}
	
}
