package controladores;

import java.util.Map;

import modelo.edificio.Edificio;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

public class ListadorDeReglas extends ActionSupport {
	
	private Map<String, Object> session;

	private int nivel;
	
	/**
	 * Obtiene el nivel seleccionado para ver las reglas
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
	 * Establece la session
	 * @param session session actual
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * Se pasa a traves de la session las reglas del nivel seleccionado
	 */
	public String execute(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDeReglas(edificio.getPisos().get(this.nivel).obtenerManejadorDeSucesos().obtenerImplicaciones());
		contenedor.establecerNivel(this.nivel);
		session.put("piso", contenedor);
		setSession(session);
		return "success";
	}
	
	/**
	 * Se pasa a traves de la session los pisos del edificio
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
	

}
