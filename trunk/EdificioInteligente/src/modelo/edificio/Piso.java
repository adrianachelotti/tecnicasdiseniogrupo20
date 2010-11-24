package modelo.edificio;
import java.util.ArrayList;
import java.util.List;

import modelo.manejadorDeSucesos.ManejadorDeSucesos;

public class Piso {
	
	/**
	 * Nivel donde se encuentra el piso en  el edificio
	 */
	private int nivel ;
	
	/**
	 * Dispositivos existentes en el piso
	 */
	private List<Dispositivo> dispositivos;
	
	/**
	 * Sensores existentes en  el piso
	 */
	private List<Sensor> sensores;
	
	/**
	 * Manejador de sucesos del piso
	 */
	private ManejadorDeSucesos manejadorDeSucesos;
	
	/**
	 * Constructor del piso cuyo nivel se pasa por parametro 
	 * @param nivel del piso
	 */
	public Piso(int nivel){
		this.dispositivos = new ArrayList<Dispositivo>();
		this.sensores = new ArrayList<Sensor>();
		this.nivel = nivel;
		this.manejadorDeSucesos= new ManejadorDeSucesos();
	}
	/**
	 * Agrega un dispositivo a la lista de dispositivos del piso
	 * @param dispositivoAgregar dispositivo a agregar
	 */
	public void agregarDispositivo(Dispositivo dispositivoAgregar){
		this.dispositivos.add(dispositivoAgregar);
	}
	
	/**
	 * Agrega un sensor a la lista de sensores del piso
	 * @param sensorAgregar sensor a agregar
	 */
	public void agregarSensores(Sensor sensorAgregar){
		this.sensores.add(sensorAgregar);
	}
	
	/**
	 * Obtiene el nivel del piso
	 * @return nivel del piso
	 */
	public int obtenerNivel() {
		return nivel;
	}
	
	/**
	 * Establece el nivel del piso
	 * @param nivel nivel del piso
	 */
	public void establecerNivel(int nivel) {
		this.nivel = nivel;
	}
	
	/**
	 * Obtiene el manejador de sucesos del piso
	 * @return manejador de sucesos del piso
	 */
	public ManejadorDeSucesos obtenerManejadorDeSucesos() {
		return manejadorDeSucesos;
	}
	
	/**
	 * Establecer el manejador de sucesos del piso
	 * @param manejadorDeSucesos manejador de sucesos del piso
	 */
	public void establecerManejadorDeSucesos(ManejadorDeSucesos manejadorDeSucesos) {
		this.manejadorDeSucesos = manejadorDeSucesos;
	}

	/**
	 * Obtiene la lista de dispositivos del piso
	 * @return
	 */
	public List<Dispositivo> obtenerDispositivos() {
		return dispositivos;
	}

	/**
	 * Establece los dispositivos del piso
	 * @param dispositivos dispositivos del piso
	 */
	public void establecerDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	/**
	 * Obtiene los sensores del piso
	 * @return sensores del piso
	 */
	public List<Sensor> obtenerSensores() {
		return sensores;
	}

	/**
	 * Establece los sensores del piso
	 * @param sensores sensores del piso
	 */
	public void establecerSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}
	
	/**
	 * TODO: ver de sacar repetidas
	 * Obtiene el catalago de todas las mediciones posibles
	 * @return
	 */
	public List<String> obtenerCatalogoMedicionesPosibles(){
		List<String> medicionesPosibles = new ArrayList<String>();
		for (Sensor sensor:this.obtenerSensores()){
			for (String medicion: sensor.obtenerMedicionesPosibles()){
				if (!medicionesPosibles.contains(medicion)){
					medicionesPosibles.add(medicion);
				}
			}
			
		}
		return medicionesPosibles;
	}
	@Override
	/**
	 * Metodo para comparar un piso con otro a traves del nivel
	 */
	public boolean equals(Object obj) {
		return this.nivel==((Piso)obj).obtenerNivel();
	}
	

}
