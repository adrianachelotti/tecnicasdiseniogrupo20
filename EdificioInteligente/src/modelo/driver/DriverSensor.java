package modelo.driver;

public interface DriverSensor {

	public void encender();
	
	public void apagar();
	
	public boolean isEncendido();
	
	public String obtenerMedicion();
}
