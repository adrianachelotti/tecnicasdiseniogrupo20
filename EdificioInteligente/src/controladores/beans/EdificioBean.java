package controladores.beans;

import java.util.List;

import modelo.edificio.Dispositivo;
import modelo.edificio.Piso;

public class EdificioBean {
	
	List<Piso> listadoDePisos;
	
	List<Dispositivo> listaDeDisposititvos;

	public List<Dispositivo> getListaDeDisposititvos() {
		return listaDeDisposititvos;
	}

	public void setListaDeDisposititvos(List<Dispositivo> listaDeDisposititvos) {
		this.listaDeDisposititvos = listaDeDisposititvos;
	}

	public List<Piso> getListadoDePisos() {
		return listadoDePisos;
	}

	public void setListadoDePisos(List<Piso> listadoDePisos) {
		this.listadoDePisos = listadoDePisos;
	}
	

}
