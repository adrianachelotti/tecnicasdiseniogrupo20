package controladores.beans;

import java.util.List;
import modelo.manejadorDeSucesos.*;
import modelo.driver.*;
import modelo.edificio.*;


public class EdificioBean {
	
	/**
	 * Lista de pisos del edificio
	 */
	List<Piso> listadoDePisos;
	
	/**
	 * Catalogo de drivers de dispositivos del edificio
	 */
	List<DriverDispositivo> catalogoDriversDeDisposititvos;
	
	/**
	 * Catalogo de Drivers de sensores del edificio 
	 */
	List<DriverSensor> catalogoDriversDeSensores;

	/**
	 * Lista de dispositivo para un piso determinado
	 */
	List<Dispositivo> listadoDeDispositivos;
	
	/**
	 * Lista de reglas para un piso determinado
	 */
	List<Implicacion> listaDeReglas;
	
	/**
	 * Lista de sensores para un piso determinaod
	 */
	List<Sensor> listadoDeSensores;
	
	/**
	 * Mediciones posibles obtenidas por el driver
	 */
	List<String> medicionesPosibles;
	
	/**
	 * Lista de mediciones esperadas por la regla
	 */
	List<String> medicionesEsperadas;
	
	/**
	 * Nivel de un piso determinado
	 */
	private int nivel;
	
	/**
	 * Obtiene el catalogo de drivers de sensores del edificio
	 * @return lista de driver de sensores
	 */
	public List<DriverSensor> obtenerCatalogoDriversDeSensores() {
		return catalogoDriversDeSensores;
	}
	
	/**
	 * Establece el catalogo de driver de sensores del edificio
	 * @param catalogoDriversDeSensores listado de driver de sensores
	 */
	public void establecerCatalogoDriversDeSensores(List<DriverSensor> catalogoDriversDeSensores) {
		this.catalogoDriversDeSensores = catalogoDriversDeSensores;
	}
	
	/**
	 * Obtiene el listado de dispositivos de un piso determinado
	 * @return una lista de dispositivos
	 */
	public List<Dispositivo> obtenerListadoDeDispositivos() {
		return listadoDeDispositivos;
	}
	
	/**
	 * Establece el listado de dispositivo para un piso determinado
	 * @param listadoDeDispositivos lista de dispositivos de un piso
	 */
	public void establecerListadoDeDispositivos(List<Dispositivo> listadoDeDispositivos) {
		this.listadoDeDispositivos = listadoDeDispositivos;
	}
	
	/**
	 * Obtiene el catalgo de driver de dispositivos del edificio
	 * @return lista de driver de dispositivos
	 */
	public List<DriverDispositivo> obtenerCatalogoDriversDeDisposititvos() {
		return catalogoDriversDeDisposititvos;
	}
	
	/**
	 * Establece el catalogo de drivers de dispositivos del edificio 
	 * @param listaDriversDeDisposititvos lista de drivers de dispositivos
	 */
	public void establecerCatalogoDriversDeDisposititvos(List<DriverDispositivo> listaDriversDeDisposititvos) {
		this.catalogoDriversDeDisposititvos = listaDriversDeDisposititvos;
	}
	
	/**
	 * Obtiene la lista de pisos del edificio
	 * @return lista de pisos
	 */
	public List<Piso> obtenerListadoDePisos() {
		return listadoDePisos;
	}
	
	/**
	 * Establece una lista de pisos
	 * @param listadoDePisos lista de pisos del edificio
	 */
	public void establecerListadoDePisos(List<Piso> listadoDePisos) {
		this.listadoDePisos = listadoDePisos;
	}
	
	/** 
	 * Establece el nivel
	 * @param nivel nivel del piso
	 */
	public void establecerNivel(int nivel) {
		this.nivel = nivel;
		
	}
	
	/**
	 * Obtiene el nivel del piso
	 * @return nivel del piso
	 */
	public int obtenerNivel() {
		return nivel;
	}
	
	/**
	 * Obtiene una lista de sensores de un piso determinado
	 * @return lista de sensores
	 */
	public List<Sensor> obtenerListadoDeSensores() {
		return listadoDeSensores;
	}
	
	/**
	 * Establece una lista de sensores de un piso determinado
	 * @param listadoDeSensores lista de sensores
	 */
	public void establecerListadoDeSensores(List<Sensor> listadoDeSensores) {
		this.listadoDeSensores = listadoDeSensores;
	}
	
	/**
	 * Obtiene una lista de reglas de un piso determinado
	 * @return listado de reglas
	 */
	public List<Implicacion> obtenerListadoDeReglas() {
		return listaDeReglas;
	}
	
	/**
	 * Establece un listado de reglas
	 * @param listaDeReglas lista de reglas
	 */
	public void establecerListadoDeReglas(List<Implicacion> listaDeReglas) {
		this.listaDeReglas = listaDeReglas;
	}
	
	/**
	 * Obtiene las mediciones posibles de un sensor
	 * @return medidicones posibles
	 */
	public List<String> obtenerMedicionesPosibles() {
		return medicionesPosibles;
	}
	
	/**
	 * Establece las mediciones posibles de un sensor
	 * @param medicionesPosibles mediciones posibles
	 */
	public void establecerMedicionesPosibles(List<String> medicionesPosibles) {
		this.medicionesPosibles = medicionesPosibles;
	}
	
	/**
	 * Obtiene mediciones esperadas de una regla
	 * @return mediciones esperadas
	 */
	public List<String> obtenerMedicionesEsperadas() {
		return medicionesEsperadas;
	}
	
	/**
	 * Establece mediciones esperadas para un regla
	 * @param medicionesEsperadas mediciones esperadas
	 */
	public void establecerMedicionesEsperadas(List<String> medicionesEsperadas) {
		this.medicionesEsperadas = medicionesEsperadas;
	}

 
	
	
	

}
