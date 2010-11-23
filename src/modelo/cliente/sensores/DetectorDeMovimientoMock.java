package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

public class DetectorDeMovimientoMock {

	private enum Movimiento{EXISTENTE_MOVIMIENTO,NO_EXISTENTE_MOVIMIENTO};
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
		Movimiento[] valores = Movimiento.values();
		List<String> mediciones = new ArrayList<String>();
		for (Movimiento movimiento : valores) {
			mediciones.add(movimiento.toString());
		}
		return mediciones;
	}
	
}
