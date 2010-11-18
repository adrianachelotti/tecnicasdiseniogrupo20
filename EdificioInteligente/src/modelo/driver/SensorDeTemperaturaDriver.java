package modelo.driver;

import java.util.List;

import modelo.cliente.sensores.SensorDeTemperaturaMock;

public class SensorDeTemperaturaDriver implements DriverSensor{
	
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
}
