package modelo.edificio;
import java.util.ArrayList;
import java.util.List;

import modelo.manejadorDeSucesos.ManejadorDeSucesos;

public class Piso {
	
	private int nivel ;
	
	private List<Dispositivo> dispositivos;
	
	private List<Sensor> sensores;
	
	private ManejadorDeSucesos manejadorDeSucesos;
	
	public Piso(int nivel){
		this.dispositivos = new ArrayList<Dispositivo>();
		this.sensores = new ArrayList<Sensor>();
		this.nivel = nivel;
		this.manejadorDeSucesos= new ManejadorDeSucesos();
	}
	
	public void agregarDispositivo(Dispositivo dispositivoAgregar){
		this.dispositivos.add(dispositivoAgregar);
	}
	
	public void agregarSensores(Sensor sensorAgregar){
		this.sensores.add(sensorAgregar);
	}
	
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public ManejadorDeSucesos getManejadorDeSucesos() {
		return manejadorDeSucesos;
	}
	public void setManejadorDeSucesos(ManejadorDeSucesos manejadorDeSucesos) {
		this.manejadorDeSucesos = manejadorDeSucesos;
	}

	public List<Dispositivo> obtenerDispositivos() {
		return dispositivos;
	}

	public void establecerDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public List<Sensor> obtenerSensores() {
		return sensores;
	}

	public void establecerSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}
		
	@Override
	public boolean equals(Object obj) {
		return this.nivel==((Piso)obj).getNivel();
	}
	

}