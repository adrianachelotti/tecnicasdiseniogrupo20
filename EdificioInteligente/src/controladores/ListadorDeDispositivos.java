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
	
	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * 
	 * @return
	 */
	public String getAccionEjecutar() {
		return accionEjecutar;
	}

	public void setAccionEjecutar(String accion) {
		this.accionEjecutar = accion;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getIdDispositivo() {
		return idDispositivo;
	}
	
	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
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
	
	public String seleccionarPisos(){		
		Map<String,Object> session = ActionContext.getContext().getSession();
		Edificio edificio = Edificio.obtenerInstancia();
		EdificioBean contenedor = new EdificioBean();
		contenedor.establecerListadoDePisos(edificio.getPisos());
		session.put("edificio", contenedor);
		setSession(session);
		return "listarPisos";
	}
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
