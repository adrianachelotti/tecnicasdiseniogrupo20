package modelo.edificio;

import java.util.ArrayList;
import java.util.List;

import modelo.driver.DriverDispositivo;
import modelo.driver.DriverSensor;


public class Edificio {

	private List<Piso> pisos;
	
	private List<DriverSensor> catalogoDriversDeSensores;
	
	private List<DriverDispositivo> catalogoDriversDeDispositivos;
	
	private static Edificio instancia;
	
	private Edificio(){		
		this.pisos = new ArrayList<Piso>();
		this.catalogoDriversDeDispositivos = new ArrayList<DriverDispositivo>();
		this.catalogoDriversDeSensores = new ArrayList<DriverSensor>();
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
	public void agregarDriverSensor(DriverSensor sensor){
		this.catalogoDriversDeSensores.add(sensor);
	}
	
	/**
	 * Agrega el driver de un dispositivo al edificio.
	 * @param dispositivo driver del dispositivo a agregar.
	 */
	public void agregarDriverDispositivo(DriverDispositivo dispositivo){
		this.catalogoDriversDeDispositivos.add(dispositivo);
	}

	/**
	 * Obtiene el catalgo de los sensores con los que cuenta el edificio.
	 * @return catalogo de sensores del edificio.
	 */
	public List<DriverSensor> getCatalogoDriversDeSensores() {
		return catalogoDriversDeSensores;
	}

	/**
	 * Obtiene el catalog de los dispositivos con los que cuenta el edificio.
	 * @return catalog de dispositivos del edificio.
	 */
	public List<DriverDispositivo> getCataloDriversDeDispositivos() {
		return catalogoDriversDeDispositivos;
	}
	
	
}
