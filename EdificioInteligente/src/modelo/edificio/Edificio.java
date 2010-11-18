package modelo.edificio;

import java.util.ArrayList;
import java.util.List;


public class Edificio {

	private List<Piso> pisos;
	
	private List<Sensor> driversSensores;
	
	private List<Dispositivo> driversDispositivos;
	
	private static Edificio instancia;
	
	private Edificio(){		
		this.pisos = new ArrayList<Piso>();
		this.pisos.add(new Piso(1));
		this.pisos.add(new Piso(2));		
		this.driversDispositivos = new ArrayList<Dispositivo>();
		this.driversSensores = new ArrayList<Sensor>();
	}
	
	/**
	 * Obtiene la instancia de la clase.
	 * @return intancia unica de la clase
	 */
	public static Edificio obtenerInstancia(){
		if(instancia==null){
			instancia = new Edificio();
		}
		return instancia;
	}
		
	/**
	 * Agrega un piso al edificio.
	 * @param pisoAgregar piso a agregar al edificio.
	 */
	public void agregarPiso(Piso pisoAgregar){
		this.pisos.add(pisoAgregar);		
	}

	/**
	 * Obtiene los pisos que forman el edificio.	
	 * @return pisos que forman el edificio.
	 */
	public List<Piso> getPisos() {
		return pisos;
	}

	/**
	 * Agrega el driver de un sensor al edificio.
	 * @param sensor driver del sensor a agregar.
	 */
	public void agregarDriverSensor(Sensor sensor){
		this.driversSensores.add(sensor);
	}
	
	/**
	 * Agrega el driver de un dispositivo al edificio.
	 * @param dispositivo driver del dispositivo a agregar.
	 */
	public void agregarDriverDispositivo(Dispositivo dispositivo){
		this.driversDispositivos.add(dispositivo);
	}

	/**
	 * Obtiene los drivers de sensores con los que cuenta el edificio.
	 * @return drivers de sensores del edificio.
	 */
	public List<Sensor> getDriversSensores() {
		return driversSensores;
	}

	/**
	 * Obtiene los drivers de dsipositivos con los que cuenta el edificio.
	 * @return drivers de dispositivos del edificio.
	 */
	public List<Dispositivo> getDriversDispositivos() {
		return driversDispositivos;
	}

	
	
	
	
}
