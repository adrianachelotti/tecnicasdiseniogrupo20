package controladores;

import java.util.Map;

import modelo.edificio.Edificio;
import modelo.edificio.Sensor;
import modelo.manejadorDeSucesos.Suceso;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

@SuppressWarnings("serial")
public class ListadorDeSensores extends ActionSupport {
	@SuppressWarnings("unused")
	private Map<String, Object> session;
	
	/**
	 * Nivel del piso donde se encuentra la lista de sensores
	 */
	private int nivel;
	
	/**
	 * Identificador del sensor a cambiar el estado o deshabilitar
	 */
	private String idSensor;
	
	/**
	 * Medicion del sensor
	 */
	private String medicionCambiar;
	
	/**
	 * Estado a cambiar al sensor 
	 */
	private String estadoCambiar;
	
	
	/**
	 * Obtiene el nivel elegido 
	 * @return nivel elegido
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * Establece el nivel elegido
	 * @param nivel nivel elegido
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Establece la session
	 * @param session session
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * Obtiene el id del sensor a cambiar la medicion
	 * @return id del sensor
	 */
	public String getIdSensor() {
		return idSensor;
	}
	
	/**
	 * Establece el id del sensor a cambiar la medicion
	 * @param idSensor id del sensor
	 */
	public void setIdSensor(String idSensor) {
		this.idSensor = idSensor;
	}
	/**
	 * Obtiene la medicion a cambiar al sensor
	 * @return medicion a cambiar
	 */
	public String getMedicionCambiar() {
		return medicionCambiar;
	}
	
	/**
	 * Establece la medicio a cambiar al sensor 
	 * @param medicionCambiar medicion a cambiar
	 */
	public void setMedicionCambiar(String medicionCambiar) {
		this.medicionCambiar = medicionCambiar;
	}
	/**
	 * Obtiene el estado a cambiar 
	 * @return el estado a cambiar
	 */
	public String getEstadoCambiar() {
		return estadoCambiar;
	}
	/**
	 * Establece el estado a cambiar por el sensor
	 * @param estadoCambiar estado a cambiar
	 */
	public void setEstadoCambiar(String estadoCambiar) {
		this.estadoCambiar = estadoCambiar;
	}
	/**
	 * Carga  la lista de sensores existente en el piso
	 */
	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDeSensores(edificio.getPisos().get(this.nivel).obtenerSensores());
		contenedor.establecerNivel(this.nivel);
		session.put("piso", contenedor);
		setSession(session);
		return "success";
	}
	
	/**
	 * Carga  la lista de pisos para mostrar 
	 * @return "listarPisos"
	 */
	public String seleccionarPisos(){		
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDePisos(edificio.getPisos());
		session.put("edificio", contenedor);
		setSession(session);
		return "listarPisos";
	}
	
	/**
	 * Cambia la medicion del sensor elegido si el mismo 
	 * esta habilitado, en caso contrario muestra un mensaje de error
	 * @return "sucees"
	 */
	public String cambiarMedicion(){
		Edificio edificio = Edificio.obtenerInstancia();
		int indice = Integer.parseInt(idSensor);
		Sensor sensor = edificio.getPisos().get(this.nivel).obtenerSensores().get(indice);
		if(sensor.estaHabilitado()){
			sensor.establecerMedicion(medicionCambiar);
			edificio.getPisos().get(this.nivel).obtenerManejadorDeSucesos().agregarSuceso(new Suceso(medicionCambiar));
		}else{
			addActionError("Imposible cambiar la medición, el sensor se encuentra deshabilitado");
		}
		return execute();
	}
	
	public String cambiarEstado(){
		Edificio edificio = Edificio.obtenerInstancia();
		int indice = Integer.parseInt(idSensor);
		Sensor sensor = edificio.getPisos().get(this.nivel).obtenerSensores().get(indice);
		if (estadoCambiar.equalsIgnoreCase("habilitar")){
			sensor.habilitar();
		}
		if (estadoCambiar.equalsIgnoreCase("deshabilitar")){
			sensor.deshabilitar();
		}
		
		
		return execute();
	}
	
	


}
