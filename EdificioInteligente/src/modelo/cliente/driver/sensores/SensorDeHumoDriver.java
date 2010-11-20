package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.SensorDeHumoMock;
import modelo.driver.DriverSensor;

public class SensorDeHumoDriver implements DriverSensor{

	private static final String nombre = "Sensor Humo-Driver";
	
	private SensorDeHumoMock sensor;
	
	public SensorDeHumoDriver(){
		this.sensor = new SensorDeHumoMock();
	}
	
	public void apagar() {
		sensor.apagar();
		
	}

	public void encender() {
		sensor.encender();
		
	}

	public void establecerMedicion(String medicion) {
		sensor.setHumo(medicion);
	}

	public boolean isEncendido() {
		return sensor.estaEncendido();
	}

	public List<String> medicionesPosibles() {
		return sensor.medicionesPosibles();
	}

	public String obtenerMedicion() {
		return sensor.getHumo();
	}

	public String obtenerNombre(){
		return nombre;
	}
}
