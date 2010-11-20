package modelo.cliente.driver.sensores;

import java.util.List;

import modelo.cliente.sensores.SensorDeTemperaturaMock;
import modelo.driver.DriverSensor;

public class SensorDeTemperaturaDriver implements DriverSensor{
	
	private static final String nombre = "Sensor Temperatura-Driver";
	
	private SensorDeTemperaturaMock sensor;
	
	public SensorDeTemperaturaDriver(){
		this.sensor = new SensorDeTemperaturaMock(); 
	}
	
	public void apagar() {
		this.sensor.apagar();
	}

	public void encender() {
		this.sensor.apagar();
	}

	public boolean isEncendido() {
		return this.sensor.estaEncendido();
	}

	public String obtenerMedicion() {
		return this.sensor.getTemperatura();
	}

	public void establecerMedicion(String medicion) {
		this.sensor.setTemperatura(medicion);
	}
	
	public List<String> medicionesPosibles(){
		return this.sensor.medicionesPosibles();
	}
	
	public String obtenerNombre(){
		return nombre;
	}
}
