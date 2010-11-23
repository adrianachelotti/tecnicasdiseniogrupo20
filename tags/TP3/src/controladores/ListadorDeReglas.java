package controladores;

import java.util.Map;

import modelo.edificio.Edificio;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import controladores.beans.EdificioBean;

@SuppressWarnings("serial")
public class ListadorDeReglas extends ActionSupport {
	
	@SuppressWarnings("unused")
	private Map<String, Object> session;

	private int nivel;
	
	private String habilitacionRegla;
	
	private String reglaElegida;
	
	
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
	 * Obtiene si la regla es habilitada
	 * @return habilitacion de la regla
	 */
	public String getHabilitacionRegla() {
		return habilitacionRegla;
	}
	
	/**
	 * Establece si la regla va a hacer habilitada o no
	 * @param habilitacionRegla habilita o no la regla
	 */
	public void setHabilitacionRegla(String habilitacionRegla) {
		this.habilitacionRegla = habilitacionRegla;
	}

	/**
	 * Obtiene la regla elegida 
	 * @return regla elegida
	 */
	public String getReglaElegida() {
		return reglaElegida;
	}
	
	/**
	 * Establece la regla elegida
	 * @param reglaElegida regla elegida
	 */
	public void setReglaElegida(String reglaElegida) {
		this.reglaElegida = reglaElegida;
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
	
	/**
	 * Habilita una regla seleccionada
	 * @return "success"
	 */
	public String configurarRegla(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		int indiceImplicacion = Integer.parseInt(reglaElegida);
		if (this.habilitacionRegla.equalsIgnoreCase("habilitar")){
			edificio.obtenerPiso(nivel).obtenerManejadorDeSucesos().habilitarImplicacion(indiceImplicacion);
		}else{
			edificio.obtenerPiso(nivel).obtenerManejadorDeSucesos().deshabilitarImplicacion(indiceImplicacion);
		}
		contenedor.establecerListadoDeReglas(edificio.obtenerPiso(this.nivel).obtenerManejadorDeSucesos().obtenerImplicaciones());
		session.put("piso", contenedor);
		setSession(session);
		return "success";
		
	}
	

}
