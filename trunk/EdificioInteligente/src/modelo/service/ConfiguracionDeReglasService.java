package modelo.service;

import java.util.List;

import modelo.edificio.Edificio;
import modelo.edificio.Piso;
import modelo.manejadorDeSucesos.Implicacion;

public class ConfiguracionDeReglasService {
	
	private Edificio edificio = Edificio.obtenerInstancia();
	
	public Implicacion[] obtenerReglasPorPiso(int nivel){
		Piso piso = edificio.obtenerPiso(nivel);
		List<Implicacion> reglas = piso.getManejadorDeSucesos().obtenerImplicaciones();
		return reglas.toArray(new Implicacion[reglas.size()]);
	}
	
	public Implicacion[] obtenerReglas(){
		List<Implicacion> reglas = edificio.obtenerReglas();
		return reglas.toArray(new Implicacion[reglas.size()]);
	}
	
	public void habilitarRegla(int identificador){
		edificio.habilitarRegla(identificador);
	}
	
	public void deshabilitarRegla(int identificador){
		edificio.deshabilitarRegla(identificador);
	}
}

