package modelo.edificio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.driver.DriverDispositivo;
import modelo.driver.DriverSensor;
import modelo.manejadorDeSucesos.Implicacion;


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
	 * Obtiene el piso del nivel pasado como parametro.
	 * @param nivel nivel del piso a buscar.
	 * @return piso del edificio con el nivel pasado como parametro.
	 */
	public Piso obtenerPiso(int nivel){
		int posicionPiso = this.getPisos().indexOf(new Piso(nivel));
		return this.getPisos().get(posicionPiso);
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
	public List<DriverSensor>	obtenerCatalogoDriversDeSensores() {
		return catalogoDriversDeSensores;
	}

	/**
	 * Obtiene el catalog de los dispositivos con los que cuenta el edificio.
	 * @return catalog de dispositivos del edificio.
	 */
	public List<DriverDispositivo> obtenerCataloDriversDeDispositivos() {
		return catalogoDriversDeDispositivos;
	}
	
	/**
	 * Obtiene las reglas que posee el edificio.
	 * @return reglas que posee el edificio.
	 */
	public List<Implicacion> obtenerReglas(){
		List<Implicacion> reglas=new ArrayList<Implicacion>();
		for (Piso piso : pisos) {
			reglas.addAll(piso.getManejadorDeSucesos().obtenerImplicaciones());
		}
		return reglas;
	}
	
	/**
	 * Obtiene la regla con el identificador pasado como parametro.
	 * @param identificador identificador de la regla a buscar.
 	 * @return regla con el identificador.
	 */
	public Implicacion obtenerReglaPorIdentificador(int identificador){
		boolean encontrado = false;
		Iterator<Piso> it = pisos.iterator();
		Implicacion implicacionActual=null;
		Piso pisoActual = null;
		while(it.hasNext() && !encontrado){
			pisoActual=it.next();
			implicacionActual = pisoActual.getManejadorDeSucesos().obtenerImplicacionPorIdentificador(identificador);
			if(implicacionActual!=null){
				encontrado=true;
			}
		}
		return implicacionActual;
	}
	
	/**
	 * Habilita la regla cuyo identificador es el pasado como parametro.
	 * @param identificador identificador de la regla a habilitar.
	 */
	public void habilitarRegla(int identificador){
		Implicacion implicacion = obtenerReglaPorIdentificador(identificador);
		if(implicacion!= null) implicacion.habilitar();
	}
	
	/**
	 * Deshabilita la regla cuyo identificador es el pasado como parametro.
	 * @param identificador identificador de la regla a deshabilitar.
	 */
	public void deshabilitarRegla(int identificador){
		Implicacion implicacion = obtenerReglaPorIdentificador(identificador);
		if(implicacion!= null) implicacion.deshabilitar();
	}
}
