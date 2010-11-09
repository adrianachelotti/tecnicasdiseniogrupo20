package edificio;
import java.util.ArrayList;
import java.util.List;

import sensores.Sensor;

import dispositivos.*;

public class Piso {
	
	private int nivel ;
	
	private List<Dispositivo> dispositivos;
	
	private List<Sensor> sensores;
	
	public Piso(int nivel){
		this.dispositivos = new ArrayList<Dispositivo>();
		this.sensores = new ArrayList<Sensor>();
		this.nivel = nivel;
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
	

}
