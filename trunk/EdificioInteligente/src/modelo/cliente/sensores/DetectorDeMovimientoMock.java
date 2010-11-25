package modelo.cliente.sensores;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que modela el mockup de un detector de movimiento.
 * @author Grupo20
 *
 */
public class DetectorDeMovimientoMock {

	private enum Movimiento{EXISTENTE_MOVIMIENTO,NO_EXISTENTE_MOVIMIENTO};
	private boolean encendido;
	private String movimiento;
		
	/**
	 * Obtiene la medicion de movimiento.
	 * @return medicion de movimiento.
	 */
	public String getMovimiento() {
		return movimiento;
	}

	/**
	 * Establece la medicion de movimiento.
	 * @param movimiento medicion a establecer.
	 */
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	/**
	 * Enciende el sensor de movimiento.
	 */
	public void encender(){
		this.encendido=true;
	}
	
	/**
	 * Apaga el sensor de movimiento.
	 */
	public void apagar(){
		this.encendido=false;
	}
	
	/**
	 * Determina si el sensor esta encendido.
	 * @return true si el sensor esta encendido.
	 * 	       false en caso contrario.
	 */
	public boolean estaEncendido() {
		return this.encendido;
	}
	
	/**
	 * Obtiene las mediciones posible que realiza el detector.
	 * @return mediciones posible del detector.
	 */
	public List<String> medicionesPosibles(){
		Movimiento[] valores = Movimiento.values();
		List<String> mediciones = new ArrayList<String>();
		for (Movimiento movimiento : valores) {
			mediciones.add(movimiento.toString());
		}
		return mediciones;
	}
	
}
