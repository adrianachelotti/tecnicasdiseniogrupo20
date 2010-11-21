package controladores;

import java.util.Map;

import modelo.edificio.Dispositivo;
import modelo.edificio.Edificio;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

public class ListadorDeDispositivos extends ActionSupport {
	
	private Map<String, Object> session;
	
	private int nivel;
	
	private String idDispositivo;
	
	private String accionEjecutar ="";
	
	/**
	 * Obtiene el nivel seleccionado para ver los dispostivos
	 * @return nivel de piso
	 */
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * Establece el nivel seleccionado
	 * @param nivel nivel seleccionado
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Obtiene la accion a ejecutar seleccionada por el usuario
	 * @return accion a ejecutar
	 */
	public String getAccionEjecutar() {
		return accionEjecutar;
	}

	/**
	 * Establece la accion a ejecutar seleccionada por el usuario
	 * @param accion accion a ejecutar
	 */
	public void setAccionEjecutar(String accion) {
		this.accionEjecutar = accion;
	}
	
	/**
	 * Establece la session
	 * @param session session actual
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * Obtiene el id del dispositivo seleccionado por el usuario
	 * @return id del dispositivo
	 */
	public String getIdDispositivo() {
		return idDispositivo;
	}
	
	/**
	 * Establece el id del dispositivo seleccionado
	 * @param idDispositivo
	 */
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	
	/**
	 * Se pasa a traves de la session los dispositivos
	 * del nivel seleccionado
	 * @return "success"
	 */
	public String execute(){		
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();		
		contenedor.establecerListadoDeDispositivos(edificio.getPisos().get(this.nivel).obtenerDispositivos());
		contenedor.establecerNivel(this.nivel);
		session.put("piso", contenedor);
		setSession(session);
		return "success";
	}
	
	/**
	 * Se listan los pisos del edificio 
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
	 * Segun la accion a ejecutar elegida por el usuario se
	 * enciende o apaga el dispositivo
	 * @return "success"
	 */
	public String ejecutar(){
		Edificio edificio = Edificio.obtenerInstancia();
		int indice = Integer.parseInt(idDispositivo);
		Dispositivo dispositivo = edificio.getPisos().get(this.nivel).obtenerDispositivos().get(indice);
		if (accionEjecutar.equalsIgnoreCase("encender")){
			dispositivo.encender();
		}
		if (accionEjecutar.equalsIgnoreCase("apagar")){
			dispositivo.apagar();
		}
		return execute();
	}
	

}
