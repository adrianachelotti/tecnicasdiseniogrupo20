package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.DetectorDeMovimientoMock;
import modelo.driver.DriverSensor;

public class DetectorDeMovimientoDriver implements DriverSensor{

	private DetectorDeMovimientoMock detector;
	
	public DetectorDeMovimientoDriver(){
		this.detector = new DetectorDeMovimientoMock();
	}
	
	public void apagar() {
		detector.apagar();
		
	}

	public void encender() {
		detector.encender();
	}

	public boolean isEncendido() {
		return detector.estaEncendido();
	}
	
	public void establecerMedicion(String medicion) {
		detector.setMovimiento(medicion);
	}

	public List<String> medicionesPosibles() {
		return detector.medicionesPosibles();
	}

	public String obtenerMedicion() {
		return detector.getMovimiento();
	}

}
