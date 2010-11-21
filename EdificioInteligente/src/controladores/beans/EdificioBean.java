package controladores.beans;

import java.util.List;
import modelo.manejadorDeSucesos.*;
import modelo.driver.*;
import modelo.edificio.*;


public class EdificioBean {
	
	List<Piso> listadoDePisos;
	
	List<DriverDispositivo> catalogoDriversDeDisposititvos;
	
	List<DriverSensor> catalogoDriversDeSensores;

	List<Dispositivo> listadoDeDispositivos;
	
	List<Implicacion> listaDeReglas;

	List<Sensor> listadoDeSensores;
	
	private int nivel;
	
	public List<DriverSensor> obtenerCatalogoDriversDeSensores() {
		return catalogoDriversDeSensores;
	}

	public void establecerCatalogoDriversDeSensores(List<DriverSensor> catalogoDriversDeSensores) {
		this.catalogoDriversDeSensores = catalogoDriversDeSensores;
	}

	public List<Dispositivo> obtenerListadoDeDispositivos() {
		return listadoDeDispositivos;
	}

	public void establecerListadoDeDispositivos(List<Dispositivo> listadoDeDispositivos) {
		this.listadoDeDispositivos = listadoDeDispositivos;
	}

	public List<DriverDispositivo> obtenerCatalogoDriversDeDisposititvos() {
		return catalogoDriversDeDisposititvos;
	}

	public void establecerCatalogoDriversDeDisposititvos(List<DriverDispositivo> listaDriversDeDisposititvos) {
		this.catalogoDriversDeDisposititvos = listaDriversDeDisposititvos;
	}

	public List<Piso> obtenerListadoDePisos() {
		return listadoDePisos;
	}

	public void establecerListadoDePisos(List<Piso> listadoDePisos) {
		this.listadoDePisos = listadoDePisos;
	}

	public void establecerNivel(int nivel) {
		this.nivel = nivel;
		
	}
	public int obtenerNivel() {
		return nivel;
	}
	
	public List<Sensor> obtenerListadoDeSensores() {
		return listadoDeSensores;
	}

	public void establecerListadoDeSensores(List<Sensor> listadoDeSensores) {
		this.listadoDeSensores = listadoDeSensores;
	}

	public List<Implicacion> obtenerListadoDeReglas() {
		return listaDeReglas;
	}

	public void establecerListadoDeReglas(List<Implicacion> listaDeReglas) {
		this.listaDeReglas = listaDeReglas;
	}
	
	 
	
	
	

}
