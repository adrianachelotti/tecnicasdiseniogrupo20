package modelo.driver;

import java.util.List;

public interface DriverSensor {

	public void encender();
	
	public void apagar();
	
	public String obtenerNombre();
	
	public boolean isEncendido();
	
	public String obtenerMedicion();
	
	public void establecerMedicion(String medicion);
	
	public List<String> medicionesPosibles();
}
