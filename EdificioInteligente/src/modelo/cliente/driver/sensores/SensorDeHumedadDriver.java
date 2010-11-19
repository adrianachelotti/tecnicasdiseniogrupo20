package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.SensorDeHumedadMock;
import modelo.driver.DriverSensor;

public class SensorDeHumedadDriver implements DriverSensor {
	
	private SensorDeHumedadMock sensor;
	
	public SensorDeHumedadDriver(){
		this.sensor = new SensorDeHumedadMock();
	}
	
	public void apagar() {
		sensor.apagar();
		
	}

	public void encender() {
		sensor.encender();
		
	}

	public void establecerMedicion(String medicion) {
		sensor.setHumedad(medicion);
	}

	public boolean isEncendido() {
		return sensor.estaEncendido();
	}

	public List<String> medicionesPosibles() {
		return sensor.medicionesPosibles();
	}

	public String obtenerMedicion() {
		return sensor.getHumedad();
	}

}
