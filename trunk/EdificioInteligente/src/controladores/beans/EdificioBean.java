package controladores.beans;

import java.util.List;

import modelo.driver.*;
import modelo.edificio.Dispositivo;
import modelo.edificio.Piso;

public class EdificioBean {
	
	List<Piso> listadoDePisos;
	
	List<DriverDispositivo> catalogoDriversDeDisposititvos;
	
	List<DriverSensor> catalogoDriversDeSensores;

	List<Dispositivo> listadoDeDispositivos;
	
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

	private int nivel;

	

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
	

}