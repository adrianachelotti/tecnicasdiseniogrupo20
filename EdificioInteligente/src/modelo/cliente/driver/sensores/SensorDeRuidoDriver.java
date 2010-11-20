package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.SensorDeRuidoMock;
import modelo.driver.DriverSensor;

public class SensorDeRuidoDriver implements DriverSensor {

	private static final String nombre = "Sensor Ruido-Driver";
	
	private SensorDeRuidoMock sensor;
	
	public SensorDeRuidoDriver(){
		this.sensor = new SensorDeRuidoMock();
	}
	
	public void apagar() {
		sensor.apagar();
	}

	public void encender() {
		sensor.encender();
	}

	public void establecerMedicion(String medicion) {
		sensor.setRuido(medicion);
	}

	public boolean isEncendido() {
		return sensor.estaEncendido();
	}

	public List<String> medicionesPosibles() {
		return sensor.medicionesPosibles();
	}

	public String obtenerMedicion() {
		return sensor.getRuido();
	}
	
	public String obtenerNombre(){
		return nombre;
	}
}
