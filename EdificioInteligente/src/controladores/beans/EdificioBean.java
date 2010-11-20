package controladores.beans;

import java.util.List;

import modelo.driver.DriverDispositivo;
import modelo.edificio.Piso;

public class EdificioBean {
	
	List<Piso> listadoDePisos;
	
	List<DriverDispositivo> catalogoDriversDeDisposititvos;

	private int nivel;

	public int getNivel() {
		return nivel;
	}

	public List<DriverDispositivo> getCatalogoDriversDeDisposititvos() {
		return catalogoDriversDeDisposititvos;
	}

	public void setCatalogoDriversDeDisposititvos(List<DriverDispositivo> listaDriversDeDisposititvos) {
		this.catalogoDriversDeDisposititvos = listaDriversDeDisposititvos;
	}

	public List<Piso> getListadoDePisos() {
		return listadoDePisos;
	}

	public void setListadoDePisos(List<Piso> listadoDePisos) {
		this.listadoDePisos = listadoDePisos;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
		
	}
	

}
